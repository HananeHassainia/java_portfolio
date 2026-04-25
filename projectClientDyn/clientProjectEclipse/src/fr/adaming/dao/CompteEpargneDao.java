package fr.adaming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ClientService;
import fr.adaming.utils.DbUtil;

public class CompteEpargneDao implements ICompteEpargneDao{
	Connection cx = null;
	PreparedStatement ps = null;
	
	@Override
	public int addCompteEpargne(CompteEpargne ceIn) {
		try {
			// Chargement du driver et ouvrir une connexion
			cx = DbUtil.OuvrirConnexion();
							
			// Stockage de notre requête dans une varible de type String
			String req = "INSERT INTO compteepargne (solde_ce, tauxRemuneration, id_clt) VALUES (?,?,?)";
			
			// Envoyer la requêtes et récupèrer les resultats de cette requêtes
				//	Récuperer l'objet de type statement 
			ps = cx.prepareStatement(req);
			
			ps.setDouble(1, ceIn.getSolde());
			ps.setDouble(2, ceIn.getTauxDeRemuneration());
			ps.setInt(3, ceIn.getClt().getId());
			
				// Envoyer la requête et récupérer le resultat et l'exploiter
			return ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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

	@Override
	public int deleteCompteEpargne(CompteEpargne ceIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : requête pour supprimer une ligne
			String req = "DELETE FROM compteepargne WHERE id_ce=?";
			
			//4 : Envoyer la requête et récupérer le resultat
			//	  Récupérer l'objet PreparedStatement
			ps = cx.prepareStatement(req);
			
			//	  Assigner une valeur au paramètre 
			ps.setInt(1, ceIn.getId());

			// 	  Envoyer la requête et exploiter le resultat
			return ps.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException | java.lang.NullPointerException e) {
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

	@Override
	public int updateCompteEpargne(CompteEpargne ceIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : requête
			String req = "UPDATE compteepargne set tauxRemuneration=?, solde_ce=?, id_clt=? where id_ce=?";
		
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	assigner des valeurs aux paramètres
			ps.setDouble(1, ceIn.getTauxDeRemuneration());
			ps.setDouble(2, ceIn.getSolde());
			ps.setInt(3, ceIn.getClt().getId());
			ps.setInt(4, ceIn.getId());
			
			
			//	Envoyer la requête et exploiter le resultat
			return ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
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

	@Override
	public ArrayList<CompteEpargne> getAllCompteEpargne(Conseiller cIn) {
		try {
			//Charger le Driver et ouvrir la connexion
			cx = DbUtil.OuvrirConnexion();
			
			//Requête : Afficher toutes les lignes
			String req = "SELECT * FROM compteepargne ce left join client c on c.id = ce.id_clt having c.id_c=?";

			//Envoyer la requête et récupérer le resultat
			//	récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			// Parametre
			ps.setInt(1, cIn.getId());
			
			//	Envoyer la requête et récuperer le resultat
			ResultSet rs = ps.executeQuery();
	
			//Exploiter le resultat 
			//	Liste qui permet de stocker tous les objets de type Client créer à partir du résultat de la requête
			ArrayList<CompteEpargne> listCe = new ArrayList<CompteEpargne>();
			
			while(rs.next()) {
				Client cltIn = new Client();
				cltIn.setId(rs.getInt("id_clt"));
				listCe.add(new CompteEpargne(rs.getInt("id_ce"), rs.getDouble("solde_ce"), rs.getDouble("TauxRemuneration"), new ClientService().getClient(cltIn, cIn)));
				//On instancie un objet de type Client avec les données du resutats renvoyé après la requête	
				//On renvois une requête pour vérifier si aucun compte courant et compte epargne n'est associé à un client 
			}
			return listCe;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
		return null;
	}

	@Override
	public CompteEpargne getCompteEpargne(CompteEpargne ceIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : Requête
			String req = "SELECT * FROM compteepargne where id_ce=?";
			
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setInt(1, ceIn.getId());
			
			//	Envoyer la requête
			ResultSet rs = ps.executeQuery();
			
			//Utilisation du résultats
			rs.next();
			
			Client cltIn = new Client();
			cltIn.setId(rs.getInt("id_clt"));
			return new CompteEpargne(rs.getInt("id_ce"), rs.getDouble("solde_ce"), rs.getDouble("TauxRemuneration"), cltIn);
		
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
	public CompteEpargne getCompteEpargneIdClt(Client cltIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : Requête
			String req = "SELECT * FROM compteepargne where id_clt=?";
			
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setInt(1, cltIn.getId());
			
			//	Envoyer la requête
			ResultSet rs = ps.executeQuery();
			
			//Utilisation du résultats
			rs.next();
			return new CompteEpargne(rs.getInt("id_ce"), rs.getDouble("solde_ce"), rs.getDouble("Tauxremuneration"), cltIn);
		
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
	public int removeAddMoney(CompteEpargne ceIn) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			//3 : Requête
			String req = "UPDATE compteepargne set solde_ce=? where id_ce=?";
			
			//4 et 5 : Envois de la requête et récupération du résultat
			//	Récupérer l'objet preparedStatement
			ps = cx.prepareStatement(req);
			
			//	Assigner les valeurs aux paramètres
			ps.setDouble(1, ceIn.getSolde());
			ps.setInt(2, ceIn.getClt().getId());
			
			//	Envoyer la requête
			return ps.executeUpdate();
		
		
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
