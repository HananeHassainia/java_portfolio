package fr.adaming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ClientService;
import fr.adaming.utils.DbUtil;

public class CompteCourantDao implements ICompteCourantDao{
	Connection cx = null;
	PreparedStatement ps = null;
	
	@Override
	public int addCompteCourant(CompteCourant ccIn) {
	
			// Récupérer le driver et ouvrir la connexion
			try {
				cx = DbUtil.OuvrirConnexion();
				
				// Stockage de notre requête dans une varible de type String
				String req = "INSERT INTO comptecourant (decouvert, id_clt, solde_cc) VALUES (?,?,?)";
				
				// Envoyer la requêtes et récupèrer les resultats de cette requêtes
					//	Récuperer l'objet de type statement 
					ps = cx.prepareStatement(req);
				
					// Envoyer la requête et récupérer le resultat et l'exploiter
					ps.setDouble(1, ccIn.getDecouvert());
					ps.setInt(2,ccIn.getClt().getId());
					ps.setDouble(3, ccIn.getSolde());
					
				return ps.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public int deleteCompteCourant(CompteCourant ccIn) {
		try {
			// 1 : Chargement du driver et ouverture de la connexion
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : requête pour supprimer une ligne
			String req = "DELETE FROM comptecourant WHERE id_cc=?";
			
			//4 : Envoyer la requête et récupérer le resultat
			//	  Récupérer l'objet PreparedStatement
			ps = cx.prepareStatement(req);
			
			//	  Assigner une valeur au paramètre 
			ps.setInt(1, ccIn.getId());

			// 	  Envoyer la requête et exploiter le resultat
			return ps.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException | java.lang.NullPointerException e) {
			e.printStackTrace();
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
	public int updateCompteCourant(CompteCourant ccIn) {
		try {
			// 1 : Chargement du driver et ouverture de la connexion
			cx = DbUtil.OuvrirConnexion();
			
			//2 : requête
			String req = "UPDATE comptecourant set decouvert=?, solde_cc=?, id_clt=? where id_cc=?";
		
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	assigner des valeurs aux paramètres
			ps.setDouble(1, ccIn.getDecouvert());
			ps.setDouble(2, ccIn.getSolde());
			ps.setInt(3, ccIn.getClt().getId());
			ps.setInt(4, ccIn.getId());
			
			//	Envoyer la requête et exploiter le resultat
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
	public ArrayList<CompteCourant> getAllCompteCourant(Conseiller cIn) {
			try {
				//Charger le Driver et ouvrir la connexion
				cx = DbUtil.OuvrirConnexion();
				
				//Requête : Afficher toutes les lignes
				String req = "SELECT * FROM compteCourant cc left join client c on c.id = cc.id_clt having c.id_c=?";
				
				//Envoyer la requête et récupérer le resultat
				//	récupérer l'objet preparedStatement
				ps = cx.prepareStatement(req);
				
				// Parametre
				ps.setInt(1, cIn.getId());
				
				//	Envoyer la requête et récuperer le resultat
				ResultSet rs = ps.executeQuery();
		
				//Exploiter le resultat 
				//	Liste qui permet de stocker tous les objets de type Client créer à partir du résultat de la requête
				ArrayList<CompteCourant> listCc = new ArrayList<CompteCourant>();
				
				while(rs.next()) {
					Client cltIn = new Client();
					cltIn.setId(rs.getInt("id_clt"));
					listCc.add(new CompteCourant(rs.getInt("id_cc"), rs.getDouble("solde_cc"), rs.getDouble("decouvert"), new ClientService().getClient(cltIn, cIn)));
					//On instancie un objet de type Client avec les données du resutats renvoyé après la requête	
					//On renvois une requête pour vérifier si aucun compte courant et compte epargne n'est associé à un client 
				}
				return listCc;
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
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
			return null;
	}

	@Override
	public CompteCourant getCompteCourant(CompteCourant ccIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : Requête
			String req = "SELECT * FROM comptecourant where id_cc=?";
			
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setInt(1, ccIn.getId());
			
			//	Envoyer la requête
			ResultSet rs = ps.executeQuery();
			
			//Utilisation du résultats
			rs.next();
			
			Client cltIn = new Client();
			cltIn.setId(rs.getInt("id_clt"));
			return new CompteCourant(rs.getInt("id_cc"), rs.getDouble("solde_cc"), rs.getDouble("decouvert"), cltIn);
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
		return null;
	}

	@Override
	public CompteCourant getCompteCourantIdClt(Client cltIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : Requête
			String req = "SELECT * FROM comptecourant where id_clt=?";
			
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setInt(1, cltIn.getId());
			
			//	Envoyer la requête
			ResultSet rs = ps.executeQuery();
			
			//Utilisation du résultats
			rs.next();
	
			return new CompteCourant(rs.getInt("id_cc"), rs.getDouble("solde_cc"), rs.getDouble("decouvert"), cltIn);
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
		return null;
	}

	@Override
	public int removeAddMoney(CompteCourant ccIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : Requête
			String req = "UPDATE comptecourant set solde_cc=? where id_cc=?";
			
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setDouble(1, ccIn.getSolde());
			ps.setInt(2, ccIn.getId());
			
			//	Envoyer la requête
			return ps.executeUpdate();
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally {
			//Vérifier si l'objet et statement et connexion sont null
			//Si il ne sont pas null : refermer la connexion avec la base de données
		}
		return 0;		
	}

	@Override
	public int getIdOfLastClient() {
		try {
			//Charger le Driver
			cx = DbUtil.OuvrirConnexion();
			
			//Requête : Afficher toutes les lignes
			String req = "SELECT MAX(id) AS LastID FROM client";
			
			//Envoyer la requête et récupérer le resultat
			//	récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Envoyer la requête et récuperer le resultat
			ResultSet rs = ps.executeQuery();
	
			//Exploiter le resultat 
			//	Liste qui permet de stocker tous les objets de type Client créer à partir du résultat de la requête
			
			rs.next();
			
			return rs.getInt("LastID");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
