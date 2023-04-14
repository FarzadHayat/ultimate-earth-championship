package main;

import views.MainScreen;
import views.TabbedView;

/**
 * A subclass of GameManager that represents the graphical version of the game.
 */
public class GraphicalGameManager extends GameManager
{
	
	private MainScreen mainScreen;

	/**
	 * Starts the game by initializing the MainScreen.
	 */
	@Override
	public void play()
	{
		mainScreen = new MainScreen();
		getShop().generateCatalogue();
		TabbedView tabbedView = new TabbedView();
		mainScreen.displayView(tabbedView);
	}

}
