package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.program.executableStack.MyStack;
import model.statements.StatementInterface;

public class ForkStatement implements StatementInterface {
    StatementInterface stmt;
    public ForkStatement(StatementInterface statement) {
        this.stmt = statement;
    }
    @Override
    public String toString() { return "fork(" + stmt + ");"; }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stack = new MyStack<>();
        return new ProgramState(stack, state.getSymTable().clone(), state.getOut(), state.getFileTable(), state.getHeap(), stmt);
    }

    @Override
    public StatementInterface deepCopy() {
        return new ForkStatement(stmt.deepCopy());
    }
}
