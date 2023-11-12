package view;

import controller.Controller;
import model.ProgramState;
import model.expressions.classes.ArithmeticExpression;
import model.expressions.classes.RelationalExpression;
import model.expressions.classes.ValueExpression;
import model.expressions.classes.VariableExpression;
import model.program.executableStack.MyIStack;
import model.program.executableStack.MyStack;
import model.program.output.MyIList;
import model.program.output.MyList;
import model.program.symbolTable.MyDictionary;
import model.program.symbolTable.MyIDictionary;
import model.statements.Classes.*;
import model.statements.StatementInterface;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.ValueInterface;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {

        MyIStack<StatementInterface> stk = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl = new MyDictionary<StringValue, BufferedReader>() {
        };

        StatementInterface ex1 = new ComposedStatement(new VariableDeclarationStatement("a", new IntType()),
                new ComposedStatement(new VariableDeclarationStatement("b", new IntType()),
                        new ComposedStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(2))),
                                new ComposedStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(3))),
                                        new IfStatement(new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), "<"),
                                                new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b")))))));
        ProgramState prg1 = new ProgramState(stk, symTbl, out, fileTbl, ex1);
        IRepository repo1 = new Repository("C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f1.txt");
        Controller ctr1 = new Controller(repo1);

        MyIStack<StatementInterface> stk2 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl2 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out2 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl2 = new MyDictionary<StringValue, BufferedReader>() {
        };

        StatementInterface ex2 = new ComposedStatement(new VariableDeclarationStatement("a", new IntType()),
                new ComposedStatement(new VariableDeclarationStatement("b", new IntType()),
                        new ComposedStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
                                new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new ComposedStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"),
                                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState prg2 = new ProgramState(stk2, symTbl2, out2, fileTbl2, ex2);
        IRepository repo2 = new Repository("C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f2.txt");
        Controller ctr2 = new Controller(repo2);

        MyIStack<StatementInterface> stk3 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl3 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out3 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl3 = new MyDictionary<StringValue, BufferedReader>();

        StatementInterface ex3 = new ComposedStatement(new VariableDeclarationStatement("a", new BoolType()),
                new ComposedStatement(new VariableDeclarationStatement("v", new IntType()),
                        new ComposedStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new ComposedStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v",
                                        new ValueExpression(new IntValue(2))), new AssignmentStatement("v",
                                        new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        ProgramState prg3 = new ProgramState(stk3, symTbl3, out3, fileTbl3, ex3);
        IRepository repo3 = new Repository("C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f3.txt");
        Controller ctr3 = new Controller(repo3);

        MyIStack<StatementInterface> stk4 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl4 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out4 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl4 = new MyDictionary<StringValue, BufferedReader>();

        StatementInterface ex4 = new ComposedStatement(new VariableDeclarationStatement("varf", new StringType()),
                new ComposedStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/pm.txt"))),
                        new ComposedStatement(new openRFile(new VariableExpression("varf")),
                                new ComposedStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new ComposedStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new ComposedStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new ComposedStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new ComposedStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFile(new VariableExpression("varf"))))))))));
        ProgramState prg4 = new ProgramState(stk4, symTbl4, out4, fileTbl4, ex4);
        IRepository repo4 = new Repository("C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f4.txt");
        Controller ctr4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new exitCommand("0", "exit"));
        menu.addCommand(new runCommand("1", ex1.toString(), ctr1, prg1));
        menu.addCommand(new runCommand("2", ex2.toString(), ctr2, prg2));
        menu.addCommand(new runCommand("3", ex3.toString(), ctr3, prg3));
        menu.addCommand(new runCommand("4", ex4.toString(), ctr4, prg4));
        menu.show();
    }
}
