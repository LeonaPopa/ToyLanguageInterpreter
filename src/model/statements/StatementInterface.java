package model.statements;

import model.exceptions.MyException;
import model.ProgramState;

public interface StatementInterface {
  abstract ProgramState execute(ProgramState state) throws MyException;

}
