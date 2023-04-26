package manager;

import display.DisplayType;
import display.GraphicalDisplay;
import views.SetupView;
import views.TabbedView;

/**
 * A subclass of GameManager that represents the graphical version of the game.
 */
public class GraphicalGameManager extends GameManager
{
	private GraphicalDisplay graphicalDisplay;

	private SetupView setupView;
	
	/**
	 * Starts the game by initializing the GraphicalDisplay.
	 */
	@Override
	public void play()
	{
		graphicalDisplay = new GraphicalDisplay();
		
		//tabbedView();
		setup();
	}
	
	private void setup()
	{
		setupView = new SetupView(this);
		
		graphicalDisplay.displayView(setupView);
	}
	
	/**
	 * Called by the setupView to confirm that the playerTeam has been setup
	 */
	public void finishedSetup()
	{
		tabbedView();
	}
	
	private void tabbedView()
	{
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
