package display;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

import model.GameEnvironment;
import match.*;
import views.ChampionSetupView;
import views.MatchView;
import views.WeaponSetupView;
import views.TabbedView;

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
		frame.setLocationRelativeTo(null);
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
	public void displayHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTeam() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showTeamView();
	}

	@Override
	public void displayShop() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showShopView();
	
	}

	@Override
	public void displayStadium() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showStadiumView();
	}

	@Override
	public void displayChampionSetup() {
		ChampionSetupView championSetupView = new ChampionSetupView();
		displayView(championSetupView);
	}
	

	@Override
	public void displayWeaponSetup() {
		WeaponSetupView weaponSetupView = new WeaponSetupView();
		displayView(weaponSetupView);
	}

	@Override
	public void displayLiveMatch(LiveMatch match) {
		
		MatchView matchView = new MatchView(match);
		displayView(matchView);
		
	}

	@Override
	public void displayGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}



}
