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
//imports
public class Panel extends JPanel {
    private String word, letter, dashStr;
    private String usedLettsStr = "";
    private JLabel dashes, picLabel, usedLetts;
    private JTextField guess;
    private BufferedImage hangMan;
    private int phase=0;
    //variables

    public Panel() throws IOException {
        setLayout(new BorderLayout(0,0)); // Setting layout to BorderLayout
        
        setBackground(new Color(255, 204, 153));//Setting Background color

        try { word = chooseWord(); }//Try to get a random word
        catch (FileNotFoundException e) {System.out.println();}//If we get an error tryingto get a random error print the error
        
        dashes = new JLabel(repeat("_ ",word.length()));//setting up dashes corresponding to letter length
        dashes.setFont(new Font("Serif", Font.PLAIN, 50));//setting font of dasher
        add(dashes, BorderLayout.CENTER);//setting position of dasher

        JPanel hangPane = new JPanel(new BorderLayout());//setting up the plane that posts the hangman image
        hangPane.setBackground(new Color(255, 204, 153));//setting up color of plane
        JPanel miniPane = new JPanel(new FlowLayout());//creating guess letters plane
        miniPane.setBackground(new Color(255, 204, 153));//setting background color of used letter plane
        
        JLabel text = new JLabel("Guess Letter:");//setting up the Guess letter text
        text.setFont(new Font("Serif", Font.PLAIN, 25));//setting font
        miniPane.add(text);//adding it to plane

        guess = new JTextField(5);//setting up guess letter textbox
        guess.setFont(new Font("Serif", Font.PLAIN, 25));//setting up font
        miniPane.add(guess);//adding it to plain

        JButton enter = new JButton("Guess!");//Setting up guess button
        enter.addActionListener( new Listener());//adding a action listener to it
        miniPane.add(enter);//adding it to plane

        hangMan = ImageIO.read(new File("hangman/0.jpg"));//setting up hangman plane and default image
        picLabel = new JLabel(new ImageIcon(hangMan));//setting up the image
        hangPane.add(picLabel, BorderLayout.LINE_START);//adding it to plane

        usedLetts = new JLabel("Used Letters: ");//creatin Used Letters text
        usedLetts.setFont(new Font("Serif", Font.PLAIN, 25));//setting font
        hangPane.add(usedLetts, BorderLayout.PAGE_END);//adding it to plane

        hangPane.add(miniPane, BorderLayout.PAGE_START);//adds Guess Letter, textbox, and Guess! button to window
        add(hangPane, BorderLayout.LINE_START);//adds hangplane/ hangman images to window
    }
    private String repeat(String ch, int len) {//takes in a string cs and an int len
        String fin = "";//setings fin = to nothing
        for (int i=0;i<len;i++)//for i =0 and the loop will go on until i is greater the len, for each loop i will increase by 1 
            fin = fin+ch;//fin equals the string given in the method
        return fin;//we return the string fin when done with the loop
    }
    private String chooseWord() throws FileNotFoundException {//settings up method , it returns a string and throws file no found exceptions
        String[] words = new String[851];//and new list is set up
        File file = new File("words.txt");//file is opened
        Scanner scan = new Scanner(file);//we scan the file
        for (int i=0;scan.hasNextLine() && i<851;i++) {//for each line in the file
            words[i] = scan.nextLine();//we save the line into the list
        }
        scan.close();//we close the file
        return words[(int)(Math.random()*850)];//we return a random word from list
    }
    private int isCorrectGuess(String lettGuess, String[] d) {//sets up method that return an int and takes in a string called letterGuess and a string list called d
        int rVal = 0;//sets return val to 0
        if (lettGuess.length()==0 || lettGuess.length()>1 || !lettGuess.matches("[a-z]+")) {//if the letter guess lenght is = o or is greater than one or is not a letter in the alphabet
            return -1;//return -1
        }
        for (int i=0;i<word.length();i++) {//for i =0, loop until i is greater than the word length; i increases by 1 each loop
            if (word.charAt(i)==lettGuess.charAt(0)) { // if letter is in the word[i]  
                d[i]=lettGuess; //then d[i]=letter guess
                rVal=1;//return equals of 1
            }
        }
        return rVal; //return rval
    }
    private void endScreen(String winOrLose) throws IOException {//end screen 
        String[] buttons = {"Restart", "Quit"};//set up 2 buttons restart and quit
        JPanel endPane = new JPanel();// and new panel
        if (winOrLose.equals("You Lost!!!"))//if winOrLose equal lose
            endPane.add(new JLabel(winOrLose+"\nThe word was "+word));//then display the result of winOrLoose Method and display the correct word
        else//else
            endPane.add(new JLabel(winOrLose));//display the result of winOrLoose method
        int result = JOptionPane.showOptionDialog(null, endPane, "End Game Message", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);//set result to showOptionDialog
        if (result == JOptionPane.YES_OPTION) {//if result is = JOptionPane.YES_OPTION
            usedLettsStr = "";//set used letter str to ""
            phase = 0;//set phase to 0
            word = chooseWord();//set word to choseWord()
            hangMan = ImageIO.read(new File("hangman/"+phase+".jpg"));//set hangman to hangman+phase.png
            picLabel.setIcon(new ImageIcon(hangMan));//display hangman
            usedLetts.setText("Used Letters: "+usedLettsStr);//set used letters text
            guess.setText("");//set guess to ""
            dashes.setText(repeat("_ ",word.length()));//set dashes
        }
        if (result == JOptionPane.NO_OPTION) {// if optin is JOptionPane.NO_OPTION
            System.exit(0);//then exit
        }
    }
    private class Listener implements ActionListener {//setting up class for action listener for guess button
        public void actionPerformed(ActionEvent e) {//setting up method for action listener for guess button

            String labelFin = "";//setting up labelFin as ""
            letter = guess.getText();//setting letter as result from guess.getText()
            dashStr = dashes.getText();//setting dashStr as result from dashes.getText()
            String[] dash = dashStr.split(" ");//setting up string list dash as result from dashStr.split(" ");
            if (isCorrectGuess(letter, dash)==0) {//if letter isCorrectGuess returns 0
                phase++;//phase increases by one
                try { hangMan = ImageIO.read(new File("hangman/"+phase+".jpg")); }//try to set hangman to hangman+phase.jpg
                catch (IOException f) { System.out.println(); }//else if there is an error to print error
                picLabel.setIcon(new ImageIcon(hangMan));//set image
                usedLettsStr += letter;//add letter to used letter string
                usedLetts.setText("Used Letters: "+usedLettsStr);//set text of used letter to "Used Letters: "+usedLettsStr
                try { if (phase>=10) {endScreen("You Lost!!!");} }// try to see if phase is greater than or equal to 10 and display endScreen "You Lost!!!"
                catch (IOException z) {System.out.println();}//catch and print error
            }
            else if (isCorrectGuess(letter, dash)==1) {// if isCorrectGuess(letter, dash) returns 1 
                for (int i=0;i<dash.length;i++) {// for i=0, when i is less than dash.length ; increase i per each loop
                    dash[i] += " ";//the dash[i] +=" "
                    
                    labelFin += dash[i];//string labelFin += the dash in position [i]
                    
                }
                dashes.setText(labelFin);//setting the dashes text to labelFin
                try {//try
                if (labelFin.indexOf("_")==-1)//if there is no "_"
                    endScreen("Congrats You Won!!!");//then display endScreen("congratsYouWon!!!")
                } catch (IOException w) {System.out.println();}//else print error
            }
            else {//else
                JOptionPane.showMessageDialog(null, "Your guess should be one lowercase letter", "Guess Error", 0);//show message the the guess should be one lowercase letter
            }
        }
    }
}







