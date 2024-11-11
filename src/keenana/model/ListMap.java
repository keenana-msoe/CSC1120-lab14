/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package keenana.model;

import java.util.*;
import java.util.AbstractMap;
/**
 * ListMap is the list map class used in the Trie Implementation
 * @param <K> They key generic
 * @param <V> the value generic
 */
public class ListMap<K, V> implements Map<K, V> {
    private final List<AbstractMap.SimpleEntry<K, V>> entries;

    /**
     * ListMap constructor which sets the listMap to an ArrayList
     */
    public ListMap(){
        entries = new ArrayList<>();
    }
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(entries);
    }
    @Override
    public void clear() {
        entries.clear();
    }
    @Override
    public V remove(Object key) {
        K k = (K) key;
        for (AbstractMap.SimpleEntry s : entries){
            K compare = (K) s.getKey();
            if (k.equals(compare)){
                V data = (V) s.getValue();
                entries.remove(s);
                return data;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V retval = null;
        if (containsKey(key)){
            for (AbstractMap.SimpleEntry s : entries){
                K k = (K) s.getKey();
                if (k.equals(key)){
                    retval = (V) s.setValue(value);
                }
            }
        } else {
            entries.add(new AbstractMap.SimpleEntry<>(key, value));
        }
        return retval;
    }

    @Override
    public V get(Object key) {
        K k = (K) key;
        V retVal = null;
        for (int i = 0; i < entries.size(); i++){
            K compare = (K) entries.get(i).getKey();
            if (k.equals(compare)){
                retVal = entries.get(i).getValue();
            }
        }
        return retVal;
    }

    @Override
    public boolean containsKey(Object key) {
        K k = (K) key;
        boolean found = false;
        for (int i = 0; i < entries.size() && !found; i++){
            K compare = (K) entries.get(i).getKey();
            if (k.equals(compare)){
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public int size() {
        return entries.size();
    }



    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

}
