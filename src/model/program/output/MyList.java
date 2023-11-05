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
    public void delete(T item) {
        list.remove(item);
    }
}
