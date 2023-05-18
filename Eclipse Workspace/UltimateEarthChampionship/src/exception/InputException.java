package exception;

/**
 * Input Exception
 * Exception for when a users input is not expected or allowed
 */
public class InputException extends Exception {

	private static final long serialVersionUID = -8733750908525405891L;

	/**
	 * Exception for when a users input is not expected or allowed
	 */
	public InputException() {
		super();
	}

	/**
	 * Exception for when a users input is not expected or allowed
	 * @param message This exceptions message
	 */
	public InputException(String message) {
		super(message);
	}

}
