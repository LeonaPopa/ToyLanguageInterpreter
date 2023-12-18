package repository;
import model.ProgramState;
import model.exceptions.MyException;
import model.program.output.MyIList;
import model.values.ValueInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Repository implements IRepository{
    private List<ProgramState> states;
    private String logFilePath;
    public Repository(ProgramState prg, String logFilePath) {
        this.states = new ArrayList<ProgramState>();
        states.add(prg);
        this.logFilePath = logFilePath;
    }
    @Override
    public void addProgram(ProgramState pg){
        states.add(pg);
    }
    @Override
    public List<ProgramState> getProgramList() {
        return states;
    }
    @Override
    public void logProgramStateExecution(ProgramState state) throws MyException, IOException{
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        logFile.println("ID:"+state.getId() + "\n");
        logFile.println("ExeStack:");
        ProgramState crt = states.get(0);
        Iterator it = crt.getExeStack().getAll().iterator();
        while(it.hasNext())
            logFile.println(it.next());
        logFile.println("SymTable:");
        for(Map.Entry<String, ValueInterface> e: crt.getSymTable().getDictionary().entrySet())
            logFile.println(e);
        logFile.println("Out:");
        for(ValueInterface e: crt.getOut().getAll())
            logFile.println(e);
        logFile.println("FileTable:");
        logFile.println(crt.getFileTable().toString());
        logFile.println("-------------------------");
        logFile.close();
    }
    @Override
    public ProgramState getPrgAtIndex(int index) throws MyException {
        return states.get(index);
    }
    @Override
    public void setProgramsList(List<ProgramState> prg) {
        states=prg;
    }
}
