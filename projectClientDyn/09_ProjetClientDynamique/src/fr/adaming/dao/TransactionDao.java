package fr.adaming.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Transaction;
import fr.adaming.service.CompteCourantService;
import fr.adaming.utils.DbUtil;

public class TransactionDao implements ITransactionDao {
	private Connection cx = null;
	private PreparedStatement ps = null;

	@Override
	public int addTransaction(Transaction tr) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Stockage de notre requête dans une variable de type String
			String req = "INSERT INTO operation (typeCpt, operation, montant_op, id_c) VALUES (?,?,?,?)";
			
			
			// 3 : Envoyer la requêtes et récupèrer les resultats de cette requête
				// Récuperer l'objet de type statement 
				ps = cx.prepareStatement(req);
				
				// Assigner une valeur au paramètre 
				ps.setString(1, tr.getTypeCpt());
				ps.setString(2, tr.getOperation());
				ps.setDouble(3, tr.getMontant());
				ps.setInt(4, tr.getCpt().getId());
				
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
	public int addTransactionCe(Transaction tr) {
		try {
			// 1 : Chargement du driver 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Stockage de notre requête dans une variable de type String
			String req = "INSERT INTO operationce (typeCpt, operation, montant_op, id_c) VALUES (?,?,?,?)";
			
			
			// 3 : Envoyer la requêtes et récupèrer les resultats de cette requête
				// Récuperer l'objet de type statement 
				ps = cx.prepareStatement(req);
				
				// Assigner une valeur au paramètre 
				ps.setString(1, tr.getTypeCpt());
				ps.setString(2, tr.getOperation());
				ps.setDouble(3, tr.getMontant());
				ps.setInt(4, tr.getCpt().getId());
				
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
	public List<Transaction> getAllTransactionbyCC(CompteCourant ccIn) {
		try {
			// 1 :Charger le pilote et ouvrir la connexion 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Requête 
			String req = "SELECT * FROM operation where id_c=? and typeCpt='cc'";
			
			// 3 et 4 : Envoyer la requête 
				// 3 : récupérer l'objet preparedStatement
				ps = cx.prepareStatement(req);
			
				// 4 : Paramètres
				ps.setInt(1, ccIn.getId());
				
			// 5 : Envoyer la requête 
			ResultSet rs = ps.executeQuery();
	
			// 6 : Exploiter le resultat 
			//	Liste qui permet de stocker tous les objets de type Client créer à partir du résultat de la requête
			ArrayList<Transaction> listTr = new ArrayList<Transaction>();
			
			while(rs.next()) {				
				Transaction tr = new Transaction(rs.getInt("id_op"),rs.getString("typeCpt"), rs.getDate("date_op"), rs.getString("operation"), rs.getDouble("montant_op"),ccIn);
				

				listTr.add(tr);
				//On instancie un objet de type Client avec les données du resutats renvoyé après la requête	
				//On renvois une requête pour vérifier si aucun compte courant et compte epargne n'est associé à un client 
			}
			
			return listTr;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally {
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
	public List<Transaction> getAllTransactionbyCE(CompteEpargne ceIn) {
		try {
			// 1 :Charger le pilote et ouvrir la connexion 
			cx = DbUtil.OuvrirConnexion();
			
			// 2 : Requête 
			String req = "SELECT * FROM operationCe where id_c=? and typeCpt='ce'";
			
			// 3 et 4 : Envoyer la requête 
				// 3 : récupérer l'objet preparedStatement
				ps = cx.prepareStatement(req);
			
				// 4 : Paramètres
				ps.setInt(1, ceIn.getId());
				
			// 5 : Envoyer la requête 
			ResultSet rs = ps.executeQuery();
	
			// 6 : Exploiter le resultat 
			//	Liste qui permet de stocker tous les objets de type Client créer à partir du résultat de la requête
			ArrayList<Transaction> listTr = new ArrayList<Transaction>();
			
			while(rs.next()) {				
				Transaction tr = new Transaction(rs.getInt("id_op"),rs.getString("typeCpt"), rs.getDate("date_op"), rs.getString("operation"), rs.getDouble("montant_op"),ceIn);
				

				listTr.add(tr);
				//On instancie un objet de type Client avec les données du resutats renvoyé après la requête	
				//On renvois une requête pour vérifier si aucun compte courant et compte epargne n'est associé à un client 
			}
			
			return listTr;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally {
			try {
				DbUtil.fermerConnexion(cx, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
