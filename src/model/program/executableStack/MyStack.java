package model.program.executableStack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;
    public MyStack() {
        stack = new Stack<T>();
    }
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.pop();
    }
    @Override
    public void push(T v) {
        stack.push(v);
    }
    @Override
    public Stack<T> getAll() {
        return stack;
    }
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder stringStack = new StringBuilder();
        List<T> stackList = new ArrayList<>(stack);
        for(int i = stackList.size()-1; i>=0; i--)
        {
            String currentElem = stackList.get(i).toString();
            stringStack.append(currentElem).append("\n");
        }
        return stringStack.toString();
    }
    @Override
    public String display() {
        return stack.toString();
    }
}
