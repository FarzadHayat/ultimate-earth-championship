package main;

import views.GraphicalDisplay;
import views.TabbedView;

/**
 * A subclass of GameManager that represents the graphical version of the game.
 */
public class GraphicalGameManager extends GameManager
{
	
	private GraphicalDisplay graphicalDisplay;

	/**
	 * Starts the game by initializing the GraphicalDisplay.
	 */
	@Override
	public void play()
	{
		graphicalDisplay = new GraphicalDisplay();
		getShop().generateCatalogue();
		TabbedView tabbedView = new TabbedView();
		graphicalDisplay.displayView(tabbedView);
	}

}
