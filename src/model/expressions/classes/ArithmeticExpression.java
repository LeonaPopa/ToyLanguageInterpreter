package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.types.IntType;
import model.values.IntValue;
import model.values.ValueInterface;

public class ArithmeticExpression implements ExpressionInterface {
    ExpressionInterface e1;
    ExpressionInterface e2;
    char op;
    public ArithmeticExpression(char op, ExpressionInterface e1, ExpressionInterface e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public String toString() {
        return String.valueOf(e1) + String.valueOf(op) + String.valueOf(e2);
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIDictionary2<ValueInterface> heap) throws MyException {
        ValueInterface v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == '+') return new IntValue(n1 + n2);//switch
                if (op == '-') return new IntValue(n1 - n2);
                if (op == '*') return new IntValue(n1 * n2);
                if (op == '/')
                    if (n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        throw new MyException("invalid evaluation");
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ArithmeticExpression(op, e1.deepCopy(), e2.deepCopy());
    }
}
