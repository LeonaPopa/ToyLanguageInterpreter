package model.expressions.classes;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.ValueInterface;

public class LogicalExpression implements ExpressionInterface {
    ExpressionInterface e1;
    ExpressionInterface e2;
    String op;

    public LogicalExpression(String op, ExpressionInterface e1, ExpressionInterface e2) {
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
        ValueInterface nr1 = e1.eval(tbl, heap);
        if (nr1.getType().equals(new BoolType())) {
            ValueInterface nr2 = e2.eval(tbl, heap);
            if(nr2.getType().equals(new BoolType())){
                BoolValue v1 = (BoolValue) nr1;
                BoolValue v2 = (BoolValue) nr2;
                if(op.equals("&&"))
                    return new BoolValue(v1.getVal() && v2.getVal());
                if(op.equals("||"))
                    return new BoolValue(v1.getVal() || v2.getVal());
            } else throw new MyException("Operand 2 is not boolean");
        } else throw new MyException("Operand 1 is not boolean");
        throw new MyException("Invalid expression");
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new LogicalExpression(op, e1.deepCopy(), e2.deepCopy());
    }
}
