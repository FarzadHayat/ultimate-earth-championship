package manager;

import display.DisplayType;
import display.GraphicalDisplay;
import model.GameEnvironment;
import model.Match;
import model.Team;
import views.MatchSetupView;
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
		visitShop();
	}
	
	/**
	 * The main method that initializes and starts the game.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.GUI);
		GameManager.start();
	}

	@Override
	public void visitStory(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTeam() {
		graphicalDisplay.displayTeam();
	}

	@Override
	public void visitShop() {
		graphicalDisplay.displayShop();
	}

	@Override
	public void visitMatchSelection() {
		graphicalDisplay.displayMatchSelection();
	}

	@Override
	public void visitMatchSetup(Team enemyTeam) {
		graphicalDisplay.displayMatchSetup(enemyTeam);
	}

	@Override
	public void visitLiveMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitMatchResults(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitWeekResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}


}
