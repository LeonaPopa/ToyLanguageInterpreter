package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.RefType;
import model.types.TypeInterface;
import model.values.RefValue;
import model.values.ValueInterface;

public class NewStatement implements StatementInterface {
    String var_name;
    ExpressionInterface expr;
    public  NewStatement(String name, ExpressionInterface expr){
        this.var_name = name;
        this.expr = expr;
    }
    public String toString() { return "new(" + var_name + ", " + expr + ");"; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIDictionary2<ValueInterface> heapTable = state.getHeap();
        if (symTable.isDefined(var_name)){
            ValueInterface val = symTable.getElementByKey(var_name);
            if (val.getType() instanceof RefType){
                RefValue reference = (RefValue)val;
                ValueInterface expVal = expr.eval(symTable, heapTable);
                RefType referenceType = (RefType) reference.getType();
                TypeInterface locationType = referenceType.getInner();
                int key = heapTable.getNextFree();
                if (expVal.getType().equals(locationType)){
                    heapTable.add(expVal);
                    symTable.add(var_name, new RefValue(key, locationType));
                }
                else throw new MyException("Types of " + expr + " and " + var_name + " do not match!");
            }
            else
                throw new MyException("Variable " + var_name + " must be a refType");
        }
        else
            throw new MyException("The variable " + var_name + " was not declared!");
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NewStatement(var_name, expr.deepCopy());
    }
}
