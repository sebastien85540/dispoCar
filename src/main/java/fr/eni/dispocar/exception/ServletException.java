/**
 * 
 */
package fr.eni.dispocar.exception;

import java.util.Arrays;

/**
 * @author sebastien
 *
 */
public class ServletException extends Exception{

	// CONSTRUCTEUR
	
	/**
	 * 
	 */
	public ServletException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServletException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServletException(String message) {
		super(message);
	}
	
	// METHODE TOSTRING
	

	@Override
	public String toString() {
		return String.format(
				"ServletException [getMessage()=%s, getLocalizedMessage()=%s, getCause()=%s, toString()=%s, fillInStackTrace()=%s, getStackTrace()=%s, getSuppressed()=%s, getClass()=%s, hashCode()=%s]",
				getMessage(), getLocalizedMessage(), getCause(), super.toString(), fillInStackTrace(),
				Arrays.toString(getStackTrace()), Arrays.toString(getSuppressed()), getClass(), hashCode());
	}
}
