package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

@Service
public class CategorieServiceImpl implements ICategorieService {

	@Autowired
	ICategorieDao catDao;
	IProduitDao proDao;


	@Override
	public List<Categorie> getAllCategories() {
		// appel de la methode Dao
		return catDao.getAllCategories();
	}

	@Override
	public Categorie addCategorie(Categorie catIn) {

		// appel de la methode dao
		return catDao.addCategorie(catIn);
	}

	@Override
	public int updateCategorie(Categorie catIn) {

		// appel de la methode dao
		return catDao.updateCategorie(catIn);
	}

	@Override
	public boolean deleteCategorie(Categorie catIn) {
		// recuperer l'Categorie par son id
		Categorie pOut = this.getCategorieById(catIn);
		
		//on recupere les produits liés a la categorie que l'on veut supprimer
		List<Produit> listeProduit = proDao.getProduitByCategorie(pOut);
		
		for (Produit produit : listeProduit) {
			// on change la categorie du produit vers une categorie non repertorié
			produit.setCategorie(new Categorie());
		}
		
		if (pOut != null) {

			// appel de la methode dao
			return catDao.deleteCategorie(pOut);
		}

		return false;
	}

	@Override
	public Categorie getCategorieById(Categorie catIn) {

		// appel de la metode dao
		return catDao.getCategorieById(catIn);
	}

	@Override
	public List<Categorie> getCategoriesByProductKeyWord(String keyword) {
		return catDao.getCategoriesByProductKeyWord(keyword);
	}

}