package main;

import display.GraphicalDisplay;
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
		TabbedView tabbedView = new TabbedView();
		graphicalDisplay.displayView(tabbedView);
	}
	
	public GraphicalDisplay getGraphicalDisplay() {
		return graphicalDisplay;
	}
	
	/**
	 * The main method that initializes and starts the game.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.GUI);
		GameManager.start();
	}


}
