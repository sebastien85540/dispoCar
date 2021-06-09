/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Indisponibilite;
import fr.eni.dispocar.dal.IndisponibiliteDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 *
 */
public class IndisponibiliteManager {
	private IndisponibiliteDAO indispoDAO;
	
	//CONSTRUCTEUR
	
	/**
	 * 
	 */
	public IndisponibiliteManager() {
		indispoDAO = new IndisponibiliteDAO();
	}
	

	public List<Indisponibilite> selectAllIndispo() throws ManagerException {
		List<Indisponibilite> indisponibilites = null;
		try {
			indisponibilites = indispoDAO.selectAllIndispo();
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans la manager de Indisponibilites du selectAll", e);
		}
		return indisponibilites;
	}


	public void insertIndispo(Indisponibilite indispo) throws ManagerException {
		try {
			indispoDAO.insertIndispo(indispo);
		} catch (DALException e) {
			throw new ManagerException("Le passage dans le manager insert Indisponibilité s'est mal passé", e);
		}
	}


	public void indispoDelete(String id) throws ManagerException {
		try {
			indispoDAO.indispoDelete(id);
		} catch (DALException e) {
			throw new ManagerException("Une erreur a eu lieu dans la partie Manager de la suppression d'une indisponibilite", e);
		}
		
	}


	public void indispoUpdate(String id, Indisponibilite indispo) throws ManagerException {
		try {
			indispoDAO.updateIndispo(id, indispo);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager de Indisponibilite update", e);
		}
	}


	public Indisponibilite selectById(String idIndispo) throws ManagerException {
		Indisponibilite indispoSelectById = null;
		try {
			indispoSelectById = indispoDAO.indispoSelectById(idIndispo);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager de la recuperation d'une indisponibilite", e);
		}
		return indispoSelectById;
	}
}
