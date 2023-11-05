package model.program.symbolTable;

import java.util.HashMap;

interface MyIDictionary<K, V> {
    void put(K key, V value);
    V get(K key);
    HashMap<K,V> getAll();
    void size();
    void isEmpty();
    boolean containsKey(K key);

}