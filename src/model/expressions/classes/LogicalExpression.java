package model.expressions.classes;

import com.sun.jdi.Value;
import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.symbolTable.MyIDictionary;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.ValueInterface;

public class LogicalExpression implements Expressioninterface {
    Expressioninterface e1;
    Expressioninterface e2;
    String op;

    public LogicalExpression(String op, Expressioninterface e1, Expressioninterface e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public String toString() {
        return String.valueOf(e1) + String.valueOf(op) + String.valueOf(e2);
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl) throws MyException {
        ValueInterface nr1 = e1.eval(tbl);
        if (nr1.getType().equals(new BoolType())) {
            ValueInterface nr2 = e2.eval(tbl);
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
}
