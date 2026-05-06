package fr.adaming.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;

@Repository
@Transactional
public class ClientDao implements IClientDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@Override
	public Client getClientByMailMdp(Client cltIn) {
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Client c WHERE c.mail=:pMail and c.mdp=:pMdp";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pMail", cltIn.getMail());
		query.setParameter("pMdp", cltIn.getMdp());

		return (Client) query.uniqueResult();
	}

	@Override
	public Client getClientByMail(Client cltIn) {
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Client c WHERE c.mail=:pMail";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pMail", cltIn.getMail());

		return (Client) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClients() {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Client";

		// recuperer le querry
		Query query = s.createQuery(req);

		return query.list();
	}

	@Override
	public Client addClient(Client cltIn) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// appel de la methide save de la session pour relier l'obket eIn avec le
		// contecte de la session hierbante
		s.save(cltIn); // synchronisation du context en java (recuperation de l'id)

		// Il n'existe pas de methode HQL pour add (insert)

		return cltIn;

	}

	@Override
	public int updateClient(Client cltIn) {
		// recuperer une session hierbante
		Session s = sf.getCurrentSession();

		// ecrire le req HQL
		String req = "UPDATE Client as c SET c.nom=:pNom, c.prenom=:pPrenom, c.mail=:pMail, c.mdp=:pMdp, c.adresse=:pAdresse, c.tel=:pTel WHERE c.id=:pId";

		// recupperer l'objet query pour envoyer la requete HQL
		Query query = s.createQuery(req);

		// passage des parametre
		query.setParameter("pNom", cltIn.getNom());
		query.setParameter("pPrenom", cltIn.getPrenom());
		query.setParameter("pMail", cltIn.getMail());
		query.setParameter("pMdp", cltIn.getMdp());
		query.setParameter("pAdresse", cltIn.getAdresse());
		query.setParameter("pTel", cltIn.getTel());
		query.setParameter("pId", cltIn.getId());

		// executer requete HQL
		// execute update retourne le nb de ligne modifiée
		return query.executeUpdate();

	}

	@Override
	public boolean deleteClient(Client cltIn) {

		try {
			// recuperer une session hibernate
			Session s = sf.getCurrentSession();

			s.delete(cltIn);

			return true;

		} catch (HibernateException ex) {
			ex.printStackTrace();

		}

		return false;
	}

	@Override
	public Client getClientById(Client cltIn) {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL permet d'interer le formateur dans la recherche
		String req = "FROM Client c WHERE c.id=:pId";

		// recuperer l'objet query

		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pId", cltIn.getId());

		// executer la requete HQL

		return (Client) query.uniqueResult();
	}

	@Override
	public Client getLastClient() {
		// recuperer la session hierbante
		Session s = sf.getCurrentSession();

		Client clt = (Client) s.createQuery("from Client ORDER BY id DESC").setMaxResults(1).uniqueResult();
		// executer la requete HQL

		return clt;
	}
}
