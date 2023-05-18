package exception;

/**
 * Game finished exception
 * Exception when the game is finished
 */
public class GameFinishedException extends Exception {

	private static final long serialVersionUID = -128459909315038105L;

	/**
	 * Exception when the game is finished
	 */
	public GameFinishedException() {
		super();
	}

	/**
	 * Exception when the game is finished
	 * @param message This exceptions message
	 */
	public GameFinishedException(String message) {
		super(message);
	}
}
