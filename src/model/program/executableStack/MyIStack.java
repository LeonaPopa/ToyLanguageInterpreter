package model.program.executableStack;

import java.util.List;
import java.util.Stack;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    Stack<T> getAll();
    int size();
    boolean isEmpty();
    String display();
    List<T> getInOrderTraversal();
}
