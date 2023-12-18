package controller;

import model.ProgramState;
import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.values.RefValue;
import model.values.ValueInterface;
import repository.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    public IRepository repo;
    private ExecutorService executor;
    public Controller(IRepository repo) {
        this.repo = repo;
    }
    public void addProgram(ProgramState program) { repo.addProgram(program); }
    public ProgramState getProgramatIndex(int index) throws MyException{
        return repo.getPrgAtIndex(index);
    }
    private Map<Integer, ValueInterface> garbageCollector(ProgramState program){
        MyIDictionary<String, ValueInterface> sym = program.getSymTable();
        MyIDictionary2<ValueInterface> heap = program.getHeap();
        Map<Integer, ValueInterface> referenced = new HashMap<Integer, ValueInterface>();
        List<Integer> addresses = new ArrayList<Integer>();
        addresses = getAddressFromSymTable(sym.values());

        for (Map.Entry<Integer, ValueInterface> e: heap.getAll()){
            if (addresses.contains(e.getKey()))
                referenced.put(e.getKey(), e.getValue());
            else{
                int elemAddress = e.getKey();
                for (Map.Entry<Integer, ValueInterface> searchAddress: heap.getAll()){
                    ValueInterface val = searchAddress.getValue();
                    if (val instanceof RefValue){
                        RefValue ref = (RefValue)val;
                        if (ref.getAddr() == elemAddress)
                            referenced.put(elemAddress, e.getValue());
                    }
                }
            }
        }

        return referenced;
    }

    private List<Integer> getAddressFromSymTable(Collection<ValueInterface> symTable){
        return symTable.stream().filter(v->v instanceof RefValue).map(v-> { RefValue v1 = (RefValue)v; return v1.getAddr(); }).collect(Collectors.toList());
    }
    List<Integer> getAddrFromHeap(Collection<ValueInterface> heapValues){
        return heapValues.stream()
                .filter(v -> v instanceof  RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    public ProgramState oneStep(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        if (stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        StatementInterface crtStmt = stk.pop();
        return crtStmt.execute(state);
    }
    private void saveToLogFile(ProgramState programState) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(programState.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    void oneStepForAllPrg(List<ProgramState>prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logProgramStateExecution(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<ProgramState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try { return future.get();}
                catch(InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                } return null;})
                .filter(p -> p!=null)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logProgramStateExecution(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        repo.setProgramsList(prgList);

    }
    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }
    public Map<Integer,ValueInterface> unsafeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer,ValueInterface> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    List<Integer> getAddrFromSymTable(Collection<ValueInterface> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    public  void allStep() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList=removeCompletedPrg(repo.getProgramList());

        while(prgList.size() > 0){
            System.out.println(prgList.size());
            oneStepForAllPrg(prgList);
            prgList.stream().forEach(prg -> prg.getHeap().setHeap(unsafeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getDictionary().values()), getAddrFromHeap(prg.getHeap().getHeap().values()), prg.getHeap().getHeap())
            ));
            prgList.stream().forEach(prg -> System.out.println(prg));
            prgList=removeCompletedPrg(repo.getProgramList());
        }
        executor.shutdownNow();
        repo.setProgramsList(prgList);
    }

}
