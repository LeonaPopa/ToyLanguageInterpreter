package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.BoolType;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.ValueInterface;

public class VariableDeclarationStatement implements StatementInterface {
    String name;
    TypeInterface typ;

    public VariableDeclarationStatement(String name, TypeInterface typ) {
        this.name = name;
        this.typ = typ;
    }

    public String toString(){
        return typ.toString() + " " + name + ";";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        MyIDictionary<String, ValueInterface> symTbl = state.getSymTable();
        if(symTbl.isDefined(name))
            throw new MyException("Variable is already declared");
        else{
            if(typ.equals(new IntType()))
                symTbl.add(name, new IntValue(0));
            if(typ.equals(new BoolType()))
                symTbl.add(name, new BoolValue(false));
        }
        return state;
    }
}
