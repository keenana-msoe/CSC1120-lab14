/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package test;

import keenana.model.Trie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * tester for the Trie class
 */
public class TrieTest {
    private static Trie t;

    @Test
    void size(){
        List<String> words = new ArrayList<>();
        t = new Trie(words);
        Assertions.assertEquals(0, t.size());
        List<String> words2 = new ArrayList<>();
        words2.add("foo");
        words2.add("bar");
        words2.add("34");
        words2.add("foo");
        t = new Trie(words2);
        Assertions.assertEquals(3, t.size());
    }
    @Test
    void add(){
        t = new Trie(new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> t.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> t.add(""));
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        t = new Trie(words);
        Assertions.assertFalse(t.add("foo"));
        Assertions.assertTrue(t.add("tacos"));
    }
    @Test
    void exactMath(){
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        t = new Trie(words);
        Assertions.assertFalse(t.exactMatch(null));
        Assertions.assertFalse(t.exactMatch(""));
        Assertions.assertFalse(t.exactMatch("43222"));
        Assertions.assertTrue(t.exactMatch("bar"));
    }
    @Test
    void allMatches(){
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        words.add("ball");
        t = new Trie(words);
        Assertions.assertArrayEquals(new String[0], t.allMatches(null));
        String[] w = new String[words.size()];
        words.toArray(w);
        Arrays.sort(w);
        Assertions.assertArrayEquals(w, t.allMatches(""));
        String[] w2 = new String[2];
        w2[0] = "ball";
        w2[1] = "bar";
        Assertions.assertArrayEquals(w2, t.allMatches("b"));
    }
    @Test
    void getBackingClass(){
        t = new Trie(new LinkedList<>());
        Assertions.assertEquals("Keenana.ListMap", t.getBackingClass());
        t = new Trie(new ArrayList<>());
        Assertions.assertEquals("Keenana.ListMap", t.getBackingClass());
    }
}
