/**
 * Names: Pranav Velleleth, Ashwin Pulla, Vaibhav Vasudevan
 */
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Driver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String rules = "Rules/Info:\n    - The objective is to fully guess the word before the hangman is drawn\n    - You have 10 incorrect guesses\n    - Once the stick man is fully drawn, you lose\n    - You can only guess one lowercase letter at a time, don't type the same letter twice(hint: start with vowels)\n    - Good Luck!!";
		JOptionPane.showMessageDialog(null, rules, "Welcome to Hangman!!", 0);
		JFrame frame = new JFrame("Hangman Game");
		frame.setSize(900, 600);
		frame.setLocation(600,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane( new Panel());
		frame.setVisible(true);
	}

}


