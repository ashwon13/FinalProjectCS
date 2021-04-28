import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Panel extends JPanel {
    String word;
    public Panel() {
        
        setLayout(new BorderLayout(0,0)); // Setting layout to BorderLayout
        
        try { word = getWord(); }
        catch (FileNotFoundException e) {System.out.println(2);}
        

        JLabel dashes = new JLabel(repeat("_ ",word.length()));
        dashes.setFont(new Font("Serif", Font.PLAIN, 50));
        //dashes.setHorizontalAlignment(JLabel.CENTER);
        add(dashes, BorderLayout.CENTER);

        JLabel enter = new JLabel("Guess Letter:");
        enter.setFont(new Font("Serif", Font.PLAIN, 25));
        JTextField guess = new JTextField(5);
        JPanel pane = new JPanel(new FlowLayout());
        guess.setFont(new Font("Serif", Font.PLAIN, 25));
        pane.add(enter);
        pane.add(guess);
        add(pane, BorderLayout.LINE_START);
        
    }
    private String repeat(String ch, int len) {
        String fin = "";
        for (int i=0;i<len;i++)
            fin = fin+ch;
        return fin;
    }
    public String getWord() throws FileNotFoundException {
        String[] words = new String[855];
        File file = new File("words.txt");
        Scanner scan = new Scanner(file);
        for (int i=0;scan.hasNextLine();i++) {
            words[i] = scan.nextLine();
        }
        scan.close();
        return words[(int)(Math.random()*855)];
    }
}



