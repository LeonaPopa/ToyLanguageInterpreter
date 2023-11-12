package repository;

import model.ProgramState;
import model.exceptions.MyException;

import java.io.IOException;

public interface IRepository {
    ProgramState getCrtPrg();
    void addProgram(ProgramState pg);
    public void logProgramStateExecution(ProgramState state) throws MyException, IOException;
    ProgramState getPrgAtIndex(int index) throws MyException;
}
