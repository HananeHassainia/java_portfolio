package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.ILigneDeCommandeDao;
import fr.adaming.entities.LigneCommande;

@Service
public class LigneCommandeService implements ILigneCommandeService{

	@Autowired
	ILigneDeCommandeDao lCdao;
	
	@Override
	public LigneCommande addLigneCommande(LigneCommande lCadd) {
		return lCdao.addLigneCommande(lCadd);
	}

	@Override
	public int updateLigneCommande(LigneCommande lcIn) {
		return lCdao.updateLigneCommande(lcIn);

	}

}
