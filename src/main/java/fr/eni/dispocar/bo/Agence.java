/**
 * 
 */
package fr.eni.dispocar.bo;

import java.util.List;

/**
 * @author sebastien
 * Cette classe contient le modele d'agence
 *
 */
public class Agence extends Destination{
	
	// ATTRIBUTS
	private String nomAgence;
	private List<Vehicule> vehiculesAgence;
	
	// CONSTRUCTEUR

	/**
	 * Constructeur par defaut
	 */
	public Agence() {
		super();
	}

	/**
	 * Constructeur surchargé avec les attributs obligatoires
	 * @param nomAgence
	 * @param numeroDestination
	 * @param rueDestination
	 * @param codePostal
	 * @param ville
	 * @param vehiculesAgence
	 */
	public Agence(String  nomAgence,int numeroDestination, String rueDestination, int codePostal, String ville, List<Vehicule> vehiculesAgence) {
		super(numeroDestination, rueDestination, codePostal, ville);
		setNomAgence(nomAgence);
		setVehiculesAgence(vehiculesAgence);
	}

	/**
	 * Constructeur surchargé avec tous les attributs sauf l'ID
	 * @param idDestination
	 * @param nomAgence
	 * @param libelleDestination
	 * @param numeroDestination
	 * @param rueDestination
	 * @param complementDestination
	 * @param codePostal
	 * @param ville
	 * @param vehiculesAgence
	 */
	public Agence(Integer idDestination,String  nomAgence, String libelleDestination, int numeroDestination, String rueDestination,
			String complementDestination, int codePostal, String ville, List<Vehicule> vehiculesAgence) {
		super(idDestination, libelleDestination, numeroDestination, rueDestination, complementDestination, codePostal, ville);
		setNomAgence(nomAgence);
		setVehiculesAgence(vehiculesAgence);
	}

	/**
	 * Constructeur surchargé avec tous les attributs
	 * @param nomAgence
	 * @param libelleDestination
	 * @param numeroDestination
	 * @param rueDestination
	 * @param complementDestination
	 * @param codePostal
	 * @param ville
	 * @param vehiculesAgence
	 */
	public Agence(String  nomAgence,String libelleDestination, int numeroDestination, String rueDestination, String complementDestination,
			int codePostal, String ville, List<Vehicule> vehiculesAgence) {
		super(libelleDestination, numeroDestination, rueDestination, complementDestination, codePostal, ville);
		setNomAgence(nomAgence);
		setVehiculesAgence(vehiculesAgence);
	}
	
	// GETTERS ET SETTERS

	/**
	 * @return the nomAgence
	 */
	public String getNomAgence() {
		return nomAgence;
	}

	/**
	 * @param nomAgence the nomAgence to set
	 */
	public void setNomAgence(String nomAgence) {
		this.nomAgence = nomAgence;
	}
	

	/**
	 * @return the vehiculesAgence
	 */
	public List<Vehicule> getVehiculesAgence() {
		return vehiculesAgence;
	}

	/**
	 * @param vehiculesAgence the vehiculesAgence to set
	 */
	public void setVehiculesAgence(List<Vehicule> vehiculesAgence) {
		this.vehiculesAgence = vehiculesAgence;
	}
	
	// METHODE TO STRING

	@Override
	public String toString() {
		return String.format("Agence [getNomAgence()=%s, getVehiculesAgence()=%s, toString()=%s]", getNomAgence(),
				getVehiculesAgence(), super.toString());
	}
	
}
