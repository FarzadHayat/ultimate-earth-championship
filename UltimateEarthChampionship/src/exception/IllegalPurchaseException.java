package exception;

/**
 * Illegal Purchase Exception
 * Exception for when a purchase cannot be done because an AI team has already purchased a weapon
 */
public class IllegalPurchaseException extends Exception {

	private static final long serialVersionUID = -320264635201129083L;

	/**
	 * Exception for when a purchase cannot be done because an AI team has already purchased a weapon
	 */
	public IllegalPurchaseException() {
		super();
	}

	/**
	 *Exception for when a purchase cannot be done because an AI team has already purchased a weapon
	 * @param message This exceptions message
	 */
	public IllegalPurchaseException(String message) {
		super(message);
	}
}
