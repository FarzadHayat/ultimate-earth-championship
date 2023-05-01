package manager;

import display.CommandLineDisplay;
import display.DisplayType;

/**
 * A subclass of GameManager that represents the command line version of the game.
 */
public class CommandLineGameManager extends GameManager
{	
	/**
	 * Starts the game by initializing the CommandLineDisplay.
	 */
	@Override
	public void play()
	{
		displayStrategy = new CommandLineDisplay();		
		displayStrategy.displayShop();
//		displayStrategy.closeScanner();
	}
	
	/**
	 * The main method that initializes and starts the game.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.CLI);
		GameManager.start();
	}

}