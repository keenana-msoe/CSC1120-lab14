/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package test;
import keenana.model.BinarySearchTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree Test class
 */
public class BinarySearchTreeTest {
    private static BinarySearchTree ul;

    @Test
    void size(){
        List<String> words = new ArrayList<>();
        ul = new BinarySearchTree(words);
        Assertions.assertEquals(0, ul.size());
        List<String> words2 = new ArrayList<>();
        words2.add("foo");
        words2.add("bar");
        words2.add("34");
        words2.add("foo");
        ul = new BinarySearchTree(words2);
        Assertions.assertEquals(3, ul.size());
    }
    @Test
    void add(){
        ul = new BinarySearchTree(new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> ul.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ul.add(""));
        ArrayList<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        ul = new BinarySearchTree(words);
        Assertions.assertFalse(ul.add("foo"));
        Assertions.assertTrue(ul.add("tacos"));
    }
    @Test
    void exactMath(){
        ArrayList<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        ul = new BinarySearchTree(words);
        Assertions.assertFalse(ul.exactMatch(null));
        Assertions.assertFalse(ul.exactMatch(""));
        Assertions.assertFalse(ul.exactMatch("43222"));
        Assertions.assertTrue(ul.exactMatch("bar"));
    }
    @Test
    void allMatches(){
        String[] check = {"34", "ball", "bar", "foo"};
        ArrayList<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        words.add("ball");
        ul = new BinarySearchTree(words);
        Assertions.assertArrayEquals(check, ul.allMatches(null));
        String[] w = new String[words.size()];
        words.toArray(w);
        Assertions.assertArrayEquals(w, ul.allMatches(""));
        String[] w2 = new String[2];
        w2[0] = "ball";
        w2[1] = "bar";
        Assertions.assertArrayEquals(w2, ul.allMatches("b"));
    }
    @Test
    void getBackingClass(){
        ul = new BinarySearchTree(new ArrayList<>());
        Assertions.assertEquals("java.util.TreeSet", ul.getBackingClass());
        ul = new BinarySearchTree(new ArrayList<>());
        Assertions.assertEquals("java.util.TreeSet", ul.getBackingClass());
    }
}
