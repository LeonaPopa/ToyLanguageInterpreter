package model.statements.Classes;

import model.statements.StatementInterface;
import model.program.executableStack.MyIStack;
import model.ProgramState;

public class ComposedStatement implements StatementInterface {
    StatementInterface first;
    StatementInterface snd;
    public ComposedStatement(StatementInterface first, StatementInterface snd) {
        this.first = first;
        this.snd = snd;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return state;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }
}
