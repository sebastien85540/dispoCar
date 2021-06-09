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

import fr.eni.dispocar.bo.VehiculePersonnel;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class VehiculePersoDAO{
	private static final String SELECT_ALL_VEHICULES = "SELECT * FROM Vehicules WHERE type_vehicule='VP'";
	private static final String INSERT_VEHICULE = "INSERT INTO Vehicules (immatriculation, description_vehicule, nbre_places, carte_grise_name, type_vehicule) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_VEHICULE = "UPDATE FROM Vehicules SET description_vehicule=?, nbre_places=?, carte_grise_name=?, type_vehicule=? WHERE immatriculation=?";
	private static final String DELETE_VEHICULE = "DELETE FROM Vehicules WHERE immatriculation=?";
	private static final String SELECT_VEHICULE_BY_ID = "SELECT * FROM Vehicules WHERE immatriculation=?";
	
	/**
	 * Cette methode récupère tous les vehicules personnels de la base de données
	 * @return une liste des vehicules personnels
	 * @throws DALException
	 */
	public List<VehiculePersonnel> selectAllVehiculePerso() throws DALException {
		
		List<VehiculePersonnel> vehicules = new ArrayList<VehiculePersonnel>();
		Connection cnx = null;
		Statement smtSelectAll = null;
		
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_VEHICULES);
			
			while (rs.next()) {
				VehiculePersonnel vehi = new VehiculePersonnel();
				vehi.setImmatriculation(rs.getString("immatriculation"));
				vehi.setDesignationVehicule(rs.getString("description_vehicule"));
				vehi.setNbrePlaces(rs.getInt("nbre_places"));
				vehi.setCarteGriseName(rs.getString("carte_grise_name"));
				vehi.setTypeVehicule(rs.getString("type_vehicule"));
				vehicules.add(vehi);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors du selectAll dans le vehiculePerso", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture des connexions dans selectAllVehiculesPerso", e);
			}
		}
		return vehicules;
	}
	
	/**
	 * Cette methode insère un vehicule personnel en base de données
	 * @param vehicule personnel
	 * @throws DALException
	 */
	public void insertVehiculePersonnel(VehiculePersonnel vehicule) throws DALException {
		Connection cnx = null;
		PreparedStatement stmInsertVehicule = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmInsertVehicule = cnx.prepareStatement(INSERT_VEHICULE, Statement.RETURN_GENERATED_KEYS);
			stmInsertVehicule.setString(1, vehicule.getImmatriculation());
			stmInsertVehicule.setString(2, vehicule.getDesignationVehicule());
			stmInsertVehicule.setInt(3, vehicule.getNbrePlaces());
			stmInsertVehicule.setString(4, vehicule.getCarteGriseName());
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
	 * Cette methode supprimme un vehicule personnel via son ID
	 * @param id
	 * @throws DALException
	 */
	public void deleteVehiculePersonnel(int id) throws DALException {
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
	 * Cette methode met à jour un vehicule personnel
	 * @param id
	 * @param vehicule
	 * @throws DALException
	 */
	public void updateVehiculePersonnel(int id, VehiculePersonnel vehicule) throws DALException {
		Connection cnx = null;
		PreparedStatement smtUpdateVehicule = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtUpdateVehicule = cnx.prepareStatement(UPDATE_VEHICULE);
			smtUpdateVehicule.setString(1, vehicule.getDesignationVehicule());
			smtUpdateVehicule.setInt(2, vehicule.getNbrePlaces());
			smtUpdateVehicule.setString(3, vehicule.getCarteGriseName());
			smtUpdateVehicule.setString(4, vehicule.getTypeVehicule());
			smtUpdateVehicule.setString(5, vehicule.getImmatriculation());
			smtUpdateVehicule.executeUpdate();
			
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la mise à jour d'un vehicule personnel dans la DAO", e);
		} finally {
			try {
				if (smtUpdateVehicule != null) {
					smtUpdateVehicule.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans updateVehiculePersonnel", e);
			}
		}
	}
	
	/**
	 * Cette methode retourne un vehicule personnel via son ID
	 * @param id
	 * @return une destination spécifique
	 * @throws DALException
	 */
	public VehiculePersonnel selectByIdVehicule(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtSelectById = null;
		VehiculePersonnel vehi = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectById = cnx.prepareStatement(SELECT_VEHICULE_BY_ID);
			smtSelectById.setInt(1, id);
			ResultSet rs = smtSelectById.executeQuery();
			if (rs.next()) {
				vehi = new VehiculePersonnel(
						rs.getString("immatriculation"),
						rs.getString("description_vehicule"),
						rs.getInt("nbre_places"),
						rs.getString("carte_grise_name"),
						rs.getString("type_vehicule")
						);		
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue dans la couche DAO de selectByIdVehiculePersonnel", e);
		} finally {
			try {
				if (smtSelectById != null) {
					smtSelectById.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture des connexions dans la DAO selectByIdVehiculePersonnel", e);
			}
		}
		return vehi;
	}
}
