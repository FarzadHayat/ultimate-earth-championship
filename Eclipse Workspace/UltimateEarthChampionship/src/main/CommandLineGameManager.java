package main;

import display.CommandLineDisplay;

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
		getShop().generateCatalogue();
		commandLineDisplay.displayShop();
		commandLineDisplay.closeScanner();
	}
}