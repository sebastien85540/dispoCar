/**
 * 
 */
package fr.eni.dispocar.bo;

import java.io.File;
import java.time.LocalDate;

/**
 * @author sebastien
 * Cette classe est une extension de Vehicule, elle représente les vehicules personnels des salariés
 *
 */
public class VehiculePersonnel extends Vehicule{
	
	// ATTRIBUTS
	
	private String carteGriseName;
	private File carteGrise;
	private Salarie proprietaire;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public VehiculePersonnel() {
		super();
	}
	
	/**
	 * @param immatriculation
	 * @param designationVehicule
	 * @param nbrePlaces
	 * @param dateAchat
	 * @param typeVehicule
	 */
	public VehiculePersonnel(String immatriculation, String designationVehicule, int nbrePlaces, LocalDate dateAchat,
			String typeVehicule, String carteGriseName, File carteGrise, Salarie proprietaire) {
		super(immatriculation, designationVehicule, nbrePlaces, typeVehicule);
		setCarteGriseName(carteGriseName);
		setCarteGrise(carteGrise);
		setProprietaire(proprietaire);
	}

	/**
	 * @param immatriculation
	 * @param designationVehicule
	 * @param nbrePlaces
	 * @param typeVehicule
	 */
	public VehiculePersonnel(String immatriculation, String designationVehicule, int nbrePlaces, String typeVehicule, String carteGriseName, File carteGrise, Salarie proprietaire) {
		super(immatriculation, designationVehicule, nbrePlaces, typeVehicule);
		setCarteGriseName(carteGriseName);
		setCarteGrise(carteGrise);
		setProprietaire(proprietaire);
	}

	public VehiculePersonnel(String immatriculation, String designationVehicule, int nbrePlaces, String carteGriseName, String typeVehicule) {
		super(immatriculation, designationVehicule, nbrePlaces, typeVehicule);
		setCarteGriseName(carteGriseName);
	}

	// ASCESSEURS ET MUTATEURS
	/**
	 * @return the carteGriseName
	 */
	public String getCarteGriseName() {
		return carteGriseName;
	}
	/**
	 * @param carteGriseName the carteGriseName to set
	 */
	public void setCarteGriseName(String carteGriseName) {
		this.carteGriseName = carteGriseName;
	}
	/**
	 * @return the carteGrise
	 */
	public File getCarteGrise() {
		return carteGrise;
	}
	/**
	 * @param carteGrise the carteGrise to set
	 */
	public void setCarteGrise(File carteGrise) {
		this.carteGrise = carteGrise;
	}

	/**
	 * @return the proprietaire
	 */
	public Salarie getProprietaire() {
		return proprietaire;
	}

	/**
	 * @param proprietaire the proprietaire to set
	 */
	public void setProprietaire(Salarie proprietaire) {
		this.proprietaire = proprietaire;
	}

	
	// METHODE TOSTRING
	
	@Override
	public String toString() {
		return String.format(
				"VehiculePersonnel [getCarteGriseName()=%s, getCarteGrise()=%s, getProprietaire()=%s, toString()=%s]",
				getCarteGriseName(), getCarteGrise(), getProprietaire(), super.toString());
	}
}
