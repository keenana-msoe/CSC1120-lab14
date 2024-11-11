/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package test;

import keenana.model.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * test class for the HashTables
 */
public class HashTableTest {
    private static HashTable hT;

    @Test
    void size(){
        List<String> words = new ArrayList<>();
        hT = new HashTable(words);
        Assertions.assertEquals(0, hT.size());
        List<String> words2 = new ArrayList<>();
        words2.add("foo");
        words2.add("bar");
        words2.add("34");
        words2.add("foo");
        hT = new HashTable(words2);
        Assertions.assertEquals(3, hT.size());
    }
    @Test
    void add(){
        hT = new HashTable(new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> hT.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> hT.add(""));
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        hT = new HashTable(words);
        Assertions.assertFalse(hT.add("foo"));
        Assertions.assertTrue(hT.add("tacos"));
    }
    @Test
    void exactMath(){
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        hT = new HashTable(words);
        Assertions.assertFalse(hT.exactMatch(null));
        Assertions.assertFalse(hT.exactMatch(""));
        Assertions.assertFalse(hT.exactMatch("43222"));
        Assertions.assertTrue(hT.exactMatch("bar"));
    }
    @Test
    void allMatches(){
        List<String> words = new ArrayList<>();
        words.add("34");
        words.add("ball");
        words.add("bar");
        words.add("foo");
        hT = new HashTable(words);
        String[] w = new String[words.size()];
        words.toArray(w);
        Assertions.assertArrayEquals(w, hT.allMatches(null));
        Assertions.assertArrayEquals(w, hT.allMatches(""));
        String[] w2 = new String[2];
        w2[0] = "ball";
        w2[1] = "bar";
        Assertions.assertArrayEquals(w2, hT.allMatches("b"));
    }
    @Test
    void getBackingClass(){
        hT = new HashTable(new LinkedList<>());
        Assertions.assertEquals("java.util.HashSet", hT.getBackingClass());
        hT = new HashTable(new ArrayList<>());
        Assertions.assertEquals("java.util.HashSet", hT.getBackingClass());
    }
}
