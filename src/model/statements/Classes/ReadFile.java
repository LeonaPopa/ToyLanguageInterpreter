package model.statements.Classes;
import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.StringType;
import model.values.IntValue;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements StatementInterface {
    private ExpressionInterface expression;
    private String variableName;

    public ReadFile(ExpressionInterface expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symbolTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        ValueInterface value = symbolTable.getElementByKey(variableName);
        MyIDictionary2<ValueInterface> heap = state.getHeap();
        if (value == null)
            throw new MyException("Variable " + variableName + " was not declared!");

        ValueInterface expressionValue = expression.eval(symbolTable, heap);
        if (expressionValue.getType().equals(new StringType())){
            StringValue file = (StringValue)expressionValue;
            if (fileTable.isDefined(file)){
                BufferedReader reader = fileTable.getElementByKey(file);
                try {
                    String line = reader.readLine();
                    IntValue variableValue;
                    if (line == null){
                        variableValue = new IntValue(0);
                    }
                    else
                        variableValue = new IntValue(Integer.parseInt(line));
                    symbolTable.update(variableName, variableValue);
                }
                catch (IOException ex) {
                    throw new MyException(ex.getMessage());
                }
            }
            else throw new MyException("File does not exist!");
        }
        else throw new MyException("Expresion must be a stringValue");
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ReadFile(expression.deepCopy(), variableName);
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ", " + variableName + ")";
    }
}
