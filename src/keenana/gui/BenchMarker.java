/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package keenana.gui;

import keenana.model.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Benchmarking class for the different data tructures
 */
public class BenchMarker {
    private static final int trial1 = 500;
    private static final int trial2 = 1_000;
    private static final int trial3 = 10_000;
    private static final int trial4 = 25_000;
    private static final int trial5 = 50_000;
    private static final int trial6 = 100_000;
    private static final int trial7 = 250_000;
    private static final int trial8 = 500_000;
    private static final int trial9 = 750_000;
    private static final int trial10 = 1_000_000;
    private static OrderedList ol;
    private static BinarySearchTree t;
    public static void main(String[] args) {
        System.out.println("Benchmarking ... ");
        System.out.println("Trial 1 (500)");
        System.out.println();
        benchmark(trial1);
        System.out.println();

        System.out.println("Trial 2 (1,000)");
        System.out.println();
        benchmark(trial2);
        System.out.println();

        System.out.println("Trial 3 (10,000)");
        System.out.println();
        benchmark(trial3);
        System.out.println();

        System.out.println("Trial 4 (25,000)");
        System.out.println();
        benchmark(trial4);
        System.out.println();

        System.out.println("Trial 5 (50,000)");
        System.out.println();
        benchmark(trial5);
        System.out.println();

        System.out.println("Trial 6 (100,000)");
        System.out.println();
        benchmark(trial6);
        System.out.println();

        System.out.println("Trial 7 (250,000)");
        System.out.println();
        benchmark(trial7);
        System.out.println();

        System.out.println("Trial 8 (500,000)");
        System.out.println();
        benchmark(trial8);
        System.out.println();

        System.out.println("Trial 9 (750,000)");
        System.out.println();
        benchmark(trial9);
        System.out.println();

        System.out.println("Trial 10 (1,000,000)");
        System.out.println();
        benchmark(trial7);
        System.out.println();
    }
    private static void benchmark(int size){
        List<String> words1 = getWords(size);
        populate(words1);
        System.out.println("add");
        String target = AutoCompleter.getString(7);
        long s;
        long e;

        s = System.nanoTime();
        ol.add(target);
        e = System.nanoTime();
        System.out.println("OrderedList: " +calcTime(s, e));
        s = System.nanoTime();
        t.add(target);
        e = System.nanoTime();
        System.out.println("BinarySearchTree: " +calcTime(s, e));
        System.out.println();

        System.out.println("exactMatch");
        target = AutoCompleter.getString(8);
        s = System.nanoTime();
        ol.exactMatch(target);
        e = System.nanoTime();
        System.out.println("OrderedList: " +calcTime(s, e));
        s = System.nanoTime();
        t.exactMatch(target);
        e = System.nanoTime();
        System.out.println("BinarySearchTree: " +calcTime(s, e));
        System.out.println();

        System.out.println("allMatches");
        target = AutoCompleter.getString(3);
        s = System.nanoTime();
        ol.allMatches(target);
        e = System.nanoTime();
        System.out.println("OrderedList: " +calcTime(s, e));
        s = System.nanoTime();
        t.allMatches(target);
        e = System.nanoTime();
        System.out.println("BinarySearchTree: " +calcTime(s, e));
        System.out.println();
    }
    private static List<String> getWords(int size){
        List<String> words = new LinkedList<>();
        for (int i = 0; i < size; i++){
            words.add(AutoCompleter.getString((int) (Math.random() * 9) + 1));
        }
        return words;
    }
    private static void populate(List<String> words){
        ol = new OrderedList(words);
        t = new BinarySearchTree(words);
    }
    private static String calcTime(long start, long end){
        return AutoCompleter.format(end - start);
    }
}
