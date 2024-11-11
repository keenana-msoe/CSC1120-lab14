/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package keenana.model;

import java.util.*;

/**
 * OrderedList implementation of the AutoCompleter
 * like an unordered list but makes sure that the list is always in order
 */
public class OrderedList implements AutoCompleter {
    private final List<String> items;

    /**
     * The constructor for the ordered list, takes in a list of strings
     * @param s the list of strings for the start of the orderedList
     */
    public OrderedList(List<String> s){
        items = s;
        Set<String> unique = new HashSet<>(items);
        items.clear();
        items.addAll(unique);
        items.sort(null);
    }
    @Override
    public boolean add(String word) {
        if (items == null){
            throw new NullPointerException();
        }
        if (word == null){
            throw new IllegalArgumentException();
        }
        if (word.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (items.contains(word)) {
            return false;
        } else {
            ListIterator<String> it = items.listIterator();
            boolean spot = false;
            while (it.hasNext() && !spot){
                String s = it.next();
                int c = word.compareTo(s);
                if (c < 0){
                    items.add(it.previousIndex(), word);
                    spot = true;
                }
            }
            if (!spot){
                items.add(word);
                spot = true;
            }
            return spot;
        }
    }

    @Override
    public boolean exactMatch(String target) {
        if (items == null){
            throw new NullPointerException();
        }
        if (target == null){
            return false;
        }
        if (target.isEmpty()){
            return false;
        }
        int i = Collections.binarySearch(items, target);
        return i > -1;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public String getBackingClass() {
        return items.getClass().getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        if (items == null){
            throw new NullPointerException();
        }
        if (prefix == null || prefix.isEmpty()){
            return items.toArray(new String[0]);
        }
        List<String> match = new ArrayList<>();
        int i = Collections.binarySearch(items, prefix);
        if (i < 0){
            i = -(i+1);
        }
        for (int j = i; j < items.size() && items.get(j).startsWith(prefix); j++){
            match.add(items.get(j));
        }
        return match.toArray(new String[0]);
    }
}
