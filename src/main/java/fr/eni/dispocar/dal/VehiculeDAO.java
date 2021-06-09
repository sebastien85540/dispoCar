/**
 * 
 */
package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class VehiculeDAO {
	private static final String SELECT_ALL_VEHICULES = "SELECT * FROM Vehicules WHERE type_vehicule='VA'";
	private static final String INSERT_VEHICULE = "INSERT INTO Vehicules (immatriculation, description_vehicule, nbre_places, date_achat, type_vehicule) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_VEHICULE = "UPDATE FROM Vehicules SET description_vehicule=?, nbre_places=?, date_achat=?, type_vehicule=? WHERE immatriculation=?";
	private static final String DELETE_VEHICULE = "DELETE FROM Vehicules WHERE immatriculation=?";
	private static final String SELECT_VEHICULE_BY_ID = "SELECT * FROM Vehicules WHERE immatriculation=?";
	
	/**
	 * Cette methode récupère tous les vehicules de la base de données
	 * @return une liste des vehicules
	 * @throws DALException
	 */
	public List<Vehicule> selectAllVehicule() throws DALException {
		
		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		Connection cnx = null;
		Statement smtSelectAll = null;
		
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_VEHICULES);
			
			while (rs.next()) {
				Vehicule vehi = new Vehicule();
				vehi.setImmatriculation(rs.getString("immatriculation"));
				vehi.setDesignationVehicule(rs.getString("description_vehicule"));
				vehi.setNbrePlaces(rs.getInt("nbre_places"));
				vehi.setDateAchat(rs.getDate("date_achat").toLocalDate());
				vehi.setTypeVehicule(rs.getString("type_vehicule"));
				vehicules.add(vehi);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors du selectAll dans le vehicule", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture des connexions dans selectAllVehicules", e);
			}
		}
		return vehicules;
	}
	
	/**
	 * Cette methode insère un vehicule en base de données
	 * @param vehicule
	 * @throws DALException
	 */
	public void insertVehicule(Vehicule vehicule) throws DALException {
		Connection cnx = null;
		PreparedStatement stmInsertVehicule = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmInsertVehicule = cnx.prepareStatement(INSERT_VEHICULE, Statement.RETURN_GENERATED_KEYS);
			stmInsertVehicule.setString(1, vehicule.getImmatriculation());
			stmInsertVehicule.setString(2, vehicule.getDesignationVehicule());
			stmInsertVehicule.setInt(3, vehicule.getNbrePlaces());
			stmInsertVehicule.setDate(4, Date.valueOf(vehicule.getDateAchat()));
			stmInsertVehicule.setString(5, vehicule.getTypeVehicule());
			stmInsertVehicule.executeUpdate();

		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de l'insertion d'un vehicule dans le DAO", e);
		} finally {
			try {
				if (stmInsertVehicule != null) {
					stmInsertVehicule.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans insertVehicule", e);
			}
		}
		
	}
	
	/**
	 * Cette methode supprime un vehicule via son ID
	 * @param id
	 * @throws DALException
	 */
	public void deleteVehicule(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtDeleteVehicule = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtDeleteVehicule = cnx.prepareStatement(DELETE_VEHICULE);
			smtDeleteVehicule.setInt(1, id);
			smtDeleteVehicule.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la suppression d'un vehicule", e);
		} finally {
			try {
				if (smtDeleteVehicule != null) {
					smtDeleteVehicule.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion du deleteDestination", e);
			}
		}
	}
	
	/**
	 * Cette methode met à jour un vehicule
	 * @param id
	 * @param vehicule
	 * @throws DALException
	 */
	public void updateVehicule(int id, Vehicule vehicule) throws DALException {
		Connection cnx = null;
		PreparedStatement smtUpdateVehicule = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtUpdateVehicule = cnx.prepareStatement(UPDATE_VEHICULE);
			smtUpdateVehicule.setString(1, vehicule.getDesignationVehicule());
			smtUpdateVehicule.setInt(2, vehicule.getNbrePlaces());
			smtUpdateVehicule.setDate(3, Date.valueOf(vehicule.getDateAchat()));
			smtUpdateVehicule.setString(4, vehicule.getTypeVehicule());
			smtUpdateVehicule.setString(5, vehicule.getImmatriculation());
			smtUpdateVehicule.executeUpdate();
			
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la mise à jour d'un vehicule dans la DAO", e);
		} finally {
			try {
				if (smtUpdateVehicule != null) {
					smtUpdateVehicule.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans updateVehicule", e);
			}
		}
		
	}
	
	/**
	 * Cette methode retourne une destination via son ID
	 * @param id
	 * @return une destination spécifique
	 * @throws DALException
	 */
	public Vehicule selectByIdVehicule(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtSelectById = null;
		Vehicule vehi = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectById = cnx.prepareStatement(SELECT_VEHICULE_BY_ID);
			smtSelectById.setInt(1, id);
			ResultSet rs = smtSelectById.executeQuery();
			if (rs.next()) {
				vehi = new Vehicule(
						rs.getString("immatriculation"),
						rs.getString("description_vehicule"),
						rs.getInt("nbre_places"),
						rs.getDate("date_achat").toLocalDate(),
						rs.getString("type_vehicule")
						);		
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue dans la couche DAO deu selectByIdDestination", e);
		} finally {
			try {
				if (smtSelectById != null) {
					smtSelectById.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture des connexions dans la DAO selectByIdDestination", e);
			}
		}
		
		return vehi;
	}
}
