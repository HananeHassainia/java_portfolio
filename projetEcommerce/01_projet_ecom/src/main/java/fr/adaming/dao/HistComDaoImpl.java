package fr.adaming.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Client;
import fr.adaming.entities.HistoriqueCommande;


@Repository
@Transactional
public class HistComDaoImpl implements IHistComDao{

	@Autowired
	private SessionFactory sf;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistoriqueCommande> getAllCommandeByIdClients(Client clt) {
		Session s = sf.getCurrentSession();
		
		//req 
//		String req = "SELECT com.id, p.description, p.designation, lc.quantite, lc.prix, com.date FROM Client clt INNER JOIN Commande com on clt.id=com.client.id INNER JOIN LigneCommande lc on com.id=lc.com.id INNER JOIN Produit p on lc.produit.id=p.id WHERE clt.id=:pId_clt";
//		String req ="SELECT com.id, p.description, p.designation, lc.quantite, lc.prix, com.date FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 
		String req ="SELECT com.id FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 

		String req2 ="SELECT p.description FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 
		String req3 ="SELECT p.designation FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 
		String req4 ="SELECT lc.quantite FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 
		String req5 ="SELECT lc.prix FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 
		String req6 ="SELECT com.date FROM Client clt, Commande com, LigneCommande lc, Produit p  WHERE clt.id=com.client.id AND com.id=lc.commande.id AND lc.produit.id=p.id AND clt.id=:pId_clt"; 

		// objet query 
		Query query = s.createQuery(req);
		Query query2 = s.createQuery(req2);
		Query query3 = s.createQuery(req3);
		Query query4 = s.createQuery(req4);
		Query query5 = s.createQuery(req5);
		Query query6 = s.createQuery(req6);
		
		query.setParameter("pId_clt", clt.getId());
		query2.setParameter("pId_clt", clt.getId());
		query3.setParameter("pId_clt", clt.getId());
		query4.setParameter("pId_clt", clt.getId());
		query5.setParameter("pId_clt", clt.getId());
		query6.setParameter("pId_clt", clt.getId());
		
		List<Long> com = query.list();
		List<String> desc = query2.list();
		List<String> desi = query3.list();
		List<Integer> qt = query4.list();
		List<Double> prix = query5.list();
		List<Date> dn = query6.list();
		
		List<HistoriqueCommande> list = new ArrayList<HistoriqueCommande>();
		
		for (int i=0; i<com.size();i++){
			HistoriqueCommande hc = new HistoriqueCommande();
			hc.setIdCommande(com.get(i));
			hc.setDescCommande(desc.get(i));
			hc.setDesiCommande(desi.get(i));
			hc.setQuantite(qt.get(i));
			hc.setPrix(prix.get(i));
			hc.setDate(dn.get(i));
			list.add(hc);
		}
				
		System.out.println(list.size());
		
				
		return list;
				
		
	}

}
