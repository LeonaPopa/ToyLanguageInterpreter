package model.expressions.classes;

import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.types.RefType;
import model.values.RefValue;
import model.values.ValueInterface;

public class ReadHeap implements ExpressionInterface {
    ExpressionInterface expression;
    public ReadHeap(ExpressionInterface exp) { expression = exp; }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> table, MyIDictionary2<ValueInterface> heap) throws MyException {
        ValueInterface val = expression.eval(table, heap);
        if (val.getType() instanceof RefType){
            RefValue reference = (RefValue)val;
            int address = reference.getAddr();
            ValueInterface valueAtAddress = heap.getElementByKey(address);
            if (valueAtAddress == null)
                throw new MyException("Address " + address + " does not exist!");
            return valueAtAddress;
        }
        else
            throw new MyException("Expression must be a refValue");
    }

    @Override
    public String toString() { return "ReadHeap(" + expression + ");"; }
}

