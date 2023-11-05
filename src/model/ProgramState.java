package model;

import model.program.executableStack.MyIStack;
import model.program.executableStack.MyStack;
import model.program.output.MyIList;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.values.ValueInterface;

public class ProgramState {
    MyIStack<StatementInterface> exeStack;
    MyIDictionary<String, ValueInterface> symTable;
    MyIList<ValueInterface> out;
    StatementInterface originalProgram;

    public ProgramState(MyIStack<StatementInterface> exeStack,
                        MyIDictionary<String, ValueInterface> symTable,
                        MyIList<ValueInterface> out, StatementInterface originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
    }
    public String toString(){
        return exeStack.toString() + "\n" + symTable.toString() + "\n" + out.toString() + "\n";
    }

    public MyIStack<StatementInterface> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, ValueInterface> getSymTable() {
        return symTable;
    }

    public MyIList<ValueInterface> getOut() {
        return out;
    }

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }
}
