package repository;
import model.ProgramState;
import model.exceptions.MyException;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addProgram(ProgramState pg);
    List<ProgramState> getProgramList();
    public void logProgramStateExecution(ProgramState state) throws MyException, IOException;
    ProgramState getPrgAtIndex(int index) throws MyException;
    void setProgramsList(List<ProgramState> prg);
}
