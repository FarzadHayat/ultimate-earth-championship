package exception;

/**
 * Incomplete team exception
 * Exception for when a team has less than four champions
 */
public class IncompleteTeamException extends Exception {

	private static final long serialVersionUID = -3338719198136545331L;

	/**
	 * Exception for when a team has less than four champions
	 */
	public IncompleteTeamException() {
		super();
	}

	/**
	 * Exception for when a team has less than four champions
	 * @param message This exceptions message
	 */
	public IncompleteTeamException(String message) {
		super(message);
	}
}
