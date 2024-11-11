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
 * this is the binary search tree implemeentation of the AutoCOmpleter
 */
public class BinarySearchTree implements AutoCompleter {
    private final TreeSet<String> items;

    /**
     * constructor for the BinarySearchTree which takes in a TreeSet
     * @param s the treeset that is wished to be the start of the BinaryTreeSet
     */
    public BinarySearchTree(TreeSet<String> s){
        items = s;
    }
    public BinarySearchTree(List<String> list) {
        items = new TreeSet<>();
        items.addAll(list);
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
        return items.add(word);
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
        return items.size();
    }

    @Override
    public String getBackingClass() {
        return "java.util.TreeSet";
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
        String s = items.ceiling(prefix);
        Iterator<String> it = items.tailSet(s).iterator();
        boolean allAdded = false;
        while(it.hasNext() && !allAdded){
            String check = it.next();
            if (check.startsWith(prefix)){
                match.add(check);
            } else {
                allAdded = true;
            }
        }
        return match.toArray(new String[0]);
    }
}
