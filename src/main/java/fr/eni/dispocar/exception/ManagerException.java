/**
 * 
 */
package fr.eni.dispocar.exception;

/**
 * @author sebastien
 *
 */
public class ManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCTEURS

	/**
	 * 
	 */
	public ManagerException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ManagerException(String message) {
		super(message);
	}
	
	// METHODE TO STRING

	@Override
	public String toString() {
		return "ManagerException []";
	}
	
}
