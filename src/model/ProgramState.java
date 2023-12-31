package model;

import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.program.heap.MyIDictionary2;
import model.program.output.MyIList;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.TypeInterface;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<StatementInterface> exeStack;
    MyIDictionary<String, ValueInterface> symTable;
    MyIList<ValueInterface> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIDictionary2<ValueInterface> heap;
    StatementInterface originalProgram;

    int id = 1;
    private static int nextFree = 1;
    public static synchronized int getNextFree(){
        nextFree++;
        return nextFree - 1;
    }
    public int getId(){
        return id;
    }
    public ProgramState(MyIStack<StatementInterface> exeStack,
                        MyIDictionary<String, ValueInterface> symTable,
                        MyIList<ValueInterface> out, MyIDictionary<StringValue, BufferedReader> files, MyIDictionary2<ValueInterface> heap,StatementInterface originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram.deepCopy();
        this.fileTable = files;
        this.heap = heap;
        this.id = getNextFree();
        exeStack.push(this.originalProgram);
    }
    public String toString(){
        return "EXECUTION STACK:\n" + exeStack.toString()  + "\nSYMBOLS TABLE:\n" + symTable.toString() +
                "\nOUTPUT:\n" +  out.toString() + "\nFILE TABLE:\n" + fileTable.toString() + "\nHEAP:\n"+ heap.toString()+"\n";
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

    public MyIDictionary2<ValueInterface> getHeap(){return heap;}

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public void setExecutionStack(MyIStack st) { exeStack = st; }
    public void setSymbolsTable(MyIDictionary<String, ValueInterface> dict) { symTable = dict; }
    public void setOutput(MyIList<ValueInterface> l) { out = l; }

    public void setHeap(MyIDictionary2<ValueInterface> h) {heap = h;}

    public void addStatement(StatementInterface st){
        exeStack.push(st);
    }

    public StatementInterface deleteStatement() throws Exception{
        StatementInterface st = exeStack.pop();
        return st;
    }
    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }
    public ProgramState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        StatementInterface crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}
