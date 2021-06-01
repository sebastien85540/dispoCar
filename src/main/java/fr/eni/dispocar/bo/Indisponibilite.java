/**
 * 
 */
package fr.eni.dispocar.bo;

/**
 * @author sebastien
 * contient le modele d'indisponibilite
 * - idIndisponibilite sera compose de 3 lettres, diminutif de l'indisponibilite
 * - libelleIndispo contient le nom de l'indisponibilite
 *
 */
public class Indisponibilite {
	
	// ATTRIBUTS
	
	private String idIndisponibilite;
	private String libelleIndisponibilite;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public Indisponibilite() {
	}
	
	/**
	 * @param idIndisponibilite
	 * @param libelleIndisponibilite
	 */
	public Indisponibilite(String idIndisponibilite, String libelleIndisponibilite) {
		setIdIndisponibilite(idIndisponibilite);
		setLibelleIndisponibilite(libelleIndisponibilite);
	}
	
	// GETTERS ET SETTERS
	// Permettent l'encapsulation des attributs
	
	/**
	 * @return the idIndisponibilite
	 */
	public String getIdIndisponibilite() {
		return idIndisponibilite;
	}
	/**
	 * @param idIndisponibilite the idIndisponibilite to set
	 */
	public void setIdIndisponibilite(String idIndisponibilite) {
		this.idIndisponibilite = idIndisponibilite;
	}
	/**
	 * @return the libelleIndisponibilite
	 */
	public String getLibelleIndisponibilite() {
		return libelleIndisponibilite;
	}
	/**
	 * @param libelleIndisponibilite the libelleIndisponibilite to set
	 */
	public void setLibelleIndisponibilite(String libelleIndisponibilite) {
		this.libelleIndisponibilite = libelleIndisponibilite;
	}
	
	// METHODE TO STRING

	@Override
	public String toString() {
		return String.format("Indisponibilite [getIdIndisponibilite()=%s, getLibelleIndisponibilite()=%s]",
				getIdIndisponibilite(), getLibelleIndisponibilite());
	}
	
}
