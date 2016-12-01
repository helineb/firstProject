/**
 * 
 */
package model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PoolConnection {
	
	//le pool de connexion se limite à une seule connexion simultanée
	private static Connection cnx=null;
	private static String url=null;
	
	static {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//url = "jdbc:sqlserver://127.0.0.1;databasename=TestsJDBC;user=sa;password=Pa$$w0rd";
			
			//externalisation des chaines...
			Class.forName(Settings.getProperty("driver"));
			url = Settings.getProperty("url");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		if (cnx==null || cnx.isClosed()) // || !cnx.isValid(1))
			cnx = DriverManager.getConnection(url);
		return cnx;
	}
	
	public static void closeConnection(){
		if (cnx != null){
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cnx = null;
			}
		}
	}

}
