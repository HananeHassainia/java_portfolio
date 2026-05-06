package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Admin;
import fr.adaming.entities.BackCommandes;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
@Repository
@Transactional
public class BackCommandeDaoImpl implements IBackCommande{
	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring IoC
    private SessionFactory sf;
	
	// Developper la methode d ajout de back du commande
	@Override
	public BackCommandes addBackCommande(BackCommandes bkComm) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();
		// appel de la methode save de la session pour relier l'obket bkComm avec le contexte de la session hibernate
		s.save(bkComm);
		System.out.println("okkk");// synchronisation du context en java (recuperation de l'id)
		return bkComm;
	}

	@Override
	public List<BackCommandes> getBackCommandesById(long cmdId) {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL permet d'interer le formateur dans la recherche
		String req = "FROM BackCommandes bk WHERE bk.id_cmd=:pIdcmd";

		// recuperer l'objet query

		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pIdcmd", cmdId);

		// executer la requete HQL

		List<BackCommandes> liste =  query.list();
		
		for (BackCommandes cat : liste) {
			cat.setImageProd("data:image/png;base64," + Base64.encodeBase64String(cat.getPhotoProd()));
		}
		return liste;
	}

	@Override
	public List<BackCommandes> getAllBcByIdClt(Client clt) {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM BackCommandes Where client.id=:pIdClt GROUP BY id_cmd";

		// recuperer le querry
		Query query = s.createQuery(req);
		query.setParameter("pIdClt",clt.getId());

		return query.list();
	}

}
