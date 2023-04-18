package exception;

public class TeamFullException extends Exception {
	
	private static final long serialVersionUID = 2220557606077685904L;

	public TeamFullException() {
		super();
	}
	
	public TeamFullException(String message) {
		super(message);
	} 
}
