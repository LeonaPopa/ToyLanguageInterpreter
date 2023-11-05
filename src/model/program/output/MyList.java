package model.program.output;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList() {
        list = new ArrayList<>();
    }

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public List<T> getAll() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString(){
        return "MyList{" +
                "list=" + list +
                '}';
    }
    @Override
    public String display() {
        return list.toString();
    }

    @Override
    public void delete(T item) {
        list.remove(item);
    }
}
