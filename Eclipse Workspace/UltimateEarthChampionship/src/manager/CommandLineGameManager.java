package manager;

import display.CommandLineDisplay;
import display.DisplayType;
import model.GameEnvironment;
import model.Match;
import model.Team;

/**
 * A subclass of GameManager that represents the command line version of the game.
 */
public class CommandLineGameManager extends GameManager
{
	private CommandLineDisplay commandLineDisplay;
	
	/**
	 * Starts the game by initializing the CommandLineDisplay.
	 */
	@Override
	public void play()
	{
		commandLineDisplay = new CommandLineDisplay();
		visitStadium();
		commandLineDisplay.closeScanner();
	}
	
	/**
	 * The main method that initializes and starts the game.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.CLI);
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
		commandLineDisplay.displayTeam();
	}

	@Override
	public void visitShop() {
		commandLineDisplay.displayShop();
	}

	@Override
	public void visitStadium() {
		commandLineDisplay.displayStadium();
	}

	@Override
	public void visitMatchSetup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitLiveMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitGameResults(GameEnvironment gameEnvironment) {
		// TODO Auto-generated method stub
		
	}
}