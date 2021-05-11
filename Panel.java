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

public class Panel extends JPanel {
    private String word, letter, dashStr;
    private String usedLettsStr = "";
    private JLabel dashes, picLabel, usedLetts;
    private JTextField guess;
    private BufferedImage hangMan;
    private int phase=0;

    public Panel() throws IOException {
        setLayout(new BorderLayout(0,0)); // Setting layout to BorderLayout
        
        setBackground(new Color(255, 204, 153));

        try { word = chooseWord(); }
        catch (FileNotFoundException e) {System.out.println();}
        
        dashes = new JLabel(repeat("_ ",word.length()));
        dashes.setFont(new Font("Serif", Font.PLAIN, 50));
        add(dashes, BorderLayout.CENTER);

        JPanel hangPane = new JPanel(new BorderLayout());
        hangPane.setBackground(new Color(255, 204, 153));
        JPanel miniPane = new JPanel(new FlowLayout());
        miniPane.setBackground(new Color(255, 204, 153));
        
        JLabel text = new JLabel("Guess Letter:");
        text.setFont(new Font("Serif", Font.PLAIN, 25));
        miniPane.add(text);

        guess = new JTextField(5);
        guess.setFont(new Font("Serif", Font.PLAIN, 25));
        miniPane.add(guess);

        JButton enter = new JButton("Guess!");
        enter.addActionListener( new Listener());
        miniPane.add(enter);

        hangMan = ImageIO.read(new File("hangman/0.jpg"));
        picLabel = new JLabel(new ImageIcon(hangMan));
        hangPane.add(picLabel, BorderLayout.LINE_START);

        usedLetts = new JLabel("Used Letters: ");
        usedLetts.setFont(new Font("Serif", Font.PLAIN, 25));
        hangPane.add(usedLetts, BorderLayout.PAGE_END);

        hangPane.add(miniPane, BorderLayout.PAGE_START);
        add(hangPane, BorderLayout.LINE_START);
    }
    private String repeat(String ch, int len) {
        String fin = "";
        for (int i=0;i<len;i++)
            fin = fin+ch;
        return fin;
    }
    private String chooseWord() throws FileNotFoundException {
        String[] words = new String[851];
        File file = new File("words.txt");
        Scanner scan = new Scanner(file);
        for (int i=0;scan.hasNextLine() && i<851;i++) {
            words[i] = scan.nextLine();
        }
        scan.close();
        return words[(int)(Math.random()*850)];
    }
    private int isCorrectGuess(String lettGuess, String[] d) {
        int rVal = 0;
        if (lettGuess.length()==0 || lettGuess.length()>1 || !lettGuess.matches("[a-z]+")) {
            return -1;
        }
        for (int i=0;i<word.length();i++) {
            if (word.charAt(i)==lettGuess.charAt(0)) {
                d[i]=lettGuess;
                rVal=1;
            }
        }
        return rVal;
    }
    private void endScreen(String winOrLose) throws IOException {
        String[] buttons = {"Restart", "Quit"};
        JPanel endPane = new JPanel();
        if (winOrLose.equals("You Lost!!!"))
            endPane.add(new JLabel(winOrLose+"\nThe word was "+word));
        else
            endPane.add(new JLabel(winOrLose));
        int result = JOptionPane.showOptionDialog(null, endPane, "End Game Message", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
        if (result == JOptionPane.YES_OPTION) {
            usedLettsStr = "";
            phase = 0;
            word = chooseWord();
            hangMan = ImageIO.read(new File("hangman/"+phase+".jpg"));
            picLabel.setIcon(new ImageIcon(hangMan));
            usedLetts.setText("Used Letters: "+usedLettsStr);
            guess.setText("");
            dashes.setText(repeat("_ ",word.length()));
        }
        if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String labelFin = "";
            letter = guess.getText();
            dashStr = dashes.getText();
            String[] dash = dashStr.split(" ");
            if (isCorrectGuess(letter, dash)==0) {
                phase++;
                try { hangMan = ImageIO.read(new File("hangman/"+phase+".jpg")); }
                catch (IOException f) { System.out.println(); }
                picLabel.setIcon(new ImageIcon(hangMan));
                usedLettsStr += letter;
                usedLetts.setText("Used Letters: "+usedLettsStr);
                try { if (phase>=10) {endScreen("You Lost!!!");} }
                catch (IOException z) {System.out.println();}
            }
            else if (isCorrectGuess(letter, dash)==1) {
                for (int i=0;i<dash.length;i++) {
                    dash[i] += " ";
                    labelFin += dash[i];
                }
                dashes.setText(labelFin);
                try {
                if (labelFin.indexOf("_")==-1)
                    endScreen("Congrats You Won!!!");
                } catch (IOException w) {System.out.println();}
            }
            else {
                JOptionPane.showMessageDialog(null, "Your guess should be ONE LOWERCASE LETTER", "Guess Error", 0);
            }
        }
    }
}







