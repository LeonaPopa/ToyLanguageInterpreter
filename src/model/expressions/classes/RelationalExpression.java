package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.symbolTable.MyIDictionary;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.ValueInterface;

public class RelationalExpression implements Expressioninterface {
    Expressioninterface e1;
    Expressioninterface e2;
    String op;

    public RelationalExpression(Expressioninterface e1, Expressioninterface e2, String op) {
        this.e1 = e1; this.e2 = e2; this.op = op; }


    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl) throws MyException {
        ValueInterface v1, v2;
        v1 = e1.eval(tbl);
        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl);
            if(v1.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1 = i1.getVal();
                int n2 = i2.getVal();
                switch (op){
                    case "<":
                        return new BoolValue(n1 < n2);
                    case "<=":
                        return new BoolValue(n1 <= n2);
                    case "==":
                        return new BoolValue(n1 == n2);
                    case "!=":
                        return new BoolValue(n1 != n2);
                    case ">":
                        return new BoolValue(n1 > n2);
                    case ">=":
                        return new BoolValue(n1 >= n2);
                    default:
                        throw new MyException("incorrect operand");
                }
            }
            else{
                throw new MyException("second operand isn't an integer");
            }
        }
        else{
            throw new MyException("first operand isn't an integer");
        }
    }
}
