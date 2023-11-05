package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.symbolTable.MyIDictionary;
import model.values.ValueInterface;

public class ValueExpression implements Expressioninterface {
    ValueInterface e;
    public ValueExpression(ValueInterface e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return String.valueOf(e);
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl) throws MyException {
        return e;
    }
}
