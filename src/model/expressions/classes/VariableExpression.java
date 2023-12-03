package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.values.ValueInterface;

public class VariableExpression implements ExpressionInterface {
    String id;
    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIDictionary2<ValueInterface> heap) throws MyException {
        return tbl.getElementByKey(id);
    }
}
