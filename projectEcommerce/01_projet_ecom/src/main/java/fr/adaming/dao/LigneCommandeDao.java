package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.LigneCommande;

@Repository
@Transactional
public class LigneCommandeDao implements ILigneDeCommandeDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@Override
	public LigneCommande addLigneCommande(LigneCommande lCadd) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// appel de la methide save de la session pour relier l'obket eIn avec le
		// contecte de la session hierbante
		s.save(lCadd); // synchronisation du context en java (recuperation de l'id)

		return lCadd;
	}

	@Override
	public int updateLigneCommande(LigneCommande lcIn) {
		// recuperer une session hierbante
		Session s = sf.getCurrentSession();

		// ecrire le req HQL
		String req = "UPDATE LigneCommande as c SET c.quantite=:pQuantite, c.prix=:pPrix, c.produit=:pProduit, c.commande=:pCommande WHERE c.id=:pId";

		// recupperer l'objet query pour envoyer la requete HQL
		Query query = s.createQuery(req);

		// passage des parametre
		query.setParameter("pQuantite", lcIn.getQuantite());
		query.setParameter("pPrix", lcIn.getPrix());
		query.setParameter("pProduit", lcIn.getProduit());
		query.setParameter("pCommande", lcIn.getCommande());
		query.setParameter("pId", lcIn.getId());

		// executer requete HQL
		// execute update retourne le nb de ligne modifiée
		return query.executeUpdate();
	}

}
