package repository;

import model.ProgramState;
import model.exceptions.MyException;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    List<ProgramState> states;

    public Repository() {
        states = new ArrayList<ProgramState>();
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
    public void logProgramStateExecution() throws MyException{};

}
