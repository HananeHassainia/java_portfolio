package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.LigneCommande;

@Repository
@Transactional
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<LigneCommande> getAllLignesCommandes() {

		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM LigneCommande";

		// recuperer le querry
		Query query = s.createQuery(req);

		return query.list();

	}

}
