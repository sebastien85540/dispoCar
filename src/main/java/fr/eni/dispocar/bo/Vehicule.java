/**
 * 
 */
package fr.eni.dispocar.bo;

import java.time.LocalDate;

/**
 * @author sebastien
 * Contient le modele vehicule
 *
 */
public class Vehicule {
	
	// ATTRIBUTS
	
	private String immatriculation;
	private int nbrePlaces;
	private String designationVehicule;
	private LocalDate dateAchat;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public Vehicule() {
	}

	/**
	 * Constructeur surchargé avec les attributs obligatoires
	 * @param immatriculation
	 * @param nbrePlaces
	 * @param designationVehicule
	 */
	public Vehicule(String immatriculation, int nbrePlaces, String designationVehicule) {
		this.immatriculation = immatriculation;
		this.nbrePlaces = nbrePlaces;
		this.designationVehicule = designationVehicule;
	}

	/**
	 * Constructeur surchargé avec les attributs pour un vehicule d'agence
	 * @param immatriculation
	 * @param nbrePlaces
	 * @param designationVehicule
	 * @param dateAchat
	 */
	public Vehicule(String immatriculation, int nbrePlaces, String designationVehicule, LocalDate dateAchat) {
		this.immatriculation = immatriculation;
		this.nbrePlaces = nbrePlaces;
		this.designationVehicule = designationVehicule;
		this.dateAchat = dateAchat;
	}
	
	// ASCESSEURS ET MUTATEURS
	// ça permet l'encapsulation des attributs

	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @param immatriculation the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @return the nbrePlaces
	 */
	public int getNbrePlaces() {
		return nbrePlaces;
	}

	/**
	 * @param nbrePlaces the nbrePlaces to set
	 */
	public void setNbrePlaces(int nbrePlaces) {
		this.nbrePlaces = nbrePlaces;
	}

	/**
	 * @return the designationVehicule
	 */
	public String getDesignationVehicule() {
		return designationVehicule;
	}

	/**
	 * @param designationVehicule the designationVehicule to set
	 */
	public void setDesignationVehicule(String designationVehicule) {
		this.designationVehicule = designationVehicule;
	}

	/**
	 * @return the dateAchat
	 */
	public LocalDate getDateAchat() {
		return dateAchat;
	}

	/**
	 * @param dateAchat the dateAchat to set
	 */
	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	// METHODE TOSTRING

	@Override
	public String toString() {
		return String.format(
				"Vehicule [getImmatriculation()=%s, getNbrePlaces()=%s, getDesignationVehicule()=%s, getDateAchat()=%s]",
				getImmatriculation(), getNbrePlaces(), getDesignationVehicule(), getDateAchat());
	}
	
}
