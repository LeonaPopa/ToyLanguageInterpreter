package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.symbolTable.MyIDictionary;
import model.values.ValueInterface;

public class VariableExpression implements Expressioninterface {
    String id;
    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl) throws MyException {
        return tbl.getElementByKey(id);
    }
}
