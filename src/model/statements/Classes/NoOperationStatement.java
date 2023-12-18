package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.statements.StatementInterface;

public class NoOperationStatement implements StatementInterface {
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString() {
        return "no operation";
    }
}
