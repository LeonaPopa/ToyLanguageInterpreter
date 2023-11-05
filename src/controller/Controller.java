package controller;

import model.ProgramState;
import model.exceptions.MyException;
import model.program.executableStack.MyIStack;
import model.statements.StatementInterface;
import repository.IRepository;

public class Controller {
    public IRepository repo;
    boolean displayFlag;
    public Controller(IRepository repo) {
        this.repo = repo;
        displayFlag = true;
    }
    public boolean isDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public ProgramState oneStep(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        if(stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        StatementInterface crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public  void allStep() throws MyException {
        ProgramState prg = repo.getCrtPrg();
        System.out.println(prg);
        while(!prg.getExeStack().isEmpty())
        {
            oneStep(prg);
            if(displayFlag)
                System.out.println(prg);
        }
        System.out.println(prg);
    }
}
