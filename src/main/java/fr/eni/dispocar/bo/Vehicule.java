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
	private String designationVehicule;
	private int nbrePlaces;
	private LocalDate dateAchat;
	private String typeVehicule;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public Vehicule() {
	}

	/**
	 * Constructeur surchargé avec les attributs obligatoires
	 * @param immatriculation
	 * @param designationVehicule
	 * @param nbrePlaces
	 * @param typeVehicule
	 */
	public Vehicule(String immatriculation, String designationVehicule, int nbrePlaces, String typeVehicule) {
		setImmatriculation(immatriculation);
		setDesignationVehicule(designationVehicule);
		setNbrePlaces(nbrePlaces);
		setTypeVehicule(typeVehicule);
	}

	// ASCESSEURS ET MUTATEURS
	// ça permet l'encapsulation des attributs

	public Vehicule(String immatriculation, String designationVehicule, int nbrePlaces, LocalDate dateAchat, String typeVehicule) {
		setImmatriculation(immatriculation);
		setDesignationVehicule(designationVehicule);
		setNbrePlaces(nbrePlaces);
		setDateAchat(dateAchat);
		setTypeVehicule(typeVehicule);
	}

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

	/**
	 * @return the typeVehicule
	 */
	public String getTypeVehicule() {
		return typeVehicule;
	}

	/**
	 * @param typeVehicule the typeVehicule to set
	 */
	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}
	
	
	// METHODE TOSTRING
	

	@Override
	public String toString() {
		return String.format(
				"Vehicule [getImmatriculation()=%s, getNbrePlaces()=%s, getDesignationVehicule()=%s, getDateAchat()=%s, getTypeVehicule()=%s]",
				getImmatriculation(), getNbrePlaces(), getDesignationVehicule(), getDateAchat(), getTypeVehicule());
	}
}
