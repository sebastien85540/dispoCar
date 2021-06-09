/**
 * 
 */
package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dispocar.bo.Indisponibilite;
import fr.eni.dispocar.bo.IndisponibiliteVehicule;
import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class IndispoVehiculeDAO {
	private static String SELECT_ALL_INDISPO_VEHICULE = "SELECT * FROM Indisponibilites_Vehicules"
			+ " INNER JOIN Vehicules ON Vehicules.immatriculation = vehicule_immatriculation"
			+ " INNER JOIN Indisponibilites ON id_motif = motif ORDER BY date_debut DESC";
	private static String INSERT_INDISPO_VEHICULE = "INSERT INTO Indisponibilites_Vehicules (date_debut, date_fin, vehicule_immatriculation, motif) VALUES (?, ?, ?, ?)";
	private static String UPDATE_INDISPO_VEHICULE = "UPDATE FROM Indisponibilites_vehicules SET date_debut=?, date_fin=?, vehicule_immatriculation=?, motif=? WHERE id_indispo=?";
	private static String DELETE_INDISPO_VEHICULE ="DELETE FROM Indisponibilites_Vehicules WHERE id_indispo=?";
	private static String SELECT_BY_ID_INDISPO_VEHICULE = "SELECT * FROM Indisponibilites_Vehicules WHERE id_indispo=?";
	
	/**
	 * Cette methode récupère les indisponibilités d'un vehicule
	 * @return la liste des vehicules indisponibles
	 * @throws DALException
	 */
	public List<IndisponibiliteVehicule> selectAllVehiculesIndispo() throws DALException{
		List<IndisponibiliteVehicule> indispoVehicules = new ArrayList<IndisponibiliteVehicule>();
		Connection cnx = null;
		Statement smtSelectAll = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAll = cnx.createStatement();
			ResultSet rs = smtSelectAll.executeQuery(SELECT_ALL_INDISPO_VEHICULE);
			while (rs.next()) {
				IndisponibiliteVehicule indispo = new IndisponibiliteVehicule();
				indispo.setDateDebutIndispo(LocalDateTime.of(rs.getDate("date_debut").toLocalDate(), rs.getTime("date_debut").toLocalTime()));
				indispo.setDateFinIndispo(LocalDateTime.of(rs.getDate("date_fin").toLocalDate(), rs.getTime("date_fin").toLocalTime()));
				Vehicule vehi = new Vehicule();
				vehi.setImmatriculation(rs.getString("immatriculation"));
				vehi.setDesignationVehicule(rs.getString("description_vehicule"));
				vehi.setNbrePlaces(rs.getInt("nbre_places"));
				vehi.setTypeVehicule(rs.getString("type_vehicule"));
				indispo.setVehiculeIndispo(vehi);
				Indisponibilite indi = new Indisponibilite();
				indi.setIdIndisponibilite(rs.getString("id_motif"));
				indi.setLibelleIndisponibilite(rs.getString("libelle"));
				indispo.setRaisonIndispo(indi);
				indispoVehicules.add(indispo);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors du selectAllIndispoVehicules", e);
		} finally {
			try {
				if (smtSelectAll != null) {
					smtSelectAll.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est suevenue à la fermeture de la connexion dans le selectAllIndispoVehicules", e);
			}
		}
		return indispoVehicules;
	}
	
	/**
	 * Cette methode insert uhne indisponibilité d'un vehicule precis
	 * @param indispo
	 * @throws DALException
	 */
	public void insertIndispoVehicule(IndisponibiliteVehicule indispo) throws DALException {
		Connection cnx = null;
		PreparedStatement smtInsert = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtInsert = cnx.prepareStatement(INSERT_INDISPO_VEHICULE, Statement.RETURN_GENERATED_KEYS);
			smtInsert.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(indispo.getDateDebutIndispo().toLocalDate(), indispo.getDateDebutIndispo().toLocalTime())));
			smtInsert.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(indispo.getDateFinIndispo().toLocalDate(), indispo.getDateFinIndispo().toLocalTime())));
			smtInsert.setObject(3, indispo.getVehiculeIndispo());
			smtInsert.setObject(4, indispo.getRaisonIndispo());
			smtInsert.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue pendant l'insertion d'une indisponibilite de vehicule", e);
		} finally {
			try {
				if (smtInsert != null) {
					smtInsert.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue pendant la coupure de la connexion sur insertIndispoVehicule", e);
			}
		}
	}
	
	/**
	 * Cette methode met à jour l'indisponibilité d'un vehicule
	 * @param id
	 * @param indispo
	 * @throws DALException
	 */
	public void updateIndispoVehicule(int id, IndisponibiliteVehicule indispo) throws DALException {
		Connection cnx = null;
		PreparedStatement smtUpdate = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtUpdate = cnx.prepareStatement(UPDATE_INDISPO_VEHICULE);
			smtUpdate.setTimestamp(1, Timestamp.valueOf(LocalDateTime.of(indispo.getDateDebutIndispo().toLocalDate(), indispo.getDateDebutIndispo().toLocalTime())));
			smtUpdate.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(indispo.getDateFinIndispo().toLocalDate(), indispo.getDateFinIndispo().toLocalTime())));
			smtUpdate.setObject(3, indispo.getVehiculeIndispo());
			smtUpdate.setObject(4, indispo.getRaisonIndispo());
			smtUpdate.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue pendant la mise à jour de l'indisponibilité vehicule", e);
		} finally {
			try {
				if (smtUpdate != null) {
					smtUpdate.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue pendant la fermeture des connexions de la mise à jour de l'indisponibilité vehicule", e);
			}
		}
	}
	
	/**
	 * Cette methode me permet de supprimer une indispo de vehicule via son id
	 * @param id
	 * @throws DALException
	 */
	public void deleteIndispoVehicule(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtDelete = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtDelete = cnx.prepareStatement(DELETE_INDISPO_VEHICULE);
			smtDelete.setInt(1, id);
			smtDelete.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la suppression d'une indisponibilite de vehicule", e);
		} finally {
			try {
				if (smtDelete != null) {
					smtDelete.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion deleteIndispoVehicule", e);
			}
		}
	}
	
	public IndisponibiliteVehicule selectByIdIndispoVehicule(int id) throws DALException {
		IndisponibiliteVehicule indispo = null;
		Connection cnx = null;
		PreparedStatement smtSelectById = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectById = cnx.prepareStatement(SELECT_BY_ID_INDISPO_VEHICULE);
			ResultSet rs = smtSelectById.executeQuery();
			if (rs.next()) {
				indispo = new IndisponibiliteVehicule();
				indispo.setDateDebutIndispo(LocalDateTime.of(rs.getDate("date_debut").toLocalDate(), rs.getTime("date_debut").toLocalTime()));
				indispo.setDateFinIndispo(LocalDateTime.of(rs.getDate("date_fin").toLocalDate(), rs.getTime("date_fin").toLocalTime()));
				Vehicule vehi = new Vehicule();
				vehi.setImmatriculation(rs.getString("immatriculation"));
				vehi.setDesignationVehicule(rs.getString("description_vehicule"));
				vehi.setNbrePlaces(rs.getInt("nbre_places"));
				vehi.setTypeVehicule(rs.getString("type_vehicule"));
				indispo.setVehiculeIndispo(vehi);
				Indisponibilite indi = new Indisponibilite();
				indi.setIdIndisponibilite(rs.getString("id_motif"));
				indi.setLibelleIndisponibilite(rs.getString("libelle"));
				indispo.setRaisonIndispo(indi);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue lors de la récupération d'une indispoVehicule", e);
		} finally {
			try {
				if (smtSelectById != null) {
					smtSelectById.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("Une erreur est survenue lors de la fermeture de la connexion dans indispoVehicule", e);
			}
		}
		return indispo ;
	}
}
