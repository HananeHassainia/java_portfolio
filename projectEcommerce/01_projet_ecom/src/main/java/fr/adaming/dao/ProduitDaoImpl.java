package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Repository
@Transactional
public class ProduitDaoImpl implements IProduitDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getAllProduits() {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Produit order by id desc";

		// recuperer le querry
		Query query = s.createQuery(req);

		List<Produit> liste = query.list();

		for (Produit produit : liste) {

			produit.setImage("data:image/png;base64," + Base64.encodeBase64String(produit.getPhoto()));

		}

		return liste;

	}

	@Override
	public Produit addProduit(Produit pIn) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// appel de la methide save de la session pour relier l'obket eIn avec le
		// contecte de la session hierbante
		s.save(pIn); // synchronisation du context en java (recuperation de l'id)

		// Il n'existe pas de methode HQL pour add (insert)

		return pIn;

	}

	@Override
	public int updateProduit(Produit pIn) {
		// recuperer une session hierbante
		Session s = sf.getCurrentSession();

		// ecrire le req HQL
		String req = "UPDATE Produit as p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.photo=:pPhoto, p.categorie.id=:pCategorie WHERE p.id=:pId";

		// recupperer l'objet query pour envoyer la requete HQL
		Query query = s.createQuery(req);

		// passage des parametre
		query.setParameter("pDesignation", pIn.getDesignation());
		query.setParameter("pDescription", pIn.getDescription());
		query.setParameter("pPrix", pIn.getPrix());
		query.setParameter("pQuantite", pIn.getQuantite());
		// il n'y a pas de selectionne
		query.setParameter("pPhoto", pIn.getPhoto());
		query.setParameter("pId", pIn.getId());
		query.setParameter("pCategorie", pIn.getCategorie().getId());

		// executer requete HQL
		// execute update retourne le nb de ligne modifiée
		return query.executeUpdate();

	}

	@Override
	public boolean deleteProduit(Produit pIn) {

		try {
			// recuperer une session hibernate
			Session s = sf.getCurrentSession();

			s.delete(pIn);

			return true;

		} catch (HibernateException ex) {
			ex.printStackTrace();

		}

		return false;
	}

	@Override
	public Produit getProduitById(Produit pIn) {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL permet d'interer le formateur dans la recherche
		String req = "FROM Produit p WHERE p.id=:pId";

		// recuperer l'objet query

		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pId", pIn.getId());

		// executer la requete HQL

		Produit prod = (Produit) query.uniqueResult();

		if (prod != null) {

			prod.setImage("data:image/png;base64," + Base64.encodeBase64String(prod.getPhoto()));

		}

		return prod;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getProduitByCategorie(Categorie catIn) {
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Produit as p WHERE p.categorie.id=:pId";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pId", catIn.getId());

		List<Produit> liste = query.list();

		for (Produit cat : liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getProduitsByKeyWord(String keyword) {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Produit as p WHERE p.designation like:pKey";

		Query query = s.createQuery(req);

		query.setParameter("pKey", "%" + keyword + "%");

		List<Produit> liste = query.list();

		for (Produit cat : liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getProduitsByKeyWordByCat(String keyword, Categorie catIn) {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Produit as p WHERE p.designation like:pKey AND p.categorie.id=:pIdCat";

		Query query = s.createQuery(req);

		query.setParameter("pKey", "%" + keyword + "%");
		query.setParameter("pIdCat", catIn.getId());


		List<Produit> liste = query.list();

		for (Produit cat : liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste;
	}

	@Override
	public List<Produit> getProduitByCategorieOrdrDesc(Categorie catIn) {
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Produit as p WHERE p.categorie.id=:pId ORDER BY prix DESC";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pId", catIn.getId());

		List<Produit> liste = query.list();

		for (Produit cat : liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> getProduitByCategorieOrdrAsc(Categorie catIn) {
		
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Produit as p WHERE p.categorie.id=:pId ORDER BY prix ASC";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pId", catIn.getId());

		List<Produit> liste = query.list();

		for (Produit cat : liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste;
	
	}
	
	
}