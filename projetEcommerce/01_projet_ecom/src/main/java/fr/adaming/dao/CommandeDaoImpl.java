package fr.adaming.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Commande;

@Repository
@Transactional
public class CommandeDaoImpl implements ICommandeDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getAllCommandes() {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Commande";

		// recuperer le querry
		Query query = s.createQuery(req);

		return query.list();
	}

	@Override
	public Commande addCommande(Commande comIn) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// appel de la methide save de la session pour relier l'obket eIn avec le
		// contecte de la session hierbante
		s.save(comIn); // synchronisation du context en java (recuperation de l'id)

		// Il n'existe pas de methode HQL pour add (insert)

		return comIn;

	}

	@Override
	public int updateCommande(Commande comIn) {
		// recuperer une session hierbante
		Session s = sf.getCurrentSession();

		// ecrire le req HQL
		String req = "UPDATE Commande as c SET c.date=:pDate, c.lignesCommandes=:pLignesCommandes, c.Client=:pClient WHERE c.id=:pId";

		// recupperer l'objet query pour envoyer la requete HQL
		Query query = s.createQuery(req);

		// passage des parametre
		query.setParameter("pDate", comIn.getDate());
		query.setParameter("pLignesCommandes", comIn.getLignesCommandes());
		query.setParameter("pClient", comIn.getClient());

		// Dans service peut etre faire un get id client et lignes commandes pour
		// faciliter la modif du coup changer les entrées

		query.setParameter("pId", comIn.getId());

		// executer requete HQL
		// execute update retourne le nb de ligne modifiée
		return query.executeUpdate();

	}

	@Override
	public boolean deleteCommande(Commande comIn) {

		try {
			// recuperer une session hibernate
			Session s = sf.getCurrentSession();

			s.delete(comIn);

			return true;

		} catch (HibernateException ex) {
			ex.printStackTrace();

		}

		return false;
	}

	@Override
	public Commande getCommandeById(Commande comIn) {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL permet d'interer le formateur dans la recherche
		String req = "FROM Commande c WHERE c.id=:pId";

		// recuperer l'objet query

		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pId", comIn.getId());

		// executer la requete HQL

		return (Commande) query.uniqueResult();
	}

	@Override
	public Commande getLastCommande() {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		Commande cmd = (Commande) s.createQuery("from Commande ORDER BY id DESC").setMaxResults(1).uniqueResult();
		// executer la requete HQL

		return cmd;
	}
}