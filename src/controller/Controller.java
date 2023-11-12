package controller;

import model.ProgramState;
import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.statements.StatementInterface;
import repository.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    public IRepository repo;
    boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
        displayFlag = true;
    }

    public boolean isDisplayFlag() {
        return displayFlag;
    }

    public ProgramState getProgramatIndex(int index) throws MyException{
        return repo.getPrgAtIndex(index);
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }
    public void addProgram(ProgramState program) { repo.addProgram(program); }

    public ProgramState oneStep(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        if (stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        StatementInterface crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep(ProgramState prg) throws MyException, IOException {
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            repo.logProgramStateExecution(prg); // Log program state after each step
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
