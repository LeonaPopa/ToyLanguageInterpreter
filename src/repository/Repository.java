package repository;

import model.ProgramState;
import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.program.output.MyIList;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.values.ValueInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository implements IRepository{
    private List<ProgramState> states;
    private String logFilePath;
    public Repository(String logFilePath) {

        this.states = new ArrayList<ProgramState>();
        this.logFilePath = logFilePath;

    }
    @Override
    public void addProgram(ProgramState pg){
        states.add(pg);
    }

    @Override
    public ProgramState getCrtPrg() {

        return states.get(0);
    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws MyException, IOException{
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write(state.toString());
        logFile.close();
    }

    @Override
    public ProgramState getPrgAtIndex(int index) throws MyException {
        return states.get(index);
    }

    ;
    private String printStack(MyIStack<StatementInterface> stack) {
        StringBuilder stackString = new StringBuilder();
        // Use left-root-right binary tree traversal
        // Assuming you have a method getInOrderTraversal in your stack implementation
        for (StatementInterface stmt : stack.getInOrderTraversal()) {
            stackString.append(stmt.toString()).append("\n");
        }
        return stackString.toString();
    }
    private String printSymTable(MyIDictionary<String, ValueInterface> symTable) {
        StringBuilder symTableString = new StringBuilder();
        for (Map.Entry<String, ValueInterface> entry : symTable.getAll()) {
            symTableString.append(entry.getKey()).append(" --> ").append(entry.getValue().toString()).append("\n");
        }
        return symTableString.toString();
    }
    private String printList(MyIList<ValueInterface> list) {
        StringBuilder listString = new StringBuilder();
        for (ValueInterface value : list.getAll()) {
            listString.append(value.toString()).append("\n");
        }
        return listString.toString();
    }

}
