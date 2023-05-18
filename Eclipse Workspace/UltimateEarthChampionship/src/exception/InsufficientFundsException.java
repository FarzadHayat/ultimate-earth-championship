package exception;

/**
 * Insufficient funds exception
 * Called when a team attempts to purchase something but does not have enough money
 */
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 7558019994468955312L;

	/**
	 * Called when a team attempts to purchase something but does not have enough money
	 */
	public InsufficientFundsException() {
		super();
	}

	/**
	 * Called when a team attempts to purchase something but does not have enough money
	 * @param message This exceptions message
	 */
	public InsufficientFundsException(String message) {
		super(message);
	}

}
