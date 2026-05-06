package fr.adaming.service;

import fr.adaming.entities.LigneCommande;

public interface ILigneCommandeService {
	public LigneCommande addLigneCommande(LigneCommande lCadd);

	public int updateLigneCommande(LigneCommande lcIn);

}
