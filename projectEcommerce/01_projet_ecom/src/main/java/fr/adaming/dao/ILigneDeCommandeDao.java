package fr.adaming.dao;

import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;

public interface ILigneDeCommandeDao {

	public LigneCommande addLigneCommande(LigneCommande lCadd);
	
	public int updateLigneCommande(LigneCommande lcIn);

}
