/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Agence;
import fr.eni.dispocar.dal.AgenceDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 *
 */
public class AgenceManager {
	private AgenceDAO agenceDAO;

	/**
	 * 
	 */
	public AgenceManager() {
		agenceDAO = new AgenceDAO();
	}
	
	public List<Agence> selectAllAgences() throws ManagerException{
		List<Agence> agences = null;
		try {
			agences = agenceDAO.selectAll();
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue lors du passage dans la manager SelectAllAgences", e);
		}
		return agences;
	}

	public Agence selectById(int idAgence) throws ManagerException {
		Agence agence = null;
		try {
			agence = agenceDAO.selectByIdAgence(idAgence);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager selectByIdAgence", e);
		}
		return agence;
	}
}
