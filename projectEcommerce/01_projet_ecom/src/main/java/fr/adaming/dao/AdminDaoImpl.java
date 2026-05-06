package fr.adaming.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Admin;

@Repository
@Transactional
public class AdminDaoImpl implements IAdminDao {

	@Autowired // Pour injecter l'objet sessionFactoryBean instancier par le conteneur spring
				// // IoC
	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAllAdmins() {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la req HQL
		String req = "FROM Admin order by id desc";

		// recuperer le querry
		Query query = s.createQuery(req);
		List<Admin> list = query.list();
		for (Admin a : list) {
			System.out.println(a.getId());
		}
		return query.list();
	}

	@Override
	public int updateAdmin(Admin adIn) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// ecrire le req HQL
		String req = "UPDATE Admin as a SET a.nom=:pNom, a.prenom=:pPrenom, a.mail=:pMail, a.mdp=:pMdp, a.active=:pActive, a.role=:pRole WHERE a.id=:pId";

		// recupperer l'objet query pour envoyer la requete HQL
		Query query = s.createQuery(req);

		// passage des parametre
		query.setParameter("pNom", adIn.getNom());
		query.setParameter("pPrenom", adIn.getPrenom());
		query.setParameter("pMail", adIn.getMail());
		query.setParameter("pMdp", adIn.getMdp());
		query.setParameter("pActive", adIn.isActive());
		query.setParameter("pRole", adIn.getRole());

		query.setParameter("pId", adIn.getId());

		// executer requete HQL
		// execute update retourne le nb de ligne modifiée
		return query.executeUpdate();

	}

	@Override
	public Admin getAdminById(Admin adIn) {
		// recuperer la session hibernate
		Session s = sf.getCurrentSession();

		// ecrire la requete HQL permet d'interer le formateur dans la recherche
		String req = "FROM Admin a WHERE a.id=:pId";

		// recuperer l'objet query

		Query query = s.createQuery(req);

		// passage des params
		query.setParameter("pId", adIn.getId());

		// executer la requete HQL

		return (Admin) query.uniqueResult();
	}

	@Override
	public Admin addAdmin(Admin adIn) {
		// recuperer une session hibernate
		Session s = sf.getCurrentSession();

		// appel de la methide save de la session pour relier l'objet eIn avec le
		// context de la session hibernate
		s.save(adIn); // synchronisation du context en java (recuperation de l'id)

		// Il n'existe pas de methode HQL pour add (insert)

		return adIn;
	}

	@Override
	public Admin getAdminByMail(Admin adIn) {
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Admin a WHERE a.mail=:pMail";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pMail", adIn.getMail());

		return (Admin) query.uniqueResult();
	}

	@Override
	public boolean deleteAdmin(Admin adIn) {

		try {
			Session s = sf.getCurrentSession();

			s.delete(adIn);

			return true;
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	@Override
	public Admin getAdminByRole(Admin adIn) {
		Session s = sf.getCurrentSession();

		// Req
		String req = "FROM Admin a WHERE a.role.rolename=:pRoleName";

		// Récupérer l'objet query
		Query query = s.createQuery(req);

		query.setParameter("pRoleName", adIn.getRole().getRolename());

		return (Admin) query.uniqueResult();
	}

}
