package exception;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 7558019994468955312L;
	
	public InsufficientFundsException() {
		super();
	}
	
	public InsufficientFundsException(String message) {
		super(message);
	}

}
