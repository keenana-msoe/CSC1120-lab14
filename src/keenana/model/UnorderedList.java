/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package keenana.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * implementation of the unordered list.
 */
public class UnorderedList implements AutoCompleter {
    private final List<String> items;

    /**
     * constructor for the Unordered List
     * @param x the list to be passed into the unordered list
     */
    public UnorderedList(List<String> x){
        items = x;
        Set<String> unique = new HashSet<>(items);
        items.clear();
        items.addAll(unique);
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
        if (exactMatch(word)){
            return false;
        } else {
            items.add(word);
            return true;
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
        return items.contains(target);
    }

    @Override
    public int size() {
        if (items == null){
            throw new NullPointerException();
        }
        for (String item : items){
            if (item == null){
                throw new IllegalArgumentException();
            }
        }
        return items.size();
    }

    @Override
    public String getBackingClass() {
        if (items == null){
            throw new NullPointerException();
        }
        return items.getClass().getName();
    }

    @Override
    public String[] allMatches(String prefix) {
        if (items == null){
            throw new NullPointerException();
        }
        ArrayList<String> match = new ArrayList<>();
        String[] matches;
        if (prefix == null){
            return new String[0];
        }
        if (prefix.isEmpty()){
            matches = new String[size()];
            items.toArray(matches);
        } else {
            for (String s : items){
                if (s.startsWith(prefix)){
                    match.add(s);
                }
            }
            matches = new String[match.size()];
            match.toArray(matches);
        }
        return matches;
    }
}
