package fr.adaming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.adaming.entities.Conseiller;
import fr.adaming.utils.DbUtil;

// Class qui implémente l'intérface IConseillerDao
public class ConseillerDao implements IConseillerDao {

//	Declaration et instanciation des attributs
	private Connection cx = null;
	private PreparedStatement ps = null;
	
// Implémentationd des méthodes de l'interface implémentée
	// Verifier si un conseiller est bien dans la table de conseiller
	@Override
	public Conseiller inspect(Conseiller cIn) {
		try {
			// 1 : Chargement du pilote et ouverture de la connexion 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Déclaration et instanciation de la requête
			String req = "SELECT * FROM conseiller WHERE mail_c = ? and motDePasse_c =?";
			
			// 3 : Récupération de l'objet de type prepared statement 
			ps = cx.prepareStatement(req);
			
			// 4 : Attribution des parametres de la requête
			ps.setString(1, cIn.getMail());
			ps.setString(2, cIn.getMdp());
			
			// 5 : Envois concret de la requête et récupération du résultat
			ResultSet rs = ps.executeQuery();
			
			//6 : Utilisation du résulat
			rs.next();
			
			Conseiller cOut = new Conseiller(rs.getInt("id_c"),rs.getString("nom_c"), rs.getString("prenom_c"), rs.getString("adresse_c"), rs.getLong("codePostal_c"), rs.getString("ville_c"), rs.getString("telephone_c"), rs.getString("mail_c"), rs.getString("motDePasse_c"));
			
			return cOut;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.fermerConnexion(cx, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int updateConseiller(Conseiller cIn) {
		try {
			// 1 : Chargement du driver et ouverture de la connexion
			cx = DbUtil.OuvrirConnexion();
			
			//2 : requête
			String req = "UPDATE conseiller set prenom_c=?, nom_c=?, adresse_c=?, codePostal_c=?, ville_c=?, telephone_c=?, mail_c=?, motDePasse_c=? where id_c=?";
		
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	assigner des valeurs aux paramètres
			ps.setString(1, cIn.getPrenom());
			ps.setString(2, cIn.getNom());
			ps.setString(3, cIn.getAdresse());
			ps.setLong(4, cIn.getCodePostal());
			ps.setString(5, cIn.getVille());
			ps.setString(6, cIn.getTelephone());
			ps.setString(7, cIn.getMail());
			ps.setString(8, cIn.getMdp());
			ps.setInt (9, cIn.getId());



			//	Envoyer la requête et exploiter le resultat
			return ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			//Vérifier si l'objet et statement et connexion sont null
			//Si il ne sont pas null : refermer la connexion avec la base de données

			try {
				DbUtil.fermerConnexion(cx, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	return 0;
	}

}
