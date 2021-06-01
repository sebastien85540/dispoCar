/**
 * 
 */
package fr.eni.dispocar.bo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sebastien
 * Cette classe contient le modèle de reservations 
 *
 */
public class Reservation {
	
	// ATTRIBUTS
	
	private Integer idReservation;
	private String motifReservation;
	private LocalDateTime dateDebutReservation;
	private LocalDateTime dateFinReservation;
	private String descriptionReservation;
	private Agence departReservation;
	private Destination arriveeDestination;
	private List<Salarie> reservataires;
	private Vehicule vehiculeReservation;
	
	// CONSTRUCTEURS
	
	/**
	 * Constructeur par defaut
	 */
	public Reservation() {
	}
	
	/**
	 * Constructeur surchargé avec id
	 * @param idReservation
	 * @param motifReservation
	 * @param dateDebutReservation
	 * @param dateFinReservation
	 * @param descriptionReservation
	 * @param departReservation
	 * @param arriveeDestination
	 * @param reservataires
	 * @param vehiculeReservation
	 */
	public Reservation(Integer idReservation, String motifReservation, LocalDateTime dateDebutReservation,
			LocalDateTime dateFinReservation, String descriptionReservation, Agence departReservation,
			Destination arriveeDestination, List<Salarie> reservataires, Vehicule vehiculeReservation) {
		this.idReservation = idReservation;
		this.motifReservation = motifReservation;
		this.dateDebutReservation = dateDebutReservation;
		this.dateFinReservation = dateFinReservation;
		this.descriptionReservation = descriptionReservation;
		this.departReservation = departReservation;
		this.arriveeDestination = arriveeDestination;
		this.reservataires = reservataires;
		this.vehiculeReservation = vehiculeReservation;
	}

	/**
	 * Constructeur surchargé sans id
	 * @param motifReservation
	 * @param dateDebutReservation
	 * @param dateFinReservation
	 * @param descriptionReservation
	 * @param departReservation
	 * @param arriveeDestination
	 * @param reservataires
	 * @param vehiculeReservation
	 */
	public Reservation(String motifReservation, LocalDateTime dateDebutReservation, LocalDateTime dateFinReservation,
			String descriptionReservation, Agence departReservation, Destination arriveeDestination,
			List<Salarie> reservataires, Vehicule vehiculeReservation) {
		this.motifReservation = motifReservation;
		this.dateDebutReservation = dateDebutReservation;
		this.dateFinReservation = dateFinReservation;
		this.descriptionReservation = descriptionReservation;
		this.departReservation = departReservation;
		this.arriveeDestination = arriveeDestination;
		this.reservataires = reservataires;
		this.vehiculeReservation = vehiculeReservation;
	}
	
	// ASCESSEURS ET MUTATEURS
	// permet l'encapsulation des attributs

	/**
	 * @return the idReservation
	 */
	public Integer getIdReservation() {
		return idReservation;
	}

	/**
	 * @param idReservation the idReservation to set
	 */
	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	/**
	 * @return the motifReservation
	 */
	public String getMotifReservation() {
		return motifReservation;
	}

	/**
	 * @param motifReservation the motifReservation to set
	 */
	public void setMotifReservation(String motifReservation) {
		this.motifReservation = motifReservation;
	}

	/**
	 * @return the dateDebutReservation
	 */
	public LocalDateTime getDateDebutReservation() {
		return dateDebutReservation;
	}

	/**
	 * @param dateDebutReservation the dateDebutReservation to set
	 */
	public void setDateDebutReservation(LocalDateTime dateDebutReservation) {
		this.dateDebutReservation = dateDebutReservation;
	}

	/**
	 * @return the dateFinReservation
	 */
	public LocalDateTime getDateFinReservation() {
		return dateFinReservation;
	}

	/**
	 * @param dateFinReservation the dateFinReservation to set
	 */
	public void setDateFinReservation(LocalDateTime dateFinReservation) {
		this.dateFinReservation = dateFinReservation;
	}

	/**
	 * @return the descriptionReservation
	 */
	public String getDescriptionReservation() {
		return descriptionReservation;
	}

	/**
	 * @param descriptionReservation the descriptionReservation to set
	 */
	public void setDescriptionReservation(String descriptionReservation) {
		this.descriptionReservation = descriptionReservation;
	}

	/**
	 * @return the departReservation
	 */
	public Agence getDepartReservation() {
		return departReservation;
	}

	/**
	 * @param departReservation the departReservation to set
	 */
	public void setDepartReservation(Agence departReservation) {
		this.departReservation = departReservation;
	}

	/**
	 * @return the arriveeDestination
	 */
	public Destination getArriveeDestination() {
		return arriveeDestination;
	}

	/**
	 * @param arriveeDestination the arriveeDestination to set
	 */
	public void setArriveeDestination(Destination arriveeDestination) {
		this.arriveeDestination = arriveeDestination;
	}

	/**
	 * @return the reservataires
	 */
	public List<Salarie> getReservataires() {
		return reservataires;
	}

	/**
	 * @param reservataires the reservataires to set
	 */
	public void setReservataires(List<Salarie> reservataires) {
		this.reservataires = reservataires;
	}

	/**
	 * @return the vehiculeReservation
	 */
	public Vehicule getVehiculeReservation() {
		return vehiculeReservation;
	}

	/**
	 * @param vehiculeReservation the vehiculeReservation to set
	 */
	public void setVehiculeReservation(Vehicule vehiculeReservation) {
		this.vehiculeReservation = vehiculeReservation;
	}
	
	// METHODE TOSTRING

	@Override
	public String toString() {
		return String.format(
				"Reservation [getIdReservation()=%s, getMotifReservation()=%s, getDateDebutReservation()=%s, getDateFinReservation()=%s, getDescriptionReservation()=%s, getDepartReservation()=%s, getArriveeDestination()=%s, getReservataires()=%s, getVehiculeReservation()=%s]",
				getIdReservation(), getMotifReservation(), getDateDebutReservation(), getDateFinReservation(),
				getDescriptionReservation(), getDepartReservation(), getArriveeDestination(), getReservataires(),
				getVehiculeReservation());
	}
	
}
