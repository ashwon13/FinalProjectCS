import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Panel extends JPanel {
<<<<<<< HEAD
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
=======
   String word;
   private JTextField[] input;
   private JLabel[] inputLabel;
   String k;
   public Panel() {
      setLayout(new BorderLayout()); // Setting layout to BorderLayout
      
      try { word = getWord(); }
      catch (FileNotFoundException e) {System.out.println(2);}
      
   
      JLabel dashes = new JLabel(repeat("_ ",word.length()));
      dashes.setFont(new Font("Serif", Font.PLAIN, 50));
      dashes.setHorizontalAlignment(JLabel.CENTER);
      add(dashes, BorderLayout.CENTER);
      setLayout(new GridLayout(1,1));
      inputLabel=new JLabel[1];
      for(int i=0;i<inputLabel.length;i++)
      {
         inputLabel[i]=new JLabel("Enter you guess here");
         add(inputLabel[i]);
      
      }
      
      input=new JTextField[1];
   
      for(int x=0; x< input.length;x++)
      {
         input[x]=new JTextField();
         add(input[x]);
      
      }
   
   
   
   
      JLabel label1 = new JLabel( "1", JLabel.CENTER );
      label1.setHorizontalTextPosition(JLabel.CENTER);
           
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
   
>>>>>>> 538b911ff5c4442349d661b65fc8c5e3ed070dfb
}



