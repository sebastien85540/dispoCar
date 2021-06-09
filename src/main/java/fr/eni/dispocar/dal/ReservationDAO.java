package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dispocar.bo.Agence;
import fr.eni.dispocar.bo.Destination;
import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

public class ReservationDAO {
	
	private static final String SELECT_RESERVATIONS_ALL = "SELECT *  FROM Reservations"
			+ " INNER JOIN Reservation_Salarie ON id_reservation = reservation_id"
			+ "	INNER JOIN Salaries ON salarie_id = id_salarie"
			+ "	inner join Vehicules On immatriculation = vehicule"
			+ "	inner join Destinations on id_destination = destination"
			+ " ORDER BY id_reservation";
	private static final String SELECT_RESA_BY_ID = "SELECT *  FROM Reservations"
			+ " INNER JOIN Reservation_Salarie ON id_reservation = reservation_id"
			+ "	INNER JOIN Salaries ON salarie_id = id_salarie"
			+ "	INNER JOIN Vehicules On immatriculation = vehicule"
			+ "	INNER JOIN Destinations on id_destination = destination"
			+ "	WHERE id_reservation=?";
	private static final String INSERT_RESERVATION = "INSERT INTO Reservations (date_depart, date_retour, motif, designation, vehicule, depart, destination) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public List<Reservation> selectAllReservations() throws DALException{
		Connection cnx = null;
		Statement smtSelectAll = null;
		ResultSet rs = null;
		List<Reservation> reservations = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			rs = smtSelectAll.executeQuery(SELECT_RESERVATIONS_ALL);
			List<Salarie> salaries= null;
			int id = 0;
			
			
			while (rs.next()) {
				if(id != rs.getInt("id_reservation")) {
					Reservation resa = new Reservation();
					
					resa.setIdReservation(rs.getInt("id_reservation"));
					resa.setDateDebutReservation(LocalDateTime.of(rs.getDate("date_depart").toLocalDate(), rs.getTime("date_depart").toLocalTime()));
					resa.setDateFinReservation(LocalDateTime.of(rs.getDate("date_retour").toLocalDate(), rs.getTime("date_retour").toLocalTime()));
					resa.setMotifReservation(rs.getString("motif"));
					resa.setDescriptionReservation(rs.getString("designation"));
					
					Vehicule vehicule = new Vehicule();
					vehicule.setImmatriculation(rs.getString("immatriculation"));
					vehicule.setDesignationVehicule(rs.getString("description_vehicule"));
					vehicule.setNbrePlaces(rs.getInt("nbre_places"));
					vehicule.setDateAchat(rs.getDate("date_achat") != null ? rs.getDate("date_achat").toLocalDate() : null);
					resa.setVehiculeReservation(vehicule);
					Agence d = new Agence();
					d.setIdDestination(rs.getInt("id_destination"));
					d.setLibelleDestination(rs.getString("nom_destination"));
					d.setNumeroDestination(rs.getInt("numero"));
					d.setRueDestination(rs.getString("rue"));
					d.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : null);
					d.setCodePostal(rs.getInt("code_postal"));
					d.setVille(rs.getString("ville"));
					d.setAgence(rs.getBoolean("agence") == true);
					resa.setDepartReservation(d);
					
					Destination dest = new Destination();
					dest.setIdDestination(rs.getInt("id_destination"));
					dest.setLibelleDestination(rs.getString("nom_destination"));
					dest.setNumeroDestination(rs.getInt("numero"));
					dest.setRueDestination(rs.getString("rue"));
					dest.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : null);
					dest.setCodePostal(rs.getInt("code_postal"));
					dest.setVille(rs.getString("ville"));
					dest.setAgence(rs.getBoolean("agence"));
					resa.setArriveeDestination(dest);
					
					salaries = new ArrayList<Salarie>();
					Salarie sal = new Salarie();
					
