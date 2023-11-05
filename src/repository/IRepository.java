package repository;

import model.ProgramState;

public interface IRepository {
    ProgramState getCrtPrg();
    void addProgram(ProgramState pg);
}
