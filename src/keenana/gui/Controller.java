/*
 * Course: CSC 1120 121
 * Term: Spring 2024
 * Assignment: Lab 14
 * Name: Andrew Keenan
 * Created: 4-24-2024
 */
package keenana.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import keenana.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * COntroller class for the lab 11 javafx
 */
public class Controller {
    @FXML
    private TextField hE;
    @FXML
    private TextField hA;
    @FXML
    private TextField tA;
    @FXML
    private TextField tE;
    @FXML
    private TextField searched;
    @FXML
    private TextField uE;
    @FXML
    private TextField uA;
    @FXML
    private TextField oE;
    @FXML
    private TextField oA;
    @FXML
    private TextField bE;
    @FXML
    private TextField bA;
    @FXML
    private ListView<String> display;
    @FXML
    private Label file;
    private UnorderedList ul;
    private OrderedList ol;
    private BinarySearchTree bst;
    private Trie t;
    private HashTable h;

    @FXML
    private void chosenFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File input = fc.showOpenDialog(null);
        String fileName = input.toString();
        List<String> words = new ArrayList<>();
        TreeSet<String> set = new TreeSet<>();
        try {
            Scanner scan = new Scanner(input);
            while (scan.hasNext()){
                String s = scan.next();
                words.add(s);
                set.add(s);
            }
        } catch (FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error reading in dictionary File (IO Error)");
            alert.setTitle("Error");
            alert.show();
        }
        ul = new UnorderedList(words);
        ol = new OrderedList(words);
        bst = new BinarySearchTree(set);
        t = new Trie(words);
        h = new HashTable(words);
        file.setText("Dictionary File: "+fileName);
    }
    private String calcTime(long start, long end){
        return AutoCompleter.format(end - start);
    }
    @FXML
    private void input(javafx.scene.input.KeyEvent keyEvent) {
        String input = searched.getText();
        display.getItems().clear();
        display.getItems().addAll(ol.allMatches(input));
        long ls;
        long le;

        //unordered list
        ls = System.nanoTime();
        ul.exactMatch(input);
        le = System.nanoTime();
        uE.setText(calcTime(ls, le));
        ls = System.nanoTime();
        ul.allMatches(input);
        le = System.nanoTime();
        uA.setText(calcTime(ls, le));

        //ordered list
        ls = System.nanoTime();
        ol.exactMatch(input);
        le = System.nanoTime();
        oE.setText(calcTime(ls, le));
        ls = System.nanoTime();
        ol.allMatches(input);
        le = System.nanoTime();
        oA.setText(calcTime(ls, le));

        //binary search
        ls = System.nanoTime();
        bst.exactMatch(input);
        le = System.nanoTime();
        bE.setText(calcTime(ls, le));
        ls = System.nanoTime();
        bst.allMatches(input);
        le = System.nanoTime();
        bA.setText(calcTime(ls, le));

        // trie
        ls = System.nanoTime();
        t.exactMatch(input);
        le = System.nanoTime();
        tE.setText(calcTime(ls, le));
        ls = System.nanoTime();
        t.allMatches(input);
        le = System.nanoTime();
        tA.setText(calcTime(ls, le));

        //HashTable
        ls = System.nanoTime();
        h.exactMatch(input);
        le = System.nanoTime();
        hE.setText(calcTime(ls, le));
        ls = System.nanoTime();
        h.allMatches(input);
        le = System.nanoTime();
        hA.setText(calcTime(ls, le));
    }
}
