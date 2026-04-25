package fr.adaming.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DbUtil {
	public static final String URL="jdbc:mysql://localhost:3306/gestioncomptedynamique";
	public static final String USER="root";
	public static final String MDP="root";
	
	public static Connection OuvrirConnexion() throws ClassNotFoundException, SQLException {
		//chargement du pilot
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Ouvrir une connexion
		Connection cx= DriverManager.getConnection(URL, USER, MDP);
		
		return cx;
	}
	
	
	public static void fermerConnexion(Connection cx, PreparedStatement ps) throws SQLException {
		if(ps != null) {
			ps.close();
		}
		if(cx != null) {
			cx.close();
		}
	}
}
