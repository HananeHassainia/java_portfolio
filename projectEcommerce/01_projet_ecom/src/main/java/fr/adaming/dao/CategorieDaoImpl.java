package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Categorie;

@Repository
@Transactional
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> getAllCategories() {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Categorie order by id desc";

		// recuperer le querry
		Query query = s.createQuery(req);

		List<Categorie> liste = query.list();

		for (Categorie cat : liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste;
	}

	@Override
	public Categorie addCategorie(Categorie catIn) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// appel de la methide save de la session pour relier l'obket eIn avec le
		// contecte de la session hierbante
		s.save(catIn); // synchronisation du context en java (recuperation de l'id)

		// Il n'existe pas de methode HQL pour add (insert)

		return catIn;

	}

	@Override
	public int updateCategorie(Categorie catIn) {
		// recuperer une session hierbante
		Session s = sf.getCurrentSession();

		// ecrire le req HQL
		String req = "UPDATE Categorie as c SET c.nomCategorie=:pNomCategorie, c.photo=:pPhoto, c.description=:pDescription WHERE c.id=:pId";

		// recupperer l'objet query pour envoyer la requete HQL
		Query query = s.createQuery(req);

		// passage des parametre
		query.setParameter("pNomCategorie", catIn.getNomCategorie());
		query.setParameter("pPhoto", catIn.getPhoto());
		query.setParameter("pDescription", catIn.getDescription());

		// On modifie la liste Produit ?

		query.setParameter("pId", catIn.getId());

		// executer requete HQL
		// execute update retourne le nb de ligne modifiée
		return query.executeUpdate();

	}

	@Override
	public boolean deleteCategorie(Categorie catIn) {

		try {
			// recuperer une session hibernate
			Session s = sf.getCurrentSession();
			s.delete(catIn);

			return true;

		} catch (HibernateException ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public Categorie getCategorieById(Categorie catIn) {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL permet d'interer le formateur dans la recherche
		String req = "FROM Categorie c WHERE c.id=:pId";

		// recuperer l'objet query

		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pId", catIn.getId());

		// executer la requete HQL

		Categorie cat = (Categorie) query.uniqueResult();

		if (cat != null) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}

		return cat;
	}

	@Override
	public List<Categorie> getCategoriesByProductKeyWord(String keyword) {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		Criteria cats = s.createCriteria(Categorie.class).createCriteria("produits")
				.add(Restrictions.like("designation", "%" + keyword + "%"));
		cats.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Categorie> catList = cats.list();

		for (Categorie cat : catList) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return catList;
	}

}