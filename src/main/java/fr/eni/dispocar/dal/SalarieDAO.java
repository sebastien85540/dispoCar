/**
 * 
 */
package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dispocar.bo.Agence;
import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class SalarieDAO {
	private static final String SELECT_ALL_SALARIES = "SELECT * FROM Salaries "
			+ "INNER JOIN Destinations ON id_destination = Salaries.agence";
	private static final String INSERT_SALARIE = "INSERT INTO Salaries (nom, prenom, email, telephone, permis, agence) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SALARIE = "UPDATE Salaries SET nom=?, prenom=?, email=?, telephone=?, permis=?, agence=? WHERE id_salarie=?";
	private static final String DELETE_SALARIE = "DELETE FROM Salaries WHERE id_salarie";
	private static final String SELECT_BY_ID_SALARIE = "SELECT * FROM Salaries INNER JOIN Destinations ON agence = id_destination WHERE id_salarie=?";
	
	/**
	 * Cette methode récupère tous les salariés dans la base de données
	 * @return une liste de salariés
	 * @throws DALException
	 */
	public List<Salarie> selectAllSalaries() throws DALException {
		List<Salarie> salaries = new ArrayList<Salarie>();
		Connection cnx = null;
		Statement smtSelectAll = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_SALARIES);
			while (rs.next()) {
				Salarie salarie = new Salarie();
				salarie.setIdSalarie(rs.getInt("id_salarie"));
				salarie.setNom(rs.getString("nom"));
				salarie.setPrenom(rs.getString("prenom"));
				salarie.setEmail(rs.getString("email"));
				salarie.setTelephone(rs.getString("telephone"));
				salarie.setPermisName(rs.getString("permis"));
				Agence agence = new Agence();
				agence.setIdDestination(rs.getInt("id_destination"));
				agence.setLibelleDestination(rs.getString("nom_destination"));
				agence.setNumeroDestination(rs.getInt("numero"));
				agence.setRueDestination(rs.getString("rue"));
				agence.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : "");
				agence.setVille(rs.getString("ville"));
				agence.setAgence(rs.getBoolean("agence"));
				salarie.setAgenceSalarie(agence);
				salaries.add(salarie);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la récupération de la liste des salaries dans SelectAllSalaries", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans le selectAllSalaries", e);
			}
		}
		return salaries;
	}
	
	/**
	 * Cette methode crée un salarié dans la base de données
	 * @param salarie
	 * @throws DALException
	 */
	public void insertSalarie(Salarie salarie) throws DALException {
		Connection cnx = null;
		PreparedStatement smtinsertSalarie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtinsertSalarie = cnx.prepareStatement(INSERT_SALARIE, Statement.RETURN_GENERATED_KEYS);
			smtinsertSalarie.setString(1, salarie.getNom());
			smtinsertSalarie.setString(2, salarie.getPrenom());
			smtinsertSalarie.setString(3, salarie.getEmail());
			smtinsertSalarie.setString(4, salarie.getTelephone());
			smtinsertSalarie.setString(5, salarie.getPermisName());
			Agence agence = new Agence();
			agence.getIdDestination();
			agence.getLibelleDestination();
			agence.getNumeroDestination();
			agence.getRueDestination();
			agence.getComplementDestination();
			agence.getCodePostal();
			agence.getVille();
			agence.getAgence();
			salarie.setAgenceSalarie(agence);
			smtinsertSalarie.setObject(6, salarie.getAgenceSalarie());
			smtinsertSalarie.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de l'insertion d'un salarié", e);
		} finally {
			try {
				if (smtinsertSalarie != null) {
					smtinsertSalarie.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion de l'insertSalarie", e);
			}
		}
	}
	
	/**
	 * Cette methode met à jour un salarié
	 * @param id
	 * @param salarie
	 * @throws DALException
	 */
	public void updateSalarie(int id, Salarie salarie) throws DALException {
		Connection cnx = null;
		PreparedStatement smtUpdateSalarie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtUpdateSalarie = cnx.prepareStatement(UPDATE_SALARIE);
			smtUpdateSalarie = cnx.prepareStatement(INSERT_SALARIE, Statement.RETURN_GENERATED_KEYS);
			smtUpdateSalarie.setString(1, salarie.getNom());
			smtUpdateSalarie.setString(2, salarie.getPrenom());
			smtUpdateSalarie.setString(3, salarie.getEmail());
			smtUpdateSalarie.setString(4, salarie.getTelephone());
			smtUpdateSalarie.setString(5, salarie.getPermisName());
			Agence agence = new Agence();
			agence.getIdDestination();
			agence.getLibelleDestination();
			agence.getNumeroDestination();
			agence.getRueDestination();
			agence.getComplementDestination();
			agence.getCodePostal();
			agence.getVille();
			agence.getAgence();
			salarie.setAgenceSalarie(agence);
			smtUpdateSalarie.setObject(6, salarie.getAgenceSalarie());
			smtUpdateSalarie.setInt(7, id);
			smtUpdateSalarie.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue dans l'update du SalarieDAO", e);
		} finally {
			try {
				if (smtUpdateSalarie != null) {
					smtUpdateSalarie.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans la DAOUpdateSalarie", e);
			}
		}
	}
	
	/**
	 * Cette methode supprime un Salarie
	 * @param id
	 * @throws DALException
	 */
	public void deleteSalarie(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtDeleteSalarie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtDeleteSalarie = cnx.prepareStatement(DELETE_SALARIE);
			smtDeleteSalarie.setInt(1, id);
			smtDeleteSalarie.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur a eu lieu dans la supression d'un salarie au niveau DAO", e);
		} finally {
			try {
				if (smtDeleteSalarie != null) {
					smtDeleteSalarie.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue dans la fermeture de la connexion du deleteDAO", e);
			}
		}
	}
	
	/**
	 * Cette methode récupère tous les salariés dans la base de données
	 * @return une liste de salariés
	 * @throws DALException
	 */
	public Salarie selectSalarieById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtSelectById = null;
		Salarie salarie = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectById = cnx.prepareStatement(SELECT_BY_ID_SALARIE);
			smtSelectById.setInt(1, id);
			ResultSet rs = smtSelectById.executeQuery();
			
			if (rs.next()) {
				salarie = new Salarie();
						salarie.setIdSalarie(rs.getInt("id_salarie"));
						salarie.setNom(rs.getString("nom"));
						salarie.setPrenom(rs.getString("prenom"));
						salarie.setEmail(rs.getString("email"));
						salarie.setTelephone(rs.getString("telephone"));
						salarie.setPermisName(rs.getString("permis"));
						Agence agence = new Agence();
						agence.setIdDestination(rs.getInt("id_destination"));
						agence.setLibelleDestination(rs.getString("nom_destination"));
						agence.setNumeroDestination(rs.getInt("numero"));
						agence.setRueDestination(rs.getString("rue"));
						agence.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : "");
						agence.setVille(rs.getString("ville"));
						agence.setAgence(rs.getBoolean("agence"));
						salarie.setAgenceSalarie(agence);
						
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la récupération de la liste des salaries dans SelectAllSalaries", e);
		} finally {
			try {
				if (smtSelectById != null) {
					smtSelectById.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans le selectAllSalaries", e);
			}
		}
		return salarie;
	}
}
