/**
 * 
 */
package fr.eni.dispocar.bo;

/**
 * @author sebastien
 *	La classe contient un objet d'utilisateur
 */
public class Utilisateur {
	
	// ATTRIBUTS
	
	private Integer idUtilisateur;
	private String email;
	private String password;
	private Boolean administrateur = false;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 * 
	 */
	public Utilisateur() {
	}

	/**
	 * @param idUtilisateur
	 * @param email
	 * @param password
	 * @param administrateur
	 */
	public Utilisateur(Integer idUtilisateur, String email, String password, Boolean administrateur) {
		setIdUtilisateur(idUtilisateur);
		setEmail(email);
		setPassword(password);
		setAdministrateur(administrateur);
	}

	/**
	 * @param email
	 * @param password
	 * @param administrateur
	 */
	public Utilisateur(String email, String password, Boolean administrateur) {
		setEmail(email);
		setPassword(password);
		setAdministrateur(administrateur);
	}
	
	// ASCESSEURS ET MUTATEURS

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the administrateur
	 */
	public Boolean getAdministrateur() {
		return administrateur;
	}

	/**
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}

	/**
	 * @return the idUtilisateur
	 */
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	
	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	
	// METHODE TO STRING
	
	@Override
	public String toString() {
		return "Utilisateur [getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getAdministrateur()="
				+ getAdministrateur() + ", getIdUtilisateur()=" + getIdUtilisateur() + "]";
	}
}
