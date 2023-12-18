package view;

import controller.Controller;
import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.ExpressionInterface;
import model.expressions.classes.*;
import model.program.executableStack.MyIStack;
import model.program.executableStack.MyStack;
import model.program.heap.MyDictionary2;
import model.program.heap.MyIDictionary2;
import model.program.output.MyIList;
import model.program.output.MyList;
import model.program.symbolTable.MyDictionary;
import model.program.symbolTable.MyIDictionary;
import model.statements.Classes.*;
import model.statements.StatementInterface;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.ValueInterface;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Interpreter {
    public static void main(String[] args) {

        MyIStack<StatementInterface> stk = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap = new MyDictionary2<ValueInterface>();


        StatementInterface ex1 = new ComposedStatement(new VariableDeclarationStatement("v", new IntType()),
                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new
                        VariableExpression("v"))));
        ProgramState prg1 = new ProgramState(stk, symTbl, out, fileTbl,heap, ex1);
        IRepository repo1 = new Repository(prg1,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f1.txt");
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
        ProgramState prg2 = new ProgramState(stk2, symTbl2, out2, fileTbl2,heap, ex2);
        IRepository repo2 = new Repository(prg2,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f2.txt");
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
        ProgramState prg3 = new ProgramState(stk3, symTbl3, out3, fileTbl3, heap,ex3);
        IRepository repo3 = new Repository(prg3,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f3.txt");
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
        ProgramState prg4 = new ProgramState(stk4, symTbl4, out4, fileTbl4,heap, ex4);
        IRepository repo4 = new Repository(prg1,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f4.txt");
        Controller ctr4 = new Controller(repo4);



        MyIStack<StatementInterface> stk5 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl5 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out5= new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl5 = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap5 = new MyDictionary2<ValueInterface>();

        StatementInterface ex5 = new ComposedStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new ComposedStatement(new NewStatement("a", new VariableExpression("v")),
                                        new ComposedStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        ProgramState program5 = new ProgramState(stk5, symTbl5, out5, fileTbl5,heap5, ex5);
        Repository repo5 = new Repository( program5,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f5.txt");
        Controller ctrl5 = new Controller(repo5);
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)


        MyIStack<StatementInterface> stk6 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl6 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out6 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl6 = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap6 = new MyDictionary2<ValueInterface>();

        StatementInterface ex6 = new ComposedStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new ComposedStatement(new NewStatement("a", new VariableExpression("v")),
                                        new ComposedStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))), new PrintStatement(new ArithmeticExpression('+',new ReadHeap(new ReadHeap(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        ProgramState prg6 = new ProgramState(stk6, symTbl6, out6, fileTbl6, heap6, ex6);
        IRepository repo6 = new Repository(prg6,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f6.txt");
        Controller ctr6 = new Controller(repo6);
        //Ref int v;new(v,20);Ref Ref int a; new(a, v);new(v,30);print(v,30);print(rH(rH(a))
        MyIStack<StatementInterface> stk7 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl7 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out7 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl7 = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap7 = new MyDictionary2<ValueInterface>();

        StatementInterface ex7 = new ComposedStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                new ComposedStatement(new WriteHeap("v", new ValueExpression(new IntValue(30))), new PrintStatement(new ArithmeticExpression('+', new ReadHeap(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        ProgramState prg7 = new ProgramState(stk7, symTbl7, out7, fileTbl7, heap7, ex7);
        IRepository repo7 = new Repository(prg7,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f7.txt");
        Controller ctr7 = new Controller(repo7);
        //Ref int v; new(v,20);print(rH(v));wH(v,30);print(rH(v)+5)
        MyIStack<StatementInterface> stk8 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl8= new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out8 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl8 = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap8 = new MyDictionary2<ValueInterface>();

        StatementInterface ex8 =  new ComposedStatement(new VariableDeclarationStatement("v", new IntType()),
                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new ComposedStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                new ComposedStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",
                                        new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

        ProgramState prg8 = new ProgramState(stk8, symTbl8, out8, fileTbl8, heap8, ex8);
        IRepository repo8 = new Repository(prg8,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f8.txt");
        Controller ctr8 = new Controller(repo8);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        MyIStack<StatementInterface> stk9 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl9= new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out9 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl9 = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap9 = new MyDictionary2<ValueInterface>();

        StatementInterface ex9 =  new ComposedStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new ComposedStatement(new NewStatement("a", new VariableExpression("v")),
                                        new ComposedStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                        new ComposedStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))), new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a"))))))))));

        ProgramState prg9 = new ProgramState(stk9, symTbl9, out9, fileTbl9, heap9, ex9);
        IRepository repo9 = new Repository(prg9,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f9.txt");
        Controller ctr9 = new Controller(repo9);


        MyIStack<StatementInterface> stk10 = new MyStack<StatementInterface>();
        MyIDictionary<String, ValueInterface> symTbl10 = new MyDictionary<String, ValueInterface>();
        MyIList<ValueInterface> out10 = new MyList<ValueInterface>();
        MyIDictionary<StringValue, BufferedReader> fileTbl10 = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary2<ValueInterface> heap10 = new MyDictionary2<ValueInterface>();

        StatementInterface ex10 = new ComposedStatement(new VariableDeclarationStatement("v", new IntType()), new ComposedStatement(new VariableDeclarationStatement("a", new RefType(new IntType())),
                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))), new ComposedStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                        new ComposedStatement(new ForkStatement(new ComposedStatement(new WriteHeap("a", new ValueExpression(new IntValue(30))),
                                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                        new ComposedStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a"))))))),
                                new ComposedStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a")))))
                ))));
        ProgramState prg10 = new ProgramState(stk10, symTbl10, out10, fileTbl10, heap10, ex10);
        IRepository repo10 = new Repository(prg10,"C:/Users/Leona/OneDrive/Documente/GitHub/ToyLanguageInterpreter/src/model/files/f10.txt");
        Controller ctr10 = new Controller(repo10);


        TextMenu menu = new TextMenu();
        menu.addCommand(new exitCommand("0", "exit"));
        menu.addCommand(new runCommand("1", ex1.toString(), ctr1, prg1));
        menu.addCommand(new runCommand("2", ex2.toString(), ctr2, prg2));
        menu.addCommand(new runCommand("3", ex3.toString(), ctr3, prg3));
        menu.addCommand(new runCommand("4", ex4.toString(), ctr4, prg4));
        menu.addCommand(new runCommand("5", ex5.toString(), ctrl5, program5));
        menu.addCommand(new runCommand("6", ex6.toString(), ctr6, prg6));
        menu.addCommand(new runCommand("7", ex7.toString(), ctr7, prg7));
        menu.addCommand(new runCommand("8", ex8.toString(), ctr8, prg8));
        menu.addCommand(new runCommand("9", ex9.toString(), ctr9, prg9));
        menu.addCommand(new runCommand("10", ex10.toString(), ctr10, prg10));
        menu.show();
    }
}