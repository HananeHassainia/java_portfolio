package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Commande;

public interface ICommandeDao {
	
	public List<Commande> getAllCommandes();

	public Commande addCommande(Commande comIn);

	public int updateCommande(Commande comIn);

	public boolean deleteCommande(Commande comIn);

	public Commande getCommandeById(Commande comIn);

	public Commande getLastCommande();
}
