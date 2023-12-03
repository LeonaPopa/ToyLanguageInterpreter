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
import java.util.stream.Collectors;

public class Controller {
    public IRepository repo;
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


    public ProgramState oneStep(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        if (stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        StatementInterface crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep(ProgramState prg) throws MyException, IOException {
        repo.logProgramStateExecution(prg);
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            prg.getHeap().setContent(garbageCollector(prg));
            repo.logProgramStateExecution(prg);
            // Log program state after each step
        }
    }

    private void saveToLogFile(ProgramState programState) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(programState.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
