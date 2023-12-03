package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.RefType;
import model.values.RefValue;
import model.values.ValueInterface;

public class WriteHeap implements StatementInterface {
    String variableName;
    ExpressionInterface expression;
    public WriteHeap(String name, ExpressionInterface exp) { variableName = name; expression = exp; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIDictionary2<ValueInterface> heapTable = state.getHeap();

        if (symTable.isDefined(variableName)){
            ValueInterface variableValue = symTable.getElementByKey(variableName);
            if (variableValue.getType() instanceof RefType){
                RefValue variableReference = (RefValue)variableValue;
                int address = variableReference.getAddr();
                if (heapTable.isDefined(address)){
                    ValueInterface expressionValue = expression.eval(symTable, heapTable);
                    RefType locationType = (RefType)variableReference.getType();
                    //t.getInner().equals(val.getType()
                    if (locationType.getInner().equals(expressionValue.getType())){
                        heapTable.update(address, expressionValue);
                    }
                    else throw new MyException(expressionValue + " and " + locationType + " do not match!");
                }
                else throw new MyException("Address " + address + " does not exist!");
            }
            else throw new MyException("Variable " + variableName + " is not a refType!");
        }
        else throw new MyException("Variable " + variableName + " was not declared!");
        return state;
    }

    @Override
    public String toString() { return "WriteHeap(" + variableName + ", " + expression + ");"; }
}
