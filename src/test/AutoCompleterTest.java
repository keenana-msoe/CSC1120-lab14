/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package test;

import keenana.model.AutoCompleter;
import keenana.model.UnorderedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests all of the methods from the created UnorderedList
 */
class AutoCompleterTest {
    private static UnorderedList ul;

    @Test
    void size(){
        List<String> words = new ArrayList<>();
        ul = new UnorderedList(words);
        Assertions.assertEquals(0, ul.size());
        List<String> words2 = new ArrayList<>();
        words2.add("foo");
        words2.add("bar");
        words2.add("34");
        words2.add("foo");
        ul = new UnorderedList(words2);
        Assertions.assertEquals(3, ul.size());
    }
    @Test
    void add(){
        ul = new UnorderedList(new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> ul.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ul.add(""));
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        ul = new UnorderedList(words);
        Assertions.assertFalse(ul.add("foo"));
        Assertions.assertTrue(ul.add("tacos"));
    }
    @Test
    void exactMath(){
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        ul = new UnorderedList(words);
        Assertions.assertFalse(ul.exactMatch(null));
        Assertions.assertFalse(ul.exactMatch(""));
        Assertions.assertFalse(ul.exactMatch("43222"));
        Assertions.assertTrue(ul.exactMatch("bar"));
    }
    @Test
    void allMatches(){
        List<String> words = new ArrayList<>();
        words.add("foo");
        words.add("bar");
        words.add("34");
        words.add("ball");
        ul = new UnorderedList(words);
        Assertions.assertArrayEquals(new String[0], ul.allMatches(null));
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
        ul = new UnorderedList(new LinkedList<>());
        Assertions.assertEquals("java.util.LinkedList", ul.getBackingClass());
        ul = new UnorderedList(new ArrayList<>());
        Assertions.assertEquals("java.util.ArrayList", ul.getBackingClass());
    }
    @Test
    void format(){
        String s1 = "37 nanosecond(s)";
        String s2 = "37 microsecond(s)";
        String s3 = "37 milisecond(s)";
        String s4 = "37 second(s)";
        String s5 = "6 minute(s) 10 second(s)";
        String s0 = "ERROR";
        String s6 = "1 hour(s) 1 minute(s) 40 second(s)";
        final long sValue = 37;
        long ns = sValue;
        final int scale1 = 1000;
        final int scale2 = 10;
        assertEquals(s0, AutoCompleter.format(-1));
        assertEquals(s1, AutoCompleter.format(ns));
        ns *= scale1;
        assertEquals(s2, AutoCompleter.format(ns));
        ns *= scale1;
        assertEquals(s3, AutoCompleter.format(ns));
        ns *= scale1;
        assertEquals(s4, AutoCompleter.format(ns));
        ns *= scale2;
        assertEquals(s5, AutoCompleter.format(ns));
        ns *= scale2;
        assertEquals(s6, AutoCompleter.format(ns));
        assertEquals("42 minute(s) 55.3 second(s)", AutoCompleter.format(2575300000000L));
    }
}

/*
  Discussion: What method did you find most difficult to test? Why?

  The hardest method ot test for was the allMatches method as it was
  very tedius making the arrays and then needing to check those arrays for the correct values
  I also had no clue which way the arrays were going to be spit out

*/
