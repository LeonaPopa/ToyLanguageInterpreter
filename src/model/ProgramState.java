package model;

import model.program.executableStack.MyIStack;
import model.program.output.MyIList;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<StatementInterface> exeStack;
    MyIDictionary<String, ValueInterface> symTable;
    MyIList<ValueInterface> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    StatementInterface originalProgram;

    public ProgramState(MyIStack<StatementInterface> exeStack,
                        MyIDictionary<String, ValueInterface> symTable,
                        MyIList<ValueInterface> out, MyIDictionary<StringValue, BufferedReader> files, StatementInterface originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = files;
        exeStack.push(originalProgram);
    }
    public String toString(){
        return "EXECUTION STACK:\n" + exeStack.toString()  + "\nSYMBOLS TABLE:\n" + symTable.toString() +
                "\nOUTPUT:\n" +  out.toString() + "\nFILE TABLE:\n" + fileTable.toString() + "\n";
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

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public void setExecutionStack(MyIStack st) { exeStack = st; }
    public void setSymbolsTable(MyIDictionary<String, ValueInterface> dict) { symTable = dict; }
    public void setOutput(MyIList<ValueInterface> l) { out = l; }

    public void addStatement(StatementInterface st){
        exeStack.push(st);
    }

    public StatementInterface deleteStatement() throws Exception{
        StatementInterface st = exeStack.pop();
        return st;
    }
}
