/**
 * Names: Pranav Velleleth, Ashwin Pulla, Vaibhav Vasudevan
 */
// Import necessary libraries and packages
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Image;
import javax.imageio.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/**
 * Class PanelStub
 *      Creates Implementation of Hangman Game
 */
public class PanelStub extends JPanel {
    // Private fields that will be used
    private String word, letter, dashStr;
    private String usedLettsStr = "";
    private JLabel dashes, picLabel, usedLetts;
    private JTextField guess;
    private BufferedImage hangMan;
    private int phase = 0;

    public PanelStub() {
        /**
         * Set layout to BorderLayout
         * 
         * Set the field “word” to chooseWord() chooseWord() is a method that we will
         * define later in the code
         * 
         * Instantiate dashes Add dashes to the GUI
         * 
         * 
         * 
         * Create JLabel asking to “Guess a letter” and add it to GUI
         * 
         * 
         * Instantiate guess(which is the input box) and add it to GUI
         * 
         * 
         * Instantiate enter button that makes a button labeled “Guess!, add to GUI, and
         * add actionListener
         * 
         * 
         * 
         * Add 0.jpg(which is the base hangman) to the GUI using hangMan and picLabel
         * 
         * Instantiate usedLetts to create label that shows “Used Letters: “
         */

    }

    private String repeat(String ch, int len) {
        return "";
        // Repeats the dash based on the word’s length
    }

    private String chooseWord() throws FileNotFoundException {
        return "";
        // chooses a random word from words.txt
    }

    private boolean isCorrectGuess(String lettGuess, String[] d) {
        return false; 
        // checks to see if the guessed letter is correct(is in the chosen word)
    }

    private void endScreen(String winOrLose) {
        // Displays(new window) whether the user won or lost(defined by parameter),
        // allows user to either restart with a new word or quit game
    }

    private class Listener implements ActionListener {   // Listener class for the JButton
        public void actionPerformed(ActionEvent e) {
            /**
             * If the guess is not correct(call isCorrectGuess): 
             *      phase++; 
             *      Update picLabel to the next hangman pic(ex. 1.jpg,2.jpg,etc.) 
             *      Update usedLetts If phase>=10 then endScreen(“You Lost!!!”) 
             * Else: 
             *      Updates the label with dashes(by replacing dash with the correctly guessed letter) 
             *      If the whole word is completed by the user then endScreen(“Congrats, You Won!!!”)
             */
        }
    }
}
