package manager;

import display.CommandLineDisplay;
import display.DisplayType;
import story.OpeningCutscene;

/**
 * A subclass of GameManager that represents the CLI version of the game.
 */
public class CommandLineGameManager extends GameManager {

	/**
	 * Start the game by creating a new CommandLineDisplay and displaying the
	 * opening cutscene.
	 */
	@Override
	public void play() {
		displayStrategy = new CommandLineDisplay();
		setCutscene(new OpeningCutscene());
		displayStrategy.displayCutscene(getCutscene());
		((CommandLineDisplay) displayStrategy).closeScanner();
	}

	/**
	 * The main method that starts the game. Set the display type to CLI and call
	 * the start method.
	 *
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.setDisplayType(DisplayType.CLI);
		GameManager.start();
	}

}