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

/**
 * HashSet implementation of the AutoCOmpleter interface
 */
public class HashTable implements AutoCompleter {
    private final HashSet<String> items;

    /**
     * constructor for the HashTable class
     * @param list the list of original words that are added
     */
    public HashTable(List<String> list){
        items = new HashSet<>();
        items.addAll(list);
    }
    @Override
    public boolean add(String word) {
        if (word == null || word.isEmpty()){
            throw new IllegalArgumentException();
        }
        return items.add(word);
    }

    @Override
    public boolean exactMatch(String target) {
        if (target == null || target.isEmpty()){
            return false;
        }
        return items.contains(target);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public String getBackingClass() {
        return "java.util.HashSet";
    }

    @Override
    public String[] allMatches(String prefix) {
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
