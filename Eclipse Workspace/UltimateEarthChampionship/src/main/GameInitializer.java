package main;

/**
 * The class responsible for initializing the game.
 * It sets the display type and starts the game by calling the play() method on the GameManager instance.
 */
public class GameInitializer {

	public static final DisplayType TYPE = DisplayType.CLI;

	/**
	 * The main method that initializes and starts the game.
	 * It calls the play() method on the GameManager instance.
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) {
		GameManager.getInstance().play();
	}

}
