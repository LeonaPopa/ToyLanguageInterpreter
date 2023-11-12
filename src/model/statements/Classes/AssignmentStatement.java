package model.statements.Classes;

import model.exceptions.MyException;
import model.expressions.Expressioninterface;
import model.program.executableStack.MyIStack;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.ProgramState;
import model.types.TypeInterface;
import model.values.ValueInterface;

public class AssignmentStatement implements StatementInterface {
    String id;

    Expressioninterface exp;

    public AssignmentStatement(String id, Expressioninterface exp) {
        this.id = id;
        this.exp = exp;
    }
    public String toString(){
        return id + "=" + exp.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTbl = state.getSymTable();
        if(symTbl.isDefined(id)){
            ValueInterface val = exp.eval(symTbl);
            TypeInterface typId = symTbl.getElementByKey(id).getType();
            if(val.getType().equals(typId))
                symTbl.update(id, val);
            else throw new MyException("declared type of variable " + id + " and type of the assigned expression does not match");
        }
        else throw new MyException("the used variable " + id + " was not declared before");
        return state;
    }
}
