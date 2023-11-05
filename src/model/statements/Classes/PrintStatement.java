package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.statements.StatementInterface;
import model.values.ValueInterface;

public class PrintStatement implements StatementInterface {
    Expressioninterface exp;

    public PrintStatement(Expressioninterface exp) {
        this.exp = exp;
    }

    public Expressioninterface getExp() {
        return exp;
    }

    public String toString(){
        return "print(" + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface val = exp.eval(state.getSymTable());
        state.getOut().add(val);
        return state;
    }
}
