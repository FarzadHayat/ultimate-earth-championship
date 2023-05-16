package exception;

public class GameFinishedException extends Exception {

	private static final long serialVersionUID = -128459909315038105L;

	public GameFinishedException() {
		super();
	}

	public GameFinishedException(String message) {
		super(message);
	}
}
