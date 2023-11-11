package repository;

import model.ProgramState;
import model.exceptions.MyException;

public interface IRepository {
    ProgramState getCrtPrg();
    void addProgram(ProgramState pg);
    public void logProgramStateExecution() throws MyException;
}
