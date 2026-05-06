package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
@Service
public class CommandeServiceImpl implements ICommandeService{

		@Autowired
		ICommandeDao comDao;
		


		@Override
		public List<Commande> getAllCommandes() {
			// appel de la methode Dao
			return comDao.getAllCommandes();
		}

		@Override
		public Commande addCommande(Commande comIn) {

			// appel de la methode dao
			return comDao.addCommande(comIn);
		}

		@Override
		public int updateCommande(Commande comIn) {

			// appel de la methode dao
			return comDao.updateCommande(comIn);
		}

		@Override
		public boolean deleteCommande(Commande comIn) {
			// recuperer l'Commande par son id
			Commande pOut = this.getCommandeById(comIn);
			
		
			
			if (pOut != null) {

				// appel de la methode dao
				return comDao.deleteCommande(pOut);
			}

			return false;
		}

		@Override
		public Commande getCommandeById(Commande comIn) {

			// appel de la metode dao
			return comDao.getCommandeById(comIn);
		}

		@Override
		public Commande getLastCommande() {
			return comDao.getLastCommande();
		}


	}
	

