package fr.adaming.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.utils.DbUtil;

public class ClientDao implements IClientDao{
	private Connection cx = null;
	private PreparedStatement ps = null;

	
	@Override
	public ArrayList<Client> getAllClient(Conseiller cIn) {
		try {
			// 1 :Charger le pilote et ouvrir la connexion 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Requête 
			String req = "SELECT * FROM client c left join comptecourant cc on c.id = cc.id_clt left join compteepargne ce on c.id = ce.id_clt HAVING c.id_c =?";
			
			// 3 et 4 : Envoyer la requête 
				// 3 : récupérer l'objet preparedStatement
				ps = cx.prepareStatement(req);
			
				// 4 : Paramètres
				ps.setInt(1, cIn.getId());
				
			// 5 : Envoyer la requête 
			ResultSet rs = ps.executeQuery();
	
			// 6 : Exploiter le resultat 
			//	Liste qui permet de stocker tous les objets de type Client créer à partir du résultat de la requête
			ArrayList<Client> listClt = new ArrayList<Client>();
			
			while(rs.next()) {
				Client c = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getLong("codePostal"), rs.getString("ville"), rs.getString("telephone"), rs.getString("mail"), rs.getString("motDePasse"), rs.getDate("birth"));
				
				if(rs.getInt("id_cc")!=0) {
					CompteCourant cc = new CompteCourant(rs.getInt("id_cc"), rs.getDouble("solde_cc"), rs.getDouble("decouvert"), c);
					c.setCc(cc);
				}
				
				if(rs.getInt("id_ce")!=0) {
					CompteEpargne ce = new CompteEpargne(rs.getInt("id_ce"), rs.getDouble("solde_ce"), rs.getDouble("tauxRemuneration"), c);
					c.setCe(ce);
				}

				listClt.add(c);
				//On instancie un objet de type Client avec les données du resutats renvoyé après la requête	
				//On renvois une requête pour vérifier si aucun compte courant et compte epargne n'est associé à un client 
			}
			
			return listClt;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally {
			//Vérifier si l'objet et statement et connexion sont null
			//Si il ne sont pas null : refermer la connexion avec la base de données
			try {
				if (ps != null) {
					ps.close();
				}

				if (cx != null) {
					cx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	@Override
	public Client getClient(Client cltIn) {
		try {
			// 1 : Chargement du driver et ouverture de la connexion  
			cx = DbUtil.OuvrirConnexion();
			
			//2 : Requête
			String req = "SELECT * FROM client c left join comptecourant cc on c.id = cc.id_clt left join compteepargne ce on c.id = ce.id_clt WHERE c.id = ? and c.id_c = ?";
			
			//3 et 4 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setInt(1, cltIn.getId());
			ps.setInt(2, cltIn.getCs().getId());
			
			//	Envoyer la requête
			ResultSet rs = ps.executeQuery();
			
			//Utilisation du résultats
			rs.next();
	
			Client c = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getLong("codePostal"), rs.getString("ville"), rs.getString("telephone"), rs.getString("mail"), rs.getString("motDePasse"), rs.getDate("birth"));

			if(rs.getInt("id_cc")!=0) {
				CompteCourant cc = new CompteCourant(rs.getInt("id_cc"), rs.getDouble("solde_cc"), rs.getDouble("decouvert"), c);
				c.setCc(cc);
			}
			
			if(rs.getInt("id_ce")!=0) {
				CompteEpargne ce = new CompteEpargne(rs.getInt("id_ce"), rs.getDouble("solde_ce"), rs.getDouble("tauxRemuneration"), c);
				c.setCe(ce);
			}
			
			return c;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally {
			//Vérifier si l'objet et statement et connexion sont null
			//Si il ne sont pas null : refermer la connexion avec la base de données
			try {
				if (ps != null) {
					ps.close();
				}

				if (cx != null) {
					cx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}


	@Override
	public int addClient(Client cltIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Stockage de notre requête dans une variable de type String
			String req = "INSERT INTO client (nom, prenom, birth, adresse, codePostal, ville, telephone, mail, motDePasse, id_c) VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			
			// 3 : Envoyer la requêtes et récupèrer les resultats de cette requête
				// Récuperer l'objet de type statement 
				ps = cx.prepareStatement(req);
				
				// Assigner une valeur au paramètre 
				ps.setString(1, cltIn.getNom());
				ps.setString(2, cltIn.getPrenom());
				ps.setDate(3, cltIn.getBirth());
				ps.setString(4, cltIn.getAdresse());
				ps.setLong(5, cltIn.getCodePostal());
				ps.setString(6, cltIn.getVille());
				ps.setString(7, cltIn.getTelephone());
				ps.setString(8, cltIn.getMail());
				ps.setString(9, cltIn.getMdp());
				ps.setInt(10, cltIn.getCs().getId());
				
				// Envoyer la requête, récupérer le resultat et l'exploiter
				return ps.executeUpdate();
				
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
		}finally {
			try {
				DbUtil.fermerConnexion(cx, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}


	@Override
	public int deleteClt(Client cltIn) {
		try {
			// 1 : Chargement du driver et ouverture de la connexion
			cx = DbUtil.OuvrirConnexion();
			
			//2 : requête pour supprimer une ligne
			String req = "DELETE FROM client WHERE id=? and id_c=?";
			
			//3 : Envoyer la requête et récupérer le resultat
			//	  Récupérer l'objet PreparedStatement
			ps = cx.prepareStatement(req);
			
			//	  Assigner une valeur au paramètre 
			ps.setInt(1, cltIn.getId());
			ps.setInt(2, cltIn.getCs().getId());

			
			// 	  Envoyer la requête et exploiter le resultat
			return ps.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException | java.lang.NullPointerException e) {
			//e.printStackTrace();
		}finally {
			try {
				DbUtil.fermerConnexion(cx, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}


	@Override
	public int updateClient(Client cltIn) {
		try {
			// 1 : Chargement du driver et ouverture de la connexion
			cx = DbUtil.OuvrirConnexion();
			
			//2 : requête
			String req = "UPDATE client set prenom =?, nom=?, adresse=?, codePostal=?, ville=?, telephone=?, birth=?, mail=? where id=? and id_c=?";
		
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	assigner des valeurs aux paramètres
			ps.setString(1, cltIn.getPrenom());
			ps.setString(2, cltIn.getNom());
			ps.setString(3, cltIn.getAdresse());
			ps.setLong(4, cltIn.getCodePostal());
			ps.setString(5, cltIn.getVille());
			ps.setString(6, cltIn.getTelephone());
			ps.setDate(7, cltIn.getBirth());
			ps.setString(8, cltIn.getMail());
			ps.setInt(9, cltIn.getId());
			ps.setInt(10, cltIn.getCs().getId());

			//	Envoyer la requête et exploiter le resultat
			return ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			//Vérifier si l'objet et statement et connexion sont null
			//Si il ne sont pas null : refermer la connexion avec la base de données
			try {
				if (ps != null) {
					ps.close();
				}

				if (cx != null) {
					cx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return 0;
	}
	

}
