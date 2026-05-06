package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IClientDao;
import fr.adaming.dao.ICommandeDao;
import fr.adaming.dao.IHistComDao;
import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.HistoriqueCommande;

@Service
public class HistComServiceImpl implements IHistComService{

	@Autowired
	IHistComDao histComDao;
	@Autowired
	IClientDao clientDao;
	@Autowired
	ICommandeDao commandeDao;
	@Autowired
	ILigneCommandeDao lignecommandeDao;
	@Autowired
	IProduitDao produitDao;
	
	
	
	@Override
	public List<HistoriqueCommande> getAllCommandeByIdClients(Client clt) {
		//appel de la mthode dao
		return histComDao.getAllCommandeByIdClients(clt);
	}

	@Override
	public List<HistoriqueCommande> getAllCommandeByClients(Client clt) {
		
		return null;
	}

}
