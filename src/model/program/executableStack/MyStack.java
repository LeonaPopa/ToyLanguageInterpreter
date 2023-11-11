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
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stack=" + stack +
                '}';
    }

    @Override
    public String display() {
        return stack.toString();
    }

    public List<T> getInOrderTraversal() {
        return inOrderTraversal(stack);
    }

    private List<T> inOrderTraversal(Stack<T> stack) {
        List<T> result = new ArrayList<>();
        for (T element : stack) {
            result.add(element);
        }
        return result;
    }
}
