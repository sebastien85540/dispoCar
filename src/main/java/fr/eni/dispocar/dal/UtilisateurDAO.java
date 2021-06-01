/**
 * 
 */
package fr.eni.dispocar.dal;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.dispocar.bo.Utilisateur;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ToolsException;
import fr.eni.dispocar.tools.PasswordTools;


/**
 * @author sebastien
 *
 */
public class UtilisateurDAO {
	
	// Preparation de mes constantes contenant mes commandes SQL
	private static final String INSERT_UTILISATEUR = "INSERT INTO Utilisateurs(email, mot_passe, administrateur) VALUES (?, ?, ?)";
	private static final String UPDATE_UTILISATEUR = "UPDATE Utilisateurs SET email=?, mot_passe=?, administrateur=? WHERE id_utilisateur=?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM Utilisateurs WHERE id_utilisateur=?";
	private static final String SELECT_ALL = "SELECT * FROM Utilisateurs";
	private static final String SELECT_BY_ID = "SELECT id_utilisateur, email, mot_passe, administrateur FROM Utilisateurs WHERE id_utilisateur=?";
	private static final String AUTH = "SELECT email, mot_passe, administrateur FROM Utilisateurs WHERE email=? AND mot_passe=?";
	
	/**
	 * Methode INSERT pour inserer un utilisateur 
	 * @param utilisateur
	 * @return
	 * @throws DALException
	 * @throws ConnectionProviderException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public Utilisateur insertUtlisateur(Utilisateur utilisateur) throws DALException, ConnectionProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		Connection cnx = null;
		PreparedStatement psmt = null;
		
		if (utilisateur != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				psmt = cnx.prepareStatement(INSERT_UTILISATEUR, Statement.RETURN_GENERATED_KEYS);
				
				psmt.setString(1, utilisateur.getEmail());
				psmt.setString(2, PasswordTools.encrypt(utilisateur.getPassword()));
				psmt.setBoolean(3, utilisateur.getAdministrateur());
				psmt.executeUpdate();
				
				ResultSet rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setIdUtilisateur(rs.getInt(1));
				}
				
			} catch (SQLException e) {
				throw new DALException("L'insertion de données s'est mal déroulée dans l'INSERT ", e);
			} finally {
				try {
					if (psmt != null) {
						psmt.close();;
					}
					if (cnx != null) {
						cnx.close();;
					}
				} catch (SQLException e) {
					throw new DALException("La connexion ou le PreparedStatement ne s'est pas fermé correctement", e);
				}
			}
		}
		return utilisateur;
	}
	
	/**
	 * permet de modifier l'utilisateur
	 * !!! ne pourras être utilisé que par un utilisateur Administrateur
	 * @param utilisateur
	 * @throws DALException
	 * @throws ConnectionProviderException
	 * @throws ToolsException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void updateUtilisateur(Utilisateur util) throws DALException, ConnectionProviderException, InvalidKeySpecException, ToolsException, NoSuchAlgorithmException {
		Connection cnx = null; 
		PreparedStatement smtpUpdate = null;
		
		if(util != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				smtpUpdate = cnx.prepareStatement(UPDATE_UTILISATEUR);
				smtpUpdate.setString(1, util.getEmail());
				smtpUpdate.setString(2, PasswordTools.encrypt(util.getPassword()));
				smtpUpdate.setBoolean(3, util.getAdministrateur());
				smtpUpdate.setInt(4, util.getIdUtilisateur());
				
				smtpUpdate.executeUpdate();
				
			} catch (SQLException e) {
				throw new DALException("La mise a jour de l'utilisateur s'est mal passée et n'a pas été faite ", e);
			} finally {
				try {
					if (smtpUpdate != null) {
						smtpUpdate.close();
					}
					if (cnx != null) {
						cnx.close();
					}
				} catch (SQLException e) {
					throw new DALException("La connexion de la partie update est toujours active", e); 
				}
			}
		}
	}
	
	/**
	 * Cette methode permet la suppression d'un utilisateur via son id
	 * @param utilisateur
	 * @throws ConnectionProviderException
	 * @throws DALException
	 */
	public void deleteUtilisateur(int idUtilisateur) throws ConnectionProviderException, DALException {
		Connection cnx = null;
		PreparedStatement smtDelete = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtDelete = cnx.prepareStatement(DELETE_UTILISATEUR);
			smtDelete.setInt(1, idUtilisateur);
			smtDelete.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DALException("La suppression ne s'est pas déroulée correctement", e);
		} finally {
			try {
				if (smtDelete != null) {
					smtDelete.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La connexion du DELETE est toujours active", e);
			}
		}
	}
	
