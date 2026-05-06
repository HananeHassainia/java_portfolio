package fr.adaming.dao;


import java.util.List;

import fr.adaming.entities.BackCommandes;
import fr.adaming.entities.Client;

public interface IBackCommande {
	
	public BackCommandes addBackCommande(BackCommandes bkComm);

	public List<BackCommandes> getBackCommandesById(long cmdId); 
	
	public List<BackCommandes> getAllBcByIdClt(Client clt);
}
