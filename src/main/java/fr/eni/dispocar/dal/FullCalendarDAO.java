/**
 * 
 */
package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.bo.ReservationFullCalendar;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 * cette classe contient toutes les methodes pour aller chercher les informations pour mon calendrier
 *
 */
public class FullCalendarDAO {
	
	private static final String SELECT_ALL_RESERVATIONS = "SELECT id_reservation, date_depart, date_retour, vehicule, Salaries.prenom  FROM Reservations \r\n"
			+ "INNER JOIN Reservation_Salarie ON id_reservation = reservation_id\r\n"
			+ "INNER JOIN Salaries ON salarie_id = id_salarie";
	private static final String SELECT_ONE_RESERVATION = "SELECT (id_reservation, date_depart, date_retour, motif) FROM Reservations WHERE id_reservation=?";
	//private static final String
	
	public List<ReservationFullCalendar> selectAll() throws DALException, ConnectionProviderException{
		Connection cnx = null;
		Statement smtSelectAll = null;
		List<ReservationFullCalendar> reservations = new ArrayList<ReservationFullCalendar>();
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_RESERVATIONS);
			
			while (rs.next()) {
				
				ReservationFullCalendar resa;
				resa = new ReservationFullCalendar();
				
				resa.setUrl("/resa?id=" + String.valueOf(rs.getInt("id_reservation")));
				resa.setStart(rs.getString("date_depart"));
				resa.setEnd(rs.getString("date_retour"));
				resa.setTitle(rs.getString("Salaries.prenom") + rs.getString("motif"));
				reservations.add(resa);
				System.out.println(resa);
			}
		} catch (SQLException e) {
			throw new DALException("Une erreur est survenue dans la DAO ReservationCalendar", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La connexion ne s'est pas fermée", e);
			}
		}
		return reservations;
		
	}

}
