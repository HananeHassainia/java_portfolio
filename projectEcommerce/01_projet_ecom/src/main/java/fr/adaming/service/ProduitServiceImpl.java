package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IProduitDao;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Service
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	IProduitDao proDao;


	@Override
	public List<Produit> getAllProduits() {
		// appel de la methode Dao
		return proDao.getAllProduits();
	}

	@Override
	public Produit addProduit(Produit proIn) {

		// appel de la methode dao
		return proDao.addProduit(proIn);
	}

	@Override
	public int updateProduit(Produit proIn) {

		// appel de la methode dao
		return proDao.updateProduit(proIn);
	}

	@Override
	public boolean deleteProduit(Produit proIn) {
		// recuperer l'Produit par son id
		Produit pOut = this.getProduitById(proIn);
		if (pOut != null) {

			// appel de la methode dao
			return proDao.deleteProduit(pOut);
		}

		return false;
	}

	@Override
	public Produit getProduitById(Produit proIn) {

		// appel de la metode dao
		return proDao.getProduitById(proIn);
	}

	@Override
	public List<Produit> getProduitsByKeyWord(String keyword) {
		return proDao.getProduitsByKeyWord(keyword);
	}

	@Override
	public List<Produit> getProduitByCategorie(Categorie catIn) {
		return proDao.getProduitByCategorie(catIn);
	}

	@Override
	public List<Produit> getProduitsByKeyWordByCat(String keyword, Categorie catIn) {
		return proDao.getProduitsByKeyWordByCat(keyword, catIn);
	}

	@Override
	public List<Produit> getProduitByCategorieOrdrDesc(Categorie catIn) {
		return proDao.getProduitByCategorieOrdrDesc(catIn);
	}

	@Override
	public List<Produit> getProduitByCategorieOrdrAsc(Categorie catIn) {
		return proDao.getProduitByCategorieOrdrAsc(catIn);
	}
	
}