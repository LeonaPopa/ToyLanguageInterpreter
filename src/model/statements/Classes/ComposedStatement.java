package model.statements.Classes;

import model.exceptions.MyException;
import model.statements.StatementInterface;
import model.program.executableStack.MyIStack;
import model.ProgramState;

public class ComposedStatement implements StatementInterface {
    StatementInterface first;
    StatementInterface second;

    public ComposedStatement(StatementInterface first, StatementInterface snd) {
        this.first = first;
        this.second = snd;
    }
    @Override
    public String toString() {
        return "(" + first.toString() + " " + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ComposedStatement(first.deepCopy(), second.deepCopy());
    }
}