					sal.setIdSalarie(rs.getInt("id_salarie"));
					sal.setNom(rs.getString("nom"));
					sal.setPrenom(rs.getString("prenom"));
					sal.setEmail(rs.getString("email"));
					sal.setTelephone(rs.getString("telephone"));
					sal.setPermisName(rs.getString("permis"));
					Agence agence = new Agence();
					agence.setIdDestination(rs.getInt("id_destination"));
					agence.setLibelleDestination(rs.getString("nom_destination"));
					agence.setNumeroDestination(rs.getInt("numero"));
					agence.setRueDestination(rs.getString("rue"));
					agence.setComplementDestination(rs.getString("complement_adresse")!= null ? rs.getString("complement_adresse") : null);
					agence.setCodePostal(rs.getInt("code_postal"));
					agence.setVille(rs.getString("ville"));
					agence.setAgence(rs.getBoolean("agence"));
					sal.setAgenceSalarie(agence);
					salaries.add(sal);
					resa.setReservataires(salaries);
					if (reservations == null) {
						reservations = new ArrayList<Reservation>();
					}
					reservations.add(resa);
					id = rs.getInt("id_reservation");
				} else {
					Salarie sal = new Salarie();
					
					sal.setIdSalarie(rs.getInt("id_salarie"));
					sal.setNom(rs.getString("nom"));
					sal.setPrenom(rs.getString("prenom"));
					sal.setEmail(rs.getString("email"));
					sal.setTelephone(rs.getString("telephone"));
					sal.setPermisName(rs.getString("permis"));
					Agence agence = new Agence();
					agence.setIdDestination(rs.getInt("id_destination"));
					agence.setLibelleDestination(rs.getString("nom_destination"));
					agence.setNumeroDestination(rs.getInt("numero"));
					agence.setRueDestination(rs.getString("rue"));
					agence.setComplementDestination(rs.getString("complement_adresse")!= null ? rs.getString("complement_adresse") : null);
					agence.setCodePostal(rs.getInt("code_postal"));
					agence.setVille(rs.getString("ville"));
					agence.setAgence(rs.getBoolean("agence"));
					sal.setAgenceSalarie(agence);
					salaries.add(sal);
				}	
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("La récupèration des données dans ReservationDAO c'est mal déroulée", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La fermeture des connexions n'a pas pu se faire dans selectAllReservations", e);
			}
		}
		
		return reservations;
	}
	
	/**
	 * Cette methode recupère une reservation grâce à son ID
	 * @param id
	 * @return 
	 * @throws DALException
	 */
	public Reservation selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtSelectId = null;
		Reservation resa = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectId = cnx.prepareStatement(SELECT_RESA_BY_ID);
			smtSelectId.setInt(1, id);
			ResultSet rs = smtSelectId.executeQuery();
			List<Salarie> salaries= null;
			int count = 0; 
			
			resa = new Reservation();
			while (rs.next()) {
				count += 1;
				if(count <= 1) {
					resa.setIdReservation(rs.getInt("id_reservation"));
					resa.setDateDebutReservation(LocalDateTime.of(rs.getDate("date_depart").toLocalDate(), rs.getTime("date_depart").toLocalTime()));
					resa.setDateFinReservation(LocalDateTime.of(rs.getDate("date_retour").toLocalDate(), rs.getTime("date_retour").toLocalTime()));
					resa.setMotifReservation(rs.getString("motif"));
					resa.setDescriptionReservation(rs.getString("designation"));
					
					Vehicule vehicule = new Vehicule();
					vehicule.setImmatriculation(rs.getString("immatriculation"));
					vehicule.setDesignationVehicule(rs.getString("description_vehicule"));
					vehicule.setNbrePlaces(rs.getInt("nbre_places"));
					vehicule.setDateAchat(rs.getDate("date_achat") != null ? rs.getDate("date_achat").toLocalDate() : null);
					resa.setVehiculeReservation(vehicule);
					
					Agence d = new Agence();
					d.setIdDestination(rs.getInt("id_destination"));
					d.setLibelleDestination(rs.getString("nom_destination"));
					d.setNumeroDestination(rs.getInt("numero"));
					d.setRueDestination(rs.getString("rue"));
					d.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : null);
					d.setCodePostal(rs.getInt("code_postal"));
					d.setVille(rs.getString("ville"));
					d.setAgence(rs.getBoolean("agence") == true);
					resa.setDepartReservation(d);
					
					Destination dest = new Destination();
					dest.setIdDestination(rs.getInt("id_destination"));
					dest.setLibelleDestination(rs.getString("nom_destination"));
					dest.setNumeroDestination(rs.getInt("numero"));
					dest.setRueDestination(rs.getString("rue"));
					dest.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : null);
					dest.setCodePostal(rs.getInt("code_postal"));
					dest.setVille(rs.getString("ville"));
					dest.setAgence(rs.getBoolean("agence"));
					resa.setArriveeDestination(dest);
					
					salaries = new ArrayList<Salarie>();
					Salarie sal = new Salarie();
					
					sal.setIdSalarie(rs.getInt("id_salarie"));
					sal.setNom(rs.getString("nom"));
					sal.setPrenom(rs.getString("prenom"));
					sal.setEmail(rs.getString("email"));
					sal.setTelephone(rs.getString("telephone"));
					sal.setPermisName(rs.getString("permis"));
					Agence agence = new Agence();
					agence.setIdDestination(rs.getInt("id_destination"));
					agence.setLibelleDestination(rs.getString("nom_destination"));
					agence.setNumeroDestination(rs.getInt("numero"));
					agence.setRueDestination(rs.getString("rue"));
					agence.setComplementDestination(rs.getString("complement_adresse")!= null ? rs.getString("complement_adresse") : null);
					agence.setCodePostal(rs.getInt("code_postal"));
					agence.setVille(rs.getString("ville"));
					agence.setAgence(rs.getBoolean("agence"));
					sal.setAgenceSalarie(agence);
					salaries.add(sal);
					resa.setReservataires(salaries);
				} else {
					Salarie sal = new Salarie();
					
					sal.setIdSalarie(rs.getInt("id_salarie"));
					sal.setNom(rs.getString("nom"));
					sal.setPrenom(rs.getString("prenom"));
					sal.setEmail(rs.getString("email"));
					sal.setTelephone(rs.getString("telephone"));
					sal.setPermisName(rs.getString("permis"));
					Agence agence = new Agence();
					agence.setIdDestination(rs.getInt("id_destination"));
					agence.setLibelleDestination(rs.getString("nom_destination"));
					agence.setNumeroDestination(rs.getInt("numero"));
					agence.setRueDestination(rs.getString("rue"));
					agence.setComplementDestination(rs.getString("complement_adresse")!= null ? rs.getString("complement_adresse") : null);
					agence.setCodePostal(rs.getInt("code_postal"));
					agence.setVille(rs.getString("ville"));
					agence.setAgence(rs.getBoolean("agence"));
					sal.setAgenceSalarie(agence);
					salaries.add(sal);
				}	
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur a eu lieu dans la DAL de Reservation", e);
		}finally {
			try {
				if (smtSelectId != null) {
					smtSelectId.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La connexion ne s'est pas stoppée dans DALReservation", e);
			}
		}
		return resa;
	}
	
	public void insertReservation(Reservation reservation) throws DALException {
		Connection cnx = null;
		PreparedStatement smtInsert = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtInsert = cnx.prepareStatement(INSERT_RESERVATION);
			smtInsert.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(reservation.getDateDebutReservation().toLocalDate(), reservation.getDateDebutReservation().toLocalTime())));
			smtInsert.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(reservation.getDateFinReservation().toLocalDate(), reservation.getDateFinReservation().toLocalTime())));
			smtInsert.setString(3, reservation.getMotifReservation());
			smtInsert.setString(4, reservation.getDescriptionReservation());
			smtInsert.setObject(5, reservation.getVehiculeReservation());
			smtInsert.setObject(6, reservation.getDepartReservation());
			smtInsert.setObject(7, reservation.getArriveeDestination());
			smtInsert.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de l'insertion d'une réservation", e);
		} finally {
			try {
				if (smtInsert != null) {
					smtInsert.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans insertReservation", e);
			}
		}
	}
}
