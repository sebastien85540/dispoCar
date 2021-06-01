/**
 * 
 */
package fr.eni.dispocar.exception;


/**
 * @author sebastien
 * c'est une classe spécifique pour toutes les erreurs se situant dans la couche DAL
 *
 */
public class DALException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut 
	 * 
	 */
	public DALException() {
		super();
	}

	/**
	 * Constructeur prenant en compte le message d'erreur personnalisé
	 * @param message
	 */
	public DALException(String message) {
		super(message);
	}

	/**
	 * Constructeur surchargé prenant en compte un message personnel ainsi que l'erreur
	 * @param message
	 * @param cause
	 */
	public DALException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	// METHODE TO STRING renvoyant les différentes erreurs
	
	@Override
	public String toString() {
		return String.format("DALException []");
	}
	
}
