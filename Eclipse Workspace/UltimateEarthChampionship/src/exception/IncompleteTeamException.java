package exception;

public class IncompleteTeamException extends Exception {

	private static final long serialVersionUID = -3338719198136545331L;

	public IncompleteTeamException() {
		super();
	}

	public IncompleteTeamException(String message) {
		super(message);
	}
}
