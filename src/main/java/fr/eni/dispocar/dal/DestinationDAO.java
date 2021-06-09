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

import fr.eni.dispocar.bo.Destination;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class DestinationDAO {

	private static final String SELECT_ALL_DESTINATIONS = "SELECT * FROM Destinations ORDER BY id_destination";
	private static final String INSERT_DESTINATION = "INSERT INTO Destinations (nom_destination, numero, rue, complement_adresse, code_postal, ville, agence) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_DESTINATION = "DELETE FROM Destinations WHERE id_destination=?";
	private static final String UPDATE_DESTINATION ="UPDATE Destinations SET nom_destination=?, numero=?, rue=?, complement_adresse=?, code_postal=?, ville=?, agence=? WHERE id_destination=?";
	private static final String SELECT_DESTINATION_BY_ID = "SELECT * FROM Destinations WHERE id_destination=?";
	
	/**
	 * Cette methode récupère toutes les destinations de la base de données
	 * @return une liste des destinations
	 * @throws DALException
	 */
	public List<Destination> selectAllDestination() throws DALException {
		
		List<Destination> destinations = new ArrayList<Destination>();
		Connection cnx = null;
		Statement smtSelectAll = null;
		
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_DESTINATIONS);
			
			while (rs.next()) {
				Destination desti = new Destination();
				desti.setIdDestination(rs.getInt("id_destination"));
				desti.setLibelleDestination(rs.getString("nom_destination"));
				desti.setNumeroDestination(rs.getInt("numero"));
				desti.setRueDestination(rs.getString("rue"));
				desti.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : "");
				desti.setCodePostal(rs.getInt("code_postal"));
				desti.setVille(rs.getString("ville"));
				desti.setAgence(rs.getBoolean("agence"));
				destinations.add(desti);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors du selectAll dans la destination", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture des connexions dans selectAllDestinations", e);
			}
		}
		return destinations;
	}
	
	/**
	 * Cette methode insère une destination en base de données
	 * @param destination
	 * @throws DALException
	 */
	public void insertDestination(Destination destination) throws DALException {
		Connection cnx = null;
		PreparedStatement stmInsertDestination = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmInsertDestination = cnx.prepareStatement(INSERT_DESTINATION, Statement.RETURN_GENERATED_KEYS);
			stmInsertDestination.setString(1, destination.getLibelleDestination());
			stmInsertDestination.setInt(2, destination.getNumeroDestination());
			stmInsertDestination.setString(3, destination.getRueDestination());
			stmInsertDestination.setString(4, destination.getComplementDestination());
			stmInsertDestination.setInt(5, destination.getCodePostal());
			stmInsertDestination.setString(6, destination.getVille());
			stmInsertDestination.setBoolean(7, destination.getAgence());
			stmInsertDestination.executeUpdate();

		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de l'insertion d'une destination dans le DAO", e);
		} finally {
			try {
				if (stmInsertDestination != null) {
					stmInsertDestination.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans insertDestination", e);
			}
		}
		
	}
	
	/**
	 * Cette methode supprime une destination via son ID
	 * @param id
	 * @throws DALException
	 */
	public void deleteDestination(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtDeleteDestination = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtDeleteDestination = cnx.prepareStatement(DELETE_DESTINATION);
			smtDeleteDestination.setInt(1, id);
			smtDeleteDestination.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la suppression d'une destination", e);
		} finally {
			try {
				if (smtDeleteDestination != null) {
					smtDeleteDestination.close();
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
	 * Cette methode met à jour une destination
	 * @param id
	 * @param destination
	 * @throws DALException
	 */
	public void updateDestination(int id, Destination destination) throws DALException {
		Connection cnx = null;
		PreparedStatement smtUpdateDestination = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtUpdateDestination = cnx.prepareStatement(UPDATE_DESTINATION);
			smtUpdateDestination.setString(1, destination.getLibelleDestination());
			smtUpdateDestination.setInt(2, destination.getNumeroDestination());
			smtUpdateDestination.setString(3, destination.getRueDestination());
			smtUpdateDestination.setString(4, destination.getComplementDestination());
			smtUpdateDestination.setInt(5, destination.getCodePostal());
			smtUpdateDestination.setString(6, destination.getVille());
			smtUpdateDestination.setBoolean(7, destination.getAgence());
			smtUpdateDestination.setInt(8, id);
			smtUpdateDestination.executeUpdate();
			
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la mise à jour de la destination dand la DAO", e);
		} finally {
			try {
				if (smtUpdateDestination != null) {
					smtUpdateDestination.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans updateDestination", e);
			}
		}
		
	}
	
	/**
	 * Cette methode retourne une destination via son ID
	 * @param id
	 * @return une destination spécifique
	 * @throws DALException
	 */
	public Destination selectByIdDestination(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtSelectById = null;
		Destination desti = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectById = cnx.prepareStatement(SELECT_DESTINATION_BY_ID);
			smtSelectById.setInt(1, id);
			ResultSet rs = smtSelectById.executeQuery();
			if (rs.next()) {
				desti = new Destination(
									rs.getInt("id_destination"),
									rs.getString("nom_destination"),
									rs.getInt("numero"),
									rs.getString("rue"),
									rs.getString("complement_adresse"),
									rs.getInt("code_postal"),
									rs.getString("ville"),
									rs.getBoolean("agence")
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
		
		return desti;
	}
	
	
}
