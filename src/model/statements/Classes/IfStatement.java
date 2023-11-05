package model.statements.Classes;

import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.executableStack.MyIStack;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.ProgramState;
import model.types.BoolType;
import model.values.ValueInterface;

public class IfStatement implements StatementInterface {
    Expressioninterface exp;
    StatementInterface thenS;
    StatementInterface elseS;

    public IfStatement(Expressioninterface exp, StatementInterface thenS, StatementInterface elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }
    @Override
    public String toString(){
        return "(IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTbl = state.getSymTable();
        MyIStack<StatementInterface> stk = state.getExeStack();
        ValueInterface cond = exp.eval(symTbl);

        if(!cond.getType().equals(new BoolType()))
            throw new MyException("Conditional expression is not a boolean");
        else{
            if(Boolean.parseBoolean(cond.toString()) == Boolean.TRUE)
                stk.push(thenS);
            else
                stk.push(elseS);
        }
        return state;
    }
}
