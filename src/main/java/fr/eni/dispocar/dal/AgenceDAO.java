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
import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class AgenceDAO {
	private static final String SELECT_ALL_AGENCE = "SELECT * FROM Destinations WHERE agence=1 ORDER BY id_destination ";
	private static final String SELECT_BY_ID_AGENCE = "SELECT * FROM Destinations WHERE id_destination=?";

	
	/**
	 * Cette methode récupère la liste des Agences
	 * @return une liste d'agences
	 * @throws DALException
	 */
	public List<Agence> selectAll() throws DALException{
		List<Agence> agences = null;
		Connection cnx = null;
		Statement smtSelectAll = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_AGENCE);
			List<Vehicule> vehicules = new ArrayList<Vehicule>();
			int id = 0;
			while (rs.next()) {
				if(id != rs.getInt("id_destination")) {
					Agence agence = new Agence();
					
					agence.setIdDestination(rs.getInt("id_destination"));
					agence.setLibelleDestination(rs.getString("nom_destination"));
					agence.setNumeroDestination(rs.getInt("numero"));
					agence.setRueDestination(rs.getString("rue"));
					agence.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : "");
					agence.setCodePostal(rs.getInt("code_postal"));
					agence.setVille(rs.getString("ville"));
					agence.setAgence(rs.getBoolean("agence"));
					
//					Vehicule vehi = new Vehicule();
//					vehi.setImmatriculation(rs.getString("immatriculation"));
//					vehi.setDesignationVehicule(rs.getString("description_vehicule"));
//					vehi.setNbrePlaces(rs.getInt("nbre_places"));
//					vehi.setDateAchat(rs.getDate("date_achat").toLocalDate());
//					vehi.setTypeVehicule(rs.getString("type_vehicule"));
//					vehicules.add(vehi);
					agence.setVehiculesAgence(vehicules);
					if (agences == null) {
						agences = new ArrayList<Agence>();
					}
					agences.add(agence);
//					id = rs.getInt("id_destination");
//				} else {
//					Vehicule vehi = new Vehicule();
//					vehi.setImmatriculation(rs.getString("immatriculation"));
//					vehi.setDesignationVehicule(rs.getString("description_vehicule"));
//					vehi.setNbrePlaces(rs.getInt("nbre_places"));
//					vehi.setDateAchat(rs.getDate("date_achat").toLocalDate());
//					vehi.setTypeVehicule(rs.getString("type_vehicule"));
//					vehicules.add(vehi);
				}
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la récupération des agences dans la DAO", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans la DAO selectAllAgences", e);
			}
		}
		return agences;
	}
	
	/**
	 * Cette methode retourne une destination via son ID
	 * @param id
	 * @return une destination spécifique
	 * @throws DALException
	 */
	public Agence selectByIdAgence(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtSelectById = null;
		Agence agence = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectById = cnx.prepareStatement(SELECT_BY_ID_AGENCE);
			smtSelectById.setInt(1, id);
			ResultSet rs = smtSelectById.executeQuery();
				agence = new Agence();
				
				agence.setIdDestination(rs.getInt("id_destination"));
				agence.setLibelleDestination(rs.getString("nom_destination"));
				agence.setNumeroDestination(rs.getInt("numero"));
				agence.setRueDestination(rs.getString("rue"));
				agence.setComplementDestination(rs.getString("complement_adresse") != null ? rs.getString("complement_adresse") : "");
				agence.setCodePostal(rs.getInt("code_postal"));
				agence.setVille(rs.getString("ville"));
				agence.setAgence(rs.getBoolean("agence"));
				
				while (rs.next()) {
				List<Vehicule> vehicules = new ArrayList<Vehicule>();
				Vehicule vehi = new Vehicule();
				vehi.setImmatriculation(rs.getString("immatriculation"));
				vehi.setDesignationVehicule(rs.getString("description_vehicule"));
				vehi.setNbrePlaces(rs.getInt("nbre_places"));
				vehi.setDateAchat(rs.getDate("date_achat").toLocalDate());
				vehi.setTypeVehicule(rs.getString("type_vehicule"));
				vehicules.add(vehi);
				agence.setVehiculesAgence(vehicules);
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
		
		return agence;
	}
}
