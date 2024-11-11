/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package keenana.model;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * auto cpmpleter interface to stub ut methods for an Unordered List.
 */
public interface AutoCompleter {
    /**
     * Adds the word to the backing data structure
     * @param word the word to be added
     * @return if the word is added successfully
     */
    boolean add(String word);

    /**
     * Checks to see if the target word has an exact match already in the list
     * @param target the word that is checked
     * @return if the target has an exact match
     */
    boolean exactMatch(String target);

    /**
     * returns the number of items currently in the backing data structure
     * @return the number of items
     */
    int size();

    /**
     * returns the backing class of the data structure
     * @return the backing data structure's name
     */
    String getBackingClass();

    /**
     * returns all of the items in the array that match a certain prefix
     * @param prefix the target prefix
     * @return an array of all of the matching prefixes
     */
    String[] allMatches(String prefix);

    /**
     * formats the total of nanoseconds passed in to a human readable version
     * @param nanoseconds the amount of nanoseconds
     * @return a human readable version of the amount of nanoseconds
     */
    static String format(long nanoseconds) {
        if (nanoseconds < 0){
            return "ERROR";
        }
        double us = (double) nanoseconds / 1000;
        double ms = us / 1000;
        double s = ms / 1000;
        double min = s / 60;
        double hrs = min / 60;
        double days = hrs / 24;
        DecimalFormat d1 = new DecimalFormat("#.#");
        final int scale = 60;
        final int scale2 = 24;
        if (days >= 1){
            hrs = hrs - ((int)days * scale2);
            min = min - (((int)hrs * scale) + ((int)days * scale2 * scale));
            return (int)days+" day(s) "+(int)hrs+" hour(s) "
                    +(int)min+" minute(s)";
        } else if (hrs >= 1){
            min = min - ((int)hrs * scale);
            s = s - (((int)min * scale) + ((int)hrs * scale * scale));
            return (int)hrs+" hour(s) "+(int)min+" minute(s) "
                    +(int)s+" second(s)";
        } else if (min >= 1) {
            s = s - ((int)min * scale);
            return (int)min+" minute(s) "+d1.format(s)+" second(s)";
        } else if (s >= 1){
            return d1.format(s)+" second(s)";
        } else if (ms >= 1){
            return d1.format(ms)+" millisecond(s)";
        } else if (us >= 1){
            return d1.format(us)+" microsecond(s)";
        } else {
            return nanoseconds+" nanosecond(s)";
        }
    }

    /**
     * returns a random string with a random length
     * @param length the length of the random string
     * @return the random string
     */
    static String getString(int length){
        Random random = new Random();
        StringBuilder retVal = new StringBuilder();
        for (int i = 0; i < length; i++){
            char c = (char) (random.nextInt(26) + 'a');
            retVal.append(c);
        }
        return retVal.toString();
    }
}
