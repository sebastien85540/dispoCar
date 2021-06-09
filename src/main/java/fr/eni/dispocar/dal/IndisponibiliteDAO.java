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

import fr.eni.dispocar.bo.Indisponibilite;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;

/**
 * @author sebastien
 *
 */
public class IndisponibiliteDAO {
	
	private static final String SELECT_ALL_INDISPO = "SELECT * FROM Indisponibilites";
	private static final String INSERT_INDISPONIBILITE = "INSERT INTO Indisponibilites (id_motif, libelle) VALUES (?, ?)";
	private static final String DELETE_INDISPONIBILITE = "DELETE FROM Indisponibilites WHERE id_motif=?";
	private static final String UPDATE_INDISPONIBILITE = "UPDATE Indisponibilites SET id_motif=?, libelle=? WHERE id_motif=?";
	private static final String SELECT_INDISPONIBILITE_BY_ID = "SELECT * FROM Indisponibilites WHERE id_motif=?";
	
	
	public List<Indisponibilite> selectAllIndispo() throws DALException {
		List<Indisponibilite> indisponibilites = new ArrayList<Indisponibilite>();
		Connection cnx = null;
		Statement smtSelectAllIndispo = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectAllIndispo = cnx.createStatement();
			ResultSet rs = smtSelectAllIndispo.executeQuery(SELECT_ALL_INDISPO);
			
			while (rs.next()) {
				Indisponibilite indispo = new Indisponibilite();
				indispo.setIdIndisponibilite(rs.getString("id_motif"));
				indispo.setLibelleIndisponibilite(rs.getString("libelle"));
				indisponibilites.add(indispo);
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur est survenue dans le selectAll de indisponibilites", e);
		} finally {
			try {
				if (smtSelectAllIndispo != null) {
					smtSelectAllIndispo.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La connexion dans le selectAll de Indisponibilites n'est pas fermée", e);
			}
		}
		return indisponibilites;
	}

	/**
	 * Cette methode permet l'insertion d'une indisponibilité
	 * @param indispo
	 * @throws DALException
	 */
	public void insertIndispo(Indisponibilite indispo) throws DALException {
		Connection cnx = null;
		PreparedStatement smtInsertIndispo = null;
		
		if (indispo != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				smtInsertIndispo = cnx.prepareStatement(INSERT_INDISPONIBILITE, Statement.NO_GENERATED_KEYS);
				smtInsertIndispo.setString(1, indispo.getIdIndisponibilite());
				smtInsertIndispo.setString(2, indispo.getLibelleIndisponibilite());
				smtInsertIndispo.executeUpdate();

			} catch (SQLException | ConnectionProviderException e) {
				throw new DALException("Une erreur est survenue dans l'insertion d'une indisponibilite du DAO indisponibilite", e);
			} finally {
				try {
					if (smtInsertIndispo != null) {
						smtInsertIndispo.close();
					}
					if (cnx != null) {
						cnx.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

	/**
	 * Cette methode permet la suppression d'une indisponibilité via son id 
	 * @param id
	 * @throws DALException
	 */
	public void indispoDelete(String id) throws DALException {
		Connection cnx = null;
		PreparedStatement smtDeleteIndispo = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtDeleteIndispo = cnx.prepareStatement(DELETE_INDISPONIBILITE);
			smtDeleteIndispo.setString(1, id);
			smtDeleteIndispo.executeUpdate();
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Une erreur a eu lieu dans la suppression d'une indisponibilité dans la DAL", e);
		} finally {
			try {
				if (smtDeleteIndispo != null) {
					smtDeleteIndispo.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La connexion s'est mal arrêtée dans la DAL indisponibilités", e);
			}
		}
		
	}
	
	/**
	 * Cette methode modifie une indisponibilite via son id
	 * @param indispo
	 * @throws DALException
	 */
	public void updateIndispo(String id, Indisponibilite indispo) throws DALException {
		Connection cnx = null;
		PreparedStatement smtUpdateIndispo = null;
		
		if (indispo != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				smtUpdateIndispo = cnx.prepareStatement(UPDATE_INDISPONIBILITE);
				smtUpdateIndispo.setString(1, indispo.getIdIndisponibilite());
				smtUpdateIndispo.setString(2, indispo.getLibelleIndisponibilite());
				smtUpdateIndispo.setString(3, id);
				smtUpdateIndispo.executeUpdate();
				
			} catch (SQLException | ConnectionProviderException e) {
				throw new DALException("Une erreur a eu lieu dans la DAL de l'UpdateIndisponibilite", e); 
			} finally {
				try {
					if (smtUpdateIndispo != null) {
						smtUpdateIndispo.close();
					}
					if (cnx != null) {
						cnx.close();
					}
				} catch (SQLException e) {
					throw new DALException("La connexion s'est mal fermée dans l'update indisponibilité", e);
				}
			}
			
		}
		
		
	}
	
	/**
	 * Cette methode retourne un enregistrement d'indisponibilité via son ID
	 * @param idIndispo
	 * @return une indisponibilité
	 * @throws DALException 
	 */
	public Indisponibilite indispoSelectById(String idIndispo) throws DALException {
		Indisponibilite indisponibilite = null;
		Connection cnx = null;
		PreparedStatement smtSelectByIdIndispo = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelectByIdIndispo = cnx.prepareStatement(SELECT_INDISPONIBILITE_BY_ID);
			smtSelectByIdIndispo.setString(1, idIndispo);
			ResultSet rs = smtSelectByIdIndispo.executeQuery();
			if (rs.next()) {
				indisponibilite = new Indisponibilite(rs.getString("id_motif"), rs.getString("libelle"));
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("Il ya a eu une erreur dans la recuperation d'une indisponibilité via son id dans le selectById", e);
		} finally {
			try {
				if (smtSelectByIdIndispo != null) {
					smtSelectByIdIndispo.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La connexion s'est mal fermée dans le selectById d'indisponibilité", e);
			}
		}
		
		
		
		return indisponibilite;
	}

}
