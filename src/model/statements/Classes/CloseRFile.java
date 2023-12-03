package model.statements.Classes;
import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.StringType;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements StatementInterface {
    private ExpressionInterface expression;

    public CloseRFile(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIDictionary2<ValueInterface> heap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        ValueInterface expValue = expression.eval(symTable, heap);
        if (expValue.getType().equals(new StringType())){
            StringValue file = (StringValue)expValue;
            if (fileTable.isDefined(file)){
                BufferedReader reader = fileTable.getElementByKey(file);
                try {
                    reader.close();
                }
                catch (IOException ex){
                    throw new MyException(ex.getMessage());
                }
                fileTable.remove(file);
            }
            else throw new MyException("File " + file + " does not exist!");
        }
        else throw new MyException("Expression must be a string!");

        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + expression.toString() + ")";
    }
}
