/**
 * 
 */
package fr.eni.dispocar.manager;

import java.util.List;

import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.dal.SalarieDAO;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;

/**
 * @author sebastien
 *
 */
public class SalarieManager {
	private SalarieDAO salDAO;

	/**
	 * Constructeur par defaut
	 */
	public SalarieManager() {
		salDAO = new SalarieDAO();
	}

	public List<Salarie> selectAllSalaries() throws ManagerException {
		List<Salarie> salaries = null;
		try {
			salaries = salDAO.selectAllSalaries();
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans le manager du selectAll de salariés", e);
		}
		return salaries;
	}

	public Salarie selectById(int id) throws ManagerException {
		Salarie salarie = null;
		try {
			salarie = salDAO.selectSalarieById(id);
		} catch (DALException e) {
			throw new ManagerException("Une erreur est survenue dans la manager de SalarieById", e);
		}
		return salarie;
	}
	
}
