/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.dal.ReservationDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

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



	public List<Reservation> selectAllReservations() throws ManagerException {
		List<Reservation> reservations = null;
		try {
			reservations = resaDAO.selectAllReservations();
		} catch (DALException e) {
			throw new ManagerException("La récuperation de la liste des re'servations s'est mal passée", e);
		}
		return reservations;
	}

}
