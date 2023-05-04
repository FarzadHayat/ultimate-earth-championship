package exception;

public class IllegalPurchaseException extends Exception {

	private static final long serialVersionUID = -320264635201129083L;

	public IllegalPurchaseException() {
		super();
	}
	
	public IllegalPurchaseException(String message) {
		super(message);
	} 
}
