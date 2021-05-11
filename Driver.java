/**
 * Names: Pranav Velleleth, Ashwin Pulla, Vaibhav Vasudevan
 */
import java.io.IOException;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Hangman Game");
		frame.setSize(900, 600);
		frame.setLocation(600,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane( new Panel());
		frame.setVisible(true);
	}

}


