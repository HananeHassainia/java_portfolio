package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IBackCommande;
import fr.adaming.entities.BackCommandes;
import fr.adaming.entities.Client;

@Service
public class BackCommandeService implements IBackCommandeService{

	@Autowired
	IBackCommande bCDao;
	
	@Override
	public BackCommandes addBackCommande(BackCommandes bkComm) {
		return bCDao.addBackCommande(bkComm);
	}

	@Override
	public List<BackCommandes> getBackCommandesById(long cmdId) {
		return bCDao.getBackCommandesById(cmdId);
	}

	@Override
	public List<BackCommandes> getAllBcByIdClt(Client clt) {
		System.out.println("ok je suis passé dans service backCommande");
		return bCDao.getAllBcByIdClt(clt);
	}

}
