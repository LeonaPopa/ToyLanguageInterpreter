package model.program.symbolTable;

import java.util.HashMap;
import java.util.Map;

class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V get(K key) {
        return dictionary.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        return dictionary.containsKey(key);
    }
}