	/**
	 * Cette methode selectionne tous les utilisateurs de la table Utilisateurs
	 * @return List<Utilisateur> une liste complète d'utilisateurs
	 * @throws ConnectionProviderException
	 * @throws SQLException
	 * @throws DALException
	 */
	public List<Utilisateur> selectAll() throws ConnectionProviderException, SQLException, DALException{
		Connection cnx = null;
		Statement smtSelect = null;
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		try {
			cnx = ConnectionProvider.getConnection();
			smtSelect = cnx.createStatement();
			ResultSet rs = smtSelect.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				
				Utilisateur util;
				util = new Utilisateur();
				util.setIdUtilisateur(rs.getInt("id_utilisateur"));
				util.setEmail(rs.getString("email"));
				util.setPassword(rs.getString("mot_passe"));
				util.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateurs.add(util);
				System.out.println(util);
			}
		} catch (SQLException e) {
			throw new DALException("L'application n'a pas reussie à sortir la liste SelectAll ", e);
		} finally {
			try {
				if (smtSelect != null) {
					smtSelect.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La fermeture des connexions s'est mal déroulée pour selectAll ", e);
				
			}
		}
		return utilisateurs;
	}
	
	/**
	 * Cette methode va chercher les informations d'un utilisateur par son email et mot de passe
	 * @param email
	 * @param password
	 * @return l'authentification de l'utilisateur
	 * @throws DALException
	 * @throws InvalidKeySpecException
	 * @throws ToolsException
	 * @throws NoSuchAlgorithmException 
	 */
	public Utilisateur authenticate(String email, String password) throws DALException, InvalidKeySpecException, ToolsException, NoSuchAlgorithmException {
		Connection cnx = null;
		PreparedStatement prstmtAuthenticate = null;
		Utilisateur utilisateur = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			prstmtAuthenticate = cnx.prepareStatement(AUTH);
			prstmtAuthenticate.setString(1, email);
			String encryptedPassword = PasswordTools.encrypt(password);
			prstmtAuthenticate.setString(2, encryptedPassword);
			//System.out.println("Le mot de passe est " + encryptedPassword);
			ResultSet rs = prstmtAuthenticate.executeQuery();
			
			if (rs.next()) {
				utilisateur = new Utilisateur(
											rs.getString("email"),
											rs.getString("mot_passe"),
											rs.getBoolean("administrateur")
											);
				
			}
		} catch (SQLException | ConnectionProviderException e) {
			throw new DALException("L'utilisateur n'a pas pu être récupèré", e);
		} finally {
			try {
				if (prstmtAuthenticate != null) {
					prstmtAuthenticate.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La fermeture de la connexion n'a pas été effectuée dans le SelectByEmail ", e);
			}
		}
		return utilisateur;
	}
	
	/**
	 * Cette methode va chercher un utilisateur grace à son ID
	 * @param idUtilisateur
	 * @return un utilisateur
	 */
	public Utilisateur selectById(int idUtilisateur) {
		Connection cnx = null;
		PreparedStatement prstSelectById = null;
		Utilisateur util = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			prstSelectById = cnx.prepareStatement(SELECT_BY_ID);
			prstSelectById.setInt(1, idUtilisateur);
			ResultSet rs = prstSelectById.executeQuery();
			if (rs.next()) {
				util = new Utilisateur(
										rs.getInt("id_utilisateur"),
										rs.getString("email"),
										rs.getString("mot_passe"),
										rs.getBoolean("administrateur")
										);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return util;
	}
}
