/**
 * 
 */
package fr.eni.dispocar.exception;

/**
 * @author sebastien
 *
 */
public class ToolsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	// CONSTRUCTEURS
	
	/**
	 * 
	 */
	public ToolsException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ToolsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ToolsException(String message) {
		super(message);
	}
	// METHODE TO STRING

	@Override
	public String toString() {
		return "ToolsException []";
	}
	
}
