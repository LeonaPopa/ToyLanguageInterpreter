package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.values.ValueInterface;

public class ValueExpression implements ExpressionInterface {
    ValueInterface e;
    public ValueExpression(ValueInterface e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return String.valueOf(e);
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIDictionary2<ValueInterface> heap) throws MyException {
        return e;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ValueExpression(e.deepCopy());
    }
}
