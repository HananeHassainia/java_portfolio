package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Client;
import fr.adaming.entities.HistoriqueCommande;

public interface IHistComService {
	
	public List<HistoriqueCommande> getAllCommandeByIdClients(Client clt);

	public List<HistoriqueCommande> getAllCommandeByClients(Client clt);
}
