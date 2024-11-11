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
 * Trie data structure that implements the AutoCompleter interface
 */
public class Trie implements AutoCompleter {
    private static class Node {
        private boolean endsWord;
        private final Map<Character, Node> edges;

        Node(){
            this.endsWord = false;
            this.edges = new ListMap<>();
        }
        boolean isEndsWord(){
            return endsWord;
        }
        void setEndsWord(){
            endsWord = true;
        }
        Map<Character, Node> getEdges(){
            return edges;
        }
    }
    private Node head;
    private int size;

    /**
     * constructor for the trie
     * @param words initial words added
     */
    public Trie(List<String> words){
        head = new Node();
        size = 0;
        for (String s : words){
            add(s);
        }
    }
    @Override
    public boolean add(String word) {
        if (word == null || word.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (exactMatch(word)){
            return false;
        }
        Node walker = head;
        for (int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);
            Map<Character, Node> map = walker.getEdges();
            if (map.containsKey(c)){
                walker = map.get(c);
            } else {
                map.put(c, new Node());
                walker = map.get(c);
            }
        }
        walker.setEndsWord();
        size++;
        return true;
    }

    @Override
    public boolean exactMatch(String target) {
        if (target == null || target.isEmpty()){
            return false;
        }
        Node walker = head;
        for (int i = 0; i < target.length(); i++){
            Character c = target.charAt(i);
            Map<Character, Node> map = walker.getEdges();
            if (map.containsKey(c)){
                walker = map.get(c);
            } else {
                return false;
            }
        }
        return walker.isEndsWord();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String getBackingClass() {
        return "Keenana.ListMap";
    }

    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null){
            return new String[0];
        }
        ArrayList<String> words = new ArrayList<>();
        Node walker = head;
        if (prefix.isEmpty()){
            allMatches(walker, words, "");
            String[] match = new String[words.size()];
            return words.toArray(match);
        }
        for (int i = 0; i < prefix.length(); i++){
            Character c = prefix.charAt(i);
            Map<Character, Node> map = walker.getEdges();
            if (map.containsKey(c)){
                walker = map.get(c);
            } else {
                return new String[0];
            }
        }
        allMatches(walker, words, prefix);
        String[] match = new String[words.size()];
        return words.toArray(match);
    }

    private void allMatches(Node node, List<String> words, String prefix){
        if (node.isEndsWord()){
            words.add(prefix);
        }
        Character l = '0';
        while (l <= 'z'){
            Node walker = node.getEdges().get(l);
            if (walker != null){
                allMatches(walker, words, prefix+l);
            }
            l++;
        }
    }
}
