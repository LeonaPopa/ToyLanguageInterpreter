package model.statements.Classes;
import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.executableStack.MyIStack;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements StatementInterface {
    private Expressioninterface expression;

    public openRFile(Expressioninterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> exeStack = state.getExeStack();
        MyIDictionary<String, ValueInterface> symbolTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        ValueInterface value = expression.eval(symbolTable);
        if (!(value instanceof StringValue)) {
            throw new MyException("Expression result is not a string.");
        }

        StringValue stringValue = (StringValue) value;
        String filename = stringValue.getVal();

        if (fileTable.isDefined(stringValue)) {
            throw new MyException("File '" + filename + "' is already open.");
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            fileTable.add(stringValue, bufferedReader);
        } catch (FileNotFoundException e) {
            throw new MyException("Error opening file '" + filename + "'. File not found.");
        } catch (Exception e) {
            throw new MyException("Error opening file '" + filename + "'.");
        }

        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + expression.toString() + ")";
    }
}

