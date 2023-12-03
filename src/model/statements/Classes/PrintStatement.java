package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.statements.StatementInterface;
import model.values.ValueInterface;

public class PrintStatement implements StatementInterface {
    ExpressionInterface exp;

    public PrintStatement(ExpressionInterface exp) {
        this.exp = exp;
    }

    public ExpressionInterface getExp() {
        return exp;
    }

    public String toString(){
        return "print(" + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface val = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(val);
        return state;
    }
}
