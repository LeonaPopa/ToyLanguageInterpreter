package model.program.output;

import java.util.List;

public interface MyIList<T> {
    void add(T item);
    void delete(T item);
    List<T> getAll();
    void size();
    void isEmpty();
}
