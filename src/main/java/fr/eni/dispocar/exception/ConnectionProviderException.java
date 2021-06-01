/**
 * 
 */
package fr.eni.dispocar.exception;

import java.util.Arrays;

/**
 * @author sebastien
 * retourne les erreurs du connectionProvider
 *
 */
public class ConnectionProviderException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1359163900995006844L;
	// CONSTRUCTEURS

	/**
	 * Constructeur par defaut
	 */
	public ConnectionProviderException() {
		super();
	}

	/**
	 * Constructeur surchargé avec un message personnalisé ainsi que le numero de l'erreur
	 * @param message
	 * @param cause
	 */
	public ConnectionProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructeur surchargé permettant de remonter seulement un message personnalisé d'erreur
	 * @param message
	 */
	public ConnectionProviderException(String message) {
		super(message);
	}
	
	// METHODE TO STRING

	@Override
	public String toString() {
		return "ConnectionProviderException [getMessage()=" + getMessage() + ", getLocalizedMessage()="
				+ getLocalizedMessage() + ", getCause()=" + getCause() + ", toString()=" + super.toString()
				+ ", fillInStackTrace()=" + fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", getSuppressed()=" + Arrays.toString(getSuppressed()) + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
}
