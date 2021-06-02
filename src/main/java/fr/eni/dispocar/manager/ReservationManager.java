/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.dal.ReservationDAO;

/**
 * @author sebastien
 *
 */
public class ReservationManager {
	private ReservationDAO resaDAO;
	
	

	/**
	 * Constructeur par defaut
	 */
	public ReservationManager() {
		resaDAO = new ReservationDAO();
	}



	public List<Reservation> selectAllReservations() {
		List<Reservation> reservations = null;
		reservations = resaDAO.selectAllReservations();
		return reservations;
	}

}
