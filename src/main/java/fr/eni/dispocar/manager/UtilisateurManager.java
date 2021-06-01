/**
 * 
 */
package fr.eni.dispocar.manager;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

import fr.eni.dispocar.bo.Utilisateur;
import fr.eni.dispocar.dal.UtilisateurDAO;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.exception.ToolsException;


/**
 * @author sebastien
 * le manager sert à faire le lien entre la couche DAL et la couche CONTROLLER
 *
 */
public class UtilisateurManager {
	
	private UtilisateurDAO uDao;

	/**
	 * Ce constructeur instancie un nouvel utilisateur
	 * @param uDao
	 */
	public UtilisateurManager() {
		uDao = new UtilisateurDAO();
	}
	
	/**
	 * cette methode permet d'ajouter un utilisateur
	 * elle n'est utilisée que par l'utilisateur administrateur
	 * @param email
	 * @param password
	 * @param administrateur
	 * @throws ManagerException
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public Utilisateur ajouterUtilisateur(Utilisateur util) throws ManagerException, NoSuchAlgorithmException, InvalidKeySpecException {
		Utilisateur u = null;
		try {
			u = uDao.insertUtlisateur(util);
		} catch (DALException | ConnectionProviderException e) {
			throw new ManagerException("L'ajout de l'utilisateur s'est mal passé dans le Manager ", e);
		}
		return u;
	}
	
	/**
	 * Cette methode sert à mettre à jour un utilisateur
	 * @param util
	 * @throws ManagerException
	 * @throws ToolsException 
	 * @throws InvalidKeySpecException 
	 * @throws ConnectionProviderException 
	 * @throws DALException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void updateUtilisateur(Utilisateur util) throws ManagerException, InvalidKeySpecException, ToolsException, DALException, ConnectionProviderException, NoSuchAlgorithmException {
		
			uDao.updateUtilisateur(util);

	}
	
	/**
	 * Cette methode est utilisée pour supprimer un utilisateur
	 * @param util
	 * @throws ManagerException
	 */
	public void deleteUtilisateur(int idUtilisateur) throws ManagerException {
		try {
			uDao.deleteUtilisateur(idUtilisateur);
		} catch (ConnectionProviderException | DALException e) {
			throw new ManagerException("La suppression de l'utilisateur s'est mal déroulée dans le manager", e);
		}
	}
	
	public List<Utilisateur> selectAllUtilisateur() throws ManagerException, ConnectionProviderException, SQLException, DALException {
		List<Utilisateur> utilisateurs = uDao.selectAll();
		return utilisateurs;
	}
	
	public Utilisateur authentification(String email, String password) throws ManagerException, NoSuchAlgorithmException {
		Utilisateur util = null;
		try {
			util = uDao.authenticate(email, password);
		} catch (InvalidKeySpecException | DALException | ToolsException e) {
			throw new ManagerException("Les identifiants sont incorrects", e);
		}
		return util;
	}
	
	public Utilisateur selectUtilisateurById(int idUtilisateur) throws ManagerException {
		Utilisateur util = null;
		util = uDao.selectById(idUtilisateur);
		return util;
	}

}
