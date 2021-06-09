/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Destination;
import fr.eni.dispocar.dal.DestinationDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 *
 */
public class DestinationManager {
	
	private DestinationDAO destiDAO;
	
	

	/**
	 * 
	 */
	public DestinationManager() {
		destiDAO = new DestinationDAO();
	}


	/**
	 * methode selectAll
	 * @return
	 * @throws ManagerException
	 */
	public List<Destination> selectAllDestination() throws ManagerException {
		List<Destination> destinations = null;
		try {
			destinations = destiDAO.selectAllDestination();
		} catch (DALException e) {
			throw new ManagerException("une erreur est survenue dans le manager du selectAllDestinations", e);
		}
		return destinations;
	}
	
	/**
	 * methode insert
	 * @param destination
	 * @throws ManagerException
	 */
	public void insertDestination(Destination destination) throws ManagerException {
		try {
			destiDAO.insertDestination(destination);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager de l'insertDestination", e);
		}
	}
	
	/**
	 * methode delete
	 * @param id
	 * @throws ManagerException
	 */
	public void deleteDestination(int id) throws ManagerException {
		try {
			destiDAO.deleteDestination(id);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager de deleteDestination", e);
		}
	}
	
	/**
	 * methode update
	 * @param id
	 * @param destination
	 * @throws ManagerException
	 */
	public void updateDestination(int id, Destination destination) throws ManagerException {
		try {
			destiDAO.updateDestination(id, destination);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager updateDestination", e);
		}
	}
	
	/**
	 * methode selectById
	 * @param id
	 * @return
	 * @throws ManagerException
	 */
	public Destination selectById(int id) throws ManagerException {
		Destination desti = null;
		try {
			desti = destiDAO.selectByIdDestination(id);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager du selectByIdDestination", e);
		}
		return desti;
	}
}
