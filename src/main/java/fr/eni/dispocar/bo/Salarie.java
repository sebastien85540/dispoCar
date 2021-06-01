/**
 * 
 */
package fr.eni.dispocar.bo;

import java.io.File;

/**
 * @author sebastien
 * Cette classe contient un objet Salarie
 *
 */
public class Salarie {
	
	// ATTRIBUTS
	
	private Integer idSalarie;
	private String nom;
	private String prenom;
	private String email;
	private int telephone;
	private String permisName;
	private File permis;
	private Agence agenceSalarie;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public Salarie() {
	}
	
	/**
	 * Constructeur surchargé contenant tous les attributs sauf l'ID
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param permisName
	 * @param permis
	 * @param agenceSalarie
	 */
	public Salarie(String nom, String prenom, String email, int telephone, String permisName, File permis,
			Agence agenceSalarie) {
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setTelephone(telephone);
		setPermisName(permisName);
		setPermis(permis);
		setAgenceSalarie(agenceSalarie);
	}
	// GETTERS ET SETTERS
	// ils permettent d'encapsuler les attributs et de proteger contre les attaque XSS

	/**
	 * @return the idSalarie
	 */
	public Integer getIdSalarie() {
		return idSalarie;
	}

	/**
	 * @param idSalarie the idSalarie to set
	 */
	public void setIdSalarie(Integer idSalarie) {
		this.idSalarie = idSalarie;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

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
	 * @return the telephone
	 */
	public int getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the permisName
	 */
	public String getPermisName() {
		return permisName;
	}

	/**
	 * @param permisName the permisName to set
	 */
	public void setPermisName(String permisName) {
		this.permisName = permisName;
	}

	/**
	 * @return the permis
	 */
	public File getPermis() {
		return permis;
	}

	/**
	 * @param permis the permis to set
	 */
	public void setPermis(File permis) {
		this.permis = permis;
	}

	/**
	 * @return the agenceSalarie
	 */
	public Agence getAgenceSalarie() {
		return agenceSalarie;
	}

	/**
	 * @param agenceSalarie the agenceSalarie to set
	 */
	public void setAgenceSalarie(Agence agenceSalarie) {
		this.agenceSalarie = agenceSalarie;
	}
	
	// METHODE TO STRING

	@Override
	public String toString() {
		return String.format(
				"Salarie [getIdSalarie()=%s, getNom()=%s, getPrenom()=%s, getEmail()=%s, getTelephone()=%s, getPermisName()=%s, getPermis()=%s, getAgenceSalarie()=%s]",
				getIdSalarie(), getNom(), getPrenom(), getEmail(), getTelephone(), getPermisName(), getPermis(),
				getAgenceSalarie());
	}
	
}
