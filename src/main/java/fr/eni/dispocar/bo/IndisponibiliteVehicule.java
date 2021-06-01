/**
 * 
 */
package fr.eni.dispocar.bo;

import java.time.LocalDateTime;

/**
 * @author sebastien
 * Cette classe représente l'indisponibilite d'un vehicule precis
 *
 */
public class IndisponibiliteVehicule {
	
	// ATTRIBUTS
	
	private Integer idIndispoVehicule;
	private LocalDateTime dateDebutIndispo;
	private LocalDateTime dateFinIndispo;
	private Vehicule vehiculeIndispo;
	private Indisponibilite raisonIndispo;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public IndisponibiliteVehicule() {
	}

	/**
	 * Constructeur sans l'id
	 * @param dateDebutIndispo
	 * @param dateFinIndispo
	 * @param vehiculeIndispo
	 * @param raisonIndispo
	 */
	public IndisponibiliteVehicule(LocalDateTime dateDebutIndispo, LocalDateTime dateFinIndispo,
			Vehicule vehiculeIndispo, Indisponibilite raisonIndispo) {
		setDateDebutIndispo(dateDebutIndispo);
		setDateFinIndispo(dateFinIndispo);
		setVehiculeIndispo(vehiculeIndispo);
		setRaisonIndispo(raisonIndispo);
	}

	/**
	 * Constructeur avec l'id
	 * @param idIndispoVehicule
	 * @param dateDebutIndispo
	 * @param dateFinIndispo
	 * @param vehiculeIndispo
	 * @param raisonIndispo
	 */
	public IndisponibiliteVehicule(Integer idIndispoVehicule, LocalDateTime dateDebutIndispo,
			LocalDateTime dateFinIndispo, Vehicule vehiculeIndispo, Indisponibilite raisonIndispo) {
		setIdIndispoVehicule(idIndispoVehicule);
		setDateDebutIndispo(dateDebutIndispo);
		setDateFinIndispo(dateFinIndispo);
		setVehiculeIndispo(vehiculeIndispo);
		setRaisonIndispo(raisonIndispo);
	}
	
	// ASCESSEURS ET MUTATEURS
	// permet d'encapsuler les attributs
	
	/**
	 * @return the idIndispoVehicule
	 */
	public Integer getIdIndispoVehicule() {
		return idIndispoVehicule;
	}

	/**
	 * @param idIndispoVehicule the idIndispoVehicule to set
	 */
	public void setIdIndispoVehicule(Integer idIndispoVehicule) {
		this.idIndispoVehicule = idIndispoVehicule;
	}

	/**
	 * @return the dateDebutIndispo
	 */
	public LocalDateTime getDateDebutIndispo() {
		return dateDebutIndispo;
	}

	/**
	 * @param dateDebutIndispo the dateDebutIndispo to set
	 */
	public void setDateDebutIndispo(LocalDateTime dateDebutIndispo) {
		this.dateDebutIndispo = dateDebutIndispo;
	}

	/**
	 * @return the dateFinIndispo
	 */
	public LocalDateTime getDateFinIndispo() {
		return dateFinIndispo;
	}

	/**
	 * @param dateFinIndispo the dateFinIndispo to set
	 */
	public void setDateFinIndispo(LocalDateTime dateFinIndispo) {
		this.dateFinIndispo = dateFinIndispo;
	}

	/**
	 * @return the vehiculeIndispo
	 */
	public Vehicule getVehiculeIndispo() {
		return vehiculeIndispo;
	}

	/**
	 * @param vehiculeIndispo the vehiculeIndispo to set
	 */
	public void setVehiculeIndispo(Vehicule vehiculeIndispo) {
		this.vehiculeIndispo = vehiculeIndispo;
	}

	/**
	 * @return the raisonIndispo
	 */
	public Indisponibilite getRaisonIndispo() {
		return raisonIndispo;
	}

	/**
	 * @param raisonIndispo the raisonIndispo to set
	 */
	public void setRaisonIndispo(Indisponibilite raisonIndispo) {
		this.raisonIndispo = raisonIndispo;
	}
	// METHODE TOSTRING

	@Override
	public String toString() {
		return String.format(
				"IndisponibiliteVehicule [getIdIndispoVehicule()=%s, getDateDebutIndispo()=%s, getDateFinIndispo()=%s, getVehiculeIndispo()=%s, getRaisonIndispo()=%s]",
				getIdIndispoVehicule(), getDateDebutIndispo(), getDateFinIndispo(), getVehiculeIndispo(),
				getRaisonIndispo());
	}
	
	
}
