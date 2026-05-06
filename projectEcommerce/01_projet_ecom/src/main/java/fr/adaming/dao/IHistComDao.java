package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.HistoriqueCommande;

public interface IHistComDao{
	
	
	public List<HistoriqueCommande> getAllCommandeByIdClients(Client clt);
	
}
