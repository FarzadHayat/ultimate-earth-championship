package exception;

/**
 * Full team exception.
 * Exception when a team is full.
 *
 */
public class FullTeamException extends Exception {

	private static final long serialVersionUID = 2220557606077685904L;

	/**
	 * Exception when a team is full
	 */
	public FullTeamException() {
		super();
	}

	/**
	 * Exception when a team is full
	 * @param message This exceptions message
	 */
	public FullTeamException(String message) {
		super(message);
	}
}
