/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.IndisponibiliteVehicule;
import fr.eni.dispocar.dal.IndispoVehiculeDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 *
 */
public class IndisponibiliteVehiculeManager {
	private IndispoVehiculeDAO indispoDAO;

	/**
	 * 
	 */
	public IndisponibiliteVehiculeManager() {
		indispoDAO = new IndispoVehiculeDAO();
	}

	public List<IndisponibiliteVehicule> selectAllVehicules() throws ManagerException {
		List<IndisponibiliteVehicule> indisponibilites = null;
		try {
			indisponibilites = indispoDAO.selectAllVehiculesIndispo();
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager de selectAllVehiculesIndispo", e);
		}
		return indisponibilites;
	}
	
	

}
