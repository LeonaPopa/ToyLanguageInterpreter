package model.expressions;

import model.exceptions.MyException;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.values.ValueInterface;

public interface ExpressionInterface {
    ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIDictionary2<ValueInterface> heap) throws MyException;
}
