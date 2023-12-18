package model.program.heap;

import model.exceptions.MyException;
import java.util.*;

public class MyDictionary2<V> implements MyIDictionary2<V>{
    private Map<Integer, V> dictionary;
    Integer nextFree;
    public MyDictionary2() {
        dictionary = new HashMap<Integer,V>();
        nextFree = 1;
    }
    public void setHeap(Map<Integer, V> heap) {
        this.dictionary = heap;
    }
    public Map<Integer, V> getHeap() {
        return dictionary;
    }
    @Override
    public String toString() {
        StringBuilder dictString = new StringBuilder();
        for(Integer elem: dictionary.keySet()){
            dictString.append(elem.toString()).append(" -> ").append(dictionary.get(elem).toString()).append("\n");
        }
        System.out.println(dictString);
        return dictString.toString();
    }
    @Override
    public void add(V value) {
        dictionary.put(nextFree, value);
        nextFree = nextFree + 1;
    }
    public int getNextFree(){return nextFree;}
    @Override
    public String display() {
        return dictionary.toString();
    }

    @Override
    public V getElementByKey(Integer key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isDefined(Integer key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(Integer key, V newValue) {
        dictionary.replace(key, newValue);
    }

    @Override
    public List<Map.Entry<Integer, V>> getAll(){
        return new ArrayList(dictionary.entrySet());
    }

    @Override
    public V remove(Integer key) throws MyException {
        try {
            return dictionary.remove(key);
        }
        catch (Exception ex){
            throw new MyException(ex.getMessage());
        }
    }
    public Collection<V> values() { return dictionary.values(); }
    public void setContent(Map<Integer, V> map) { dictionary = map; }
}
