package model.program.symbolTable;

import java.util.HashMap;

public interface MyIDictionary<K, V> {
    void add(K key, V value);
    String display();
    V getElementByKey(K key);
    boolean isDefined(K key);
    void update(K key, V newValue);
}