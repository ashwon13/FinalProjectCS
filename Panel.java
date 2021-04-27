/**
 * Names: Pranav Velleleth, Ashwin Pulla, Vaibhav Vasudevan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Panel extends JPanel {
    public Panel() {
        setLayout(new BorderLayout()); // Setting layout to BorderLayout

        
    }

    public String getWord() throws FileNotFoundException {
        String[] words = new String[855];
	String x="Hello;//this is a test
        File file = new File("words.txt");
        Scanner scan = new Scanner(file);
        for (int i=0;scan.hasNextLine();i++) {
            words[i] = scan.next();
        }
        scan.close();
        return words[(int)(Math.random()*855)];
    }
}
