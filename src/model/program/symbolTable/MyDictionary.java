package model.program.symbolTable;

import model.exceptions.MyException;

import java.util.*;

public class MyDictionary<K, V> implements MyIDictionary<K,V> {
    private Map<K, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<K,V>();
    }
    @Override
    public String toString() {
        StringBuilder dictString = new StringBuilder();
        for(K elem: dictionary.keySet()){
            dictString.append(elem.toString()).append(" -> ").append(dictionary.get(elem).toString()).append("\n");
        }
        return dictString.toString();
    }
    @Override
    public void add(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public String display() {
        return dictionary.toString();
    }

    @Override
    public V getElementByKey(K key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(K key, V newValue) {
        dictionary.replace(key, newValue);
    }

    @Override
    public List<Map.Entry<K, V>> getAll(){
        return new ArrayList(dictionary.entrySet());
    }

    @Override
    public V remove(Object key) throws MyException {
        try {
            return dictionary.remove(key);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }
    public Collection<V> values() { return dictionary.values(); }

    @Override
    public MyDictionary<K, V> clone() {
        Map<K, V> newDict = new HashMap<>();
        Set<K> keyss = dictionary.keySet();
        for(K key: keyss){
            newDict.put(key, dictionary.get(key));
        }
        MyDictionary<K, V>  neww = new MyDictionary<K, V>();
        neww.setDictionary(newDict);
        return neww;
    }

    @Override
    public Map<K, V> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<K, V> dictionary) {
        this.dictionary = dictionary;
    }
}