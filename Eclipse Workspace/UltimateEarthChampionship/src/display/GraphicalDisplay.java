package display;

import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.GameEnvironment;
import main.Match;
import main.Shop;
import main.Team;

public class GraphicalDisplay implements DisplayStrategy {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public GraphicalDisplay()
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
	
	/**
	 * Sets the view to be displayed in the main application window.
	 * @param view the view to be displayed
	 */
	public void displayView(Container view) {
		frame.setContentPane(view);
		frame.setVisible(true);
	}

	@Override
	public void displayStory(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHome(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTeam(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayShop(Shop shop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMatches(ArrayList<Match> matches) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLiveMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMatchResults(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayWeekResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}


}
