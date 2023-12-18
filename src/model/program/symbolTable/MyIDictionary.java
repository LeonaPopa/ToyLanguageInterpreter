package model.program.symbolTable;

import model.exceptions.MyException;
import model.values.ValueInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MyIDictionary<K, V> {
    void add(K key, V value);
    String display();
    V getElementByKey(K key);
    boolean isDefined(K key);
    void update(K key, V newValue);
    List<Map.Entry<K, V>> getAll();
    V remove(Object key) throws MyException;
    Collection<V> values();
    MyDictionary<K, V> clone();
    Map<K, V> getDictionary();
}