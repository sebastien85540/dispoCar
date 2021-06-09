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
 * ce classe contient le manager du calendrier
 *
 */
public class FullCalendarManager {
	
	private ReservationDAO calendarDAO;

	/**
	 * @param calendarDAO
	 */
	public FullCalendarManager() {
		calendarDAO = new ReservationDAO();
	}
	
	/**
	 * Cette methode recupère dans la liste de reservation donnée par la DAO
	 * @return une liste des reservations
	 * @throws ManagerException
	 */
	public List<Reservation> selestAllFullCalendar() throws ManagerException{
		List<Reservation> reservations = null;
		try {
			reservations = calendarDAO.selectAllReservations();
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager FullCalendar", e);
		}
		return reservations;
	}
	
	public Reservation selectById(int id) throws ManagerException {
		Reservation reservation = null;
		try {
			reservation = calendarDAO.selectById(id);
		} catch (DALException e) {
			throw new ManagerException("recuperation impossible dans le manager FullCalendar", e);
		}
		return reservation ;
	}

}
