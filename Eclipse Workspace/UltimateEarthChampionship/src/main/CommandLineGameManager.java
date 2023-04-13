package main;

import views.CommandLineView;

/**
 * A subclass of GameManager that represents the command line version of the game.
 */
public class CommandLineGameManager extends GameManager
{

	private CommandLineView commandLineView;
	
	/**
	 * Starts the game by initializing the CommandLineView.
	 */
	@Override
	public void play()
	{
		commandLineView = new CommandLineView();
		getShop().generateCatalogue();
		commandLineView.displayShop(getShop());
		commandLineView.closeScanner();
	}
}