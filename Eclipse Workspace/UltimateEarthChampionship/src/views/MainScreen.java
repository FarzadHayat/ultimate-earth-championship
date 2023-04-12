package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

public class MainScreen
{

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainScreen()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		frame.setTitle("Ultimate Earth Championship");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void displayView(Container view) {
		frame.setContentPane(view);
		frame.setVisible(true);
	}


}
