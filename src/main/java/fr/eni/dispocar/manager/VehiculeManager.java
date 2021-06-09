/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.dal.VehiculeDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 *
 */
public class VehiculeManager {
	private VehiculeDAO vehiDAO;

	/**
	 * 
	 */
	public VehiculeManager() {
		vehiDAO = new VehiculeDAO();
	}

	public List<Vehicule> selectAllVehicules() throws ManagerException {
		List<Vehicule> vehicules = null;
		try {
			vehicules = vehiDAO.selectAllVehicule();
		} catch (DALException e) {
			throw new ManagerException("Une erreur s'est produite dans le manager selectAllVehicules", e);
		}
		return vehicules;
	}

	public Vehicule selectById(int idVehicule) throws ManagerException {
		Vehicule vehi = null;
		try {
			vehi = vehiDAO.selectByIdVehicule(idVehicule);
		} catch (DALException e) {
			throw new ManagerException("Une erreur a eu lieu dans le manager de vehiculeById", e);
		}
		return vehi;
	}
	
	

}
