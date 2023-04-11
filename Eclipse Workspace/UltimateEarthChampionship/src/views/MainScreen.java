package views;

import java.awt.Container;
import javax.swing.JFrame;
import java.awt.Font;

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
		frame.setVisible(true);
	}
	
	public void displayView(Container view) {
		frame.setContentPane(view);
	}


}
