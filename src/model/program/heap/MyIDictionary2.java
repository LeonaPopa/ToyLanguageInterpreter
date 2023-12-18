package model.program.heap;


import model.exceptions.MyException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MyIDictionary2<V> {
    void add(V value);
    String display();
    V getElementByKey(Integer key);
    boolean isDefined(Integer key);
    void update(Integer key, V newValue);
    List<Map.Entry<Integer, V>> getAll();
    void setHeap(Map<Integer, V> heap);
    Map<Integer, V> getHeap();
    V remove(Integer key) throws MyException;
    Collection<V> values();
    int getNextFree();
    void setContent(Map<Integer, V> map);
}
