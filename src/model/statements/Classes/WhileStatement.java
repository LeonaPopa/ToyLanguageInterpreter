package model.statements.Classes;

import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.program.executableStack.MyIStack;
import model.program.heap.MyIDictionary2;
import model.program.symbolTable.MyIDictionary;
import model.statements.StatementInterface;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.ValueInterface;

public class WhileStatement implements StatementInterface {
    ExpressionInterface expr;
    StatementInterface stmt;
    public WhileStatement(ExpressionInterface exp, StatementInterface stmt) { this.expr = exp; this.stmt = stmt; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIDictionary2<ValueInterface> heapTable = state.getHeap();
        MyIStack<StatementInterface> exeStack = state.getExeStack();

        ValueInterface expressionValue = expr.eval(symTable, heapTable);
        if (expressionValue.getType().equals(new BoolType())){
            BoolValue boolVal = (BoolValue)expressionValue;
            if (!boolVal.getVal()){
                return state;
            }
            else {
                exeStack.push(this);
                exeStack.push(stmt);
                return null;
            }
        }
        else throw new MyException("Expression " + expr + " must be a boolValue!");
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(expr.deepCopy(), stmt.deepCopy());
    }

    @Override
    public String toString() { return "while (" + expr + ") do " + stmt; }
}