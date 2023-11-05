package model.expressions;

import model.exceptions.MyException;
import model.program.symbolTable.MyIDictionary;
import model.values.ValueInterface;

public interface Expressioninterface {
    ValueInterface eval(MyIDictionary<String, ValueInterface> tbl) throws MyException;
}
