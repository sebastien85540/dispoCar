/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.ReservationFullCalendar;
import fr.eni.dispocar.dal.FullCalendarDAO;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 * ce classe contient le manager du calendrier
 *
 */
public class FullCalendarManager {
	
	private FullCalendarDAO calendarDAO;

	/**
	 * @param calendarDAO
	 */
	public FullCalendarManager() {
		calendarDAO = new FullCalendarDAO();
	}
	
	/**
	 * Cette methode recupère dans la liste de reservation donnée par la DAO
	 * @return une liste des reservations
	 * @throws ManagerException
	 */
	public List<ReservationFullCalendar> selestAllFullCalendar() throws ManagerException{
		List<ReservationFullCalendar> reservations = null;
		try {
			reservations = calendarDAO.selectAll();
		} catch (DALException | ConnectionProviderException e) {
			throw new ManagerException("Une erreur est survenue dans le manager FullCalendar", e);
		}
		return reservations;
	}

}
