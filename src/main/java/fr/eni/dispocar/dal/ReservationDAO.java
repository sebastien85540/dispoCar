package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					
					Destination dest = new Destination();
					dest.setIdDestination(rs.getInt("id_destination"));
					dest.setLibelleDestination(rs.getString("nom_destination"));
					dest.setNumeroDestination(rs.getInt("numero"));
					dest.setRueDestination(rs.getString("rue"));
					dest.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : null);
					dest.setCodePostal(rs.getInt("code_postal"));
					dest.setVille(rs.getString("ville"));
					dest.setAgence(rs.getBoolean("")B);
					resa.setArriveeDestination(dest);
					
					salaries = new ArrayList<Salarie>();
					Salarie sal = new Salarie();
					
					sal.setIdSalarie(rs.getInt("id_salarie"));
					sal.setNom(rs.getString("nom"));
					sal.setPrenom(rs.getString("prenom"));
					//.....
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
					//.....
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

}
