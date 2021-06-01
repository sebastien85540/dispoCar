/**
 * 
 */
package fr.eni.dispocar.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.dispocar.exception.ConnectionProviderException;


/**
 * @author sebastien
 * Le connection provider contient l'instance de connexion
 *
 */
public class ConnectionProvider {
	
	// ATTRIBUTS
	
	private static DataSource datasource;
	
	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connexion au pool de connection défini par le context.xml
	 * 
	 * @return (30 connexions, maximum 100)
	 * @throws SQLException
	 * @throws ConnectionProviderException 
	 */
	public static Connection getConnection() throws SQLException, ConnectionProviderException {
		Connection connection = null;
		try {
			connection = ConnectionProvider.datasource.getConnection();
		} catch (SQLException e) {
			throw new ConnectionProviderException("La connexion n'a pas pu aboutir à la base de données ", e);
		}
		return connection;
	}
}
