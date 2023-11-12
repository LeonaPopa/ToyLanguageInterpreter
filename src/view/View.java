package view;

import com.sun.jdi.Value;
import controller.Controller;
import model.ProgramState;
import model.exceptions.MyException;
import model.expressions.classes.ArithmeticExpression;
import model.expressions.classes.LogicalExpression;
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
import model.values.BoolValue;
import model.values.IntValue;
import model.values.ValueInterface;
import repository.IRepository;
import repository.Repository;

import javax.management.ValueExp;
import java.util.Scanner;

public class View {
    Controller ctrl;
    static Scanner scanner = new Scanner(System.in);

    public View(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public Controller getCtrl() {
        return ctrl;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static void printOptions()
    {
        System.out.println("Here are the programs");
        System.out.println("1. int v; v=2;Print(v)");
        System.out.println("2. int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("4. Exit");
    }


    public static void main(String[] args) {


        while(true)
        {
            printOptions();
            System.out.println("enter a number ");
            int choice = Integer.parseInt(scanner.next());
            StatementInterface program = null;
            if(choice == 1){
                program = new ComposedStatement(new VariableDeclarationStatement("v", new IntType()),
                        new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))));
            }
            if(choice == 2){
                program = new ComposedStatement( new VariableDeclarationStatement("a",new IntType()),
                        new ComposedStatement(new VariableDeclarationStatement("b",new IntType()),
                                new ComposedStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                        new ComposedStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
            }
            if(choice == 3){
                program = new ComposedStatement(new VariableDeclarationStatement("a",new BoolType()),
                        new ComposedStatement(new VariableDeclarationStatement("v", new IntType()),
                                new ComposedStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                        new ComposedStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",
                                                new ValueExpression(new IntValue(2))), new AssignmentStatement("v",
                                                new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
            }
            if(choice == 4)
                break;
            MyIStack<StatementInterface> stk = new MyStack<StatementInterface>();
            MyIDictionary<String, ValueInterface> symTbl = new MyDictionary<String, ValueInterface>();
            MyIList<ValueInterface> out = new MyList<ValueInterface>();
            //ProgramState state = new ProgramState(stk, symTbl, out, program);
            //IRepository repo = new Repository();
            //repo.addProgram(state);
            //Controller ctr = new Controller(repo);
            //View ui = new View(ctr);
            //try {
                //ui.getCtrl().allStep();
            //} catch (MyException e) {
                //System.out.println(e.getMessage());
            //}
        }

    }
}
