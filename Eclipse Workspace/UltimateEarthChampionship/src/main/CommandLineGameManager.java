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
		commandLineDisplay.displayShop();
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
}