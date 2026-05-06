package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitDao {
	
	public List<Produit> getAllProduits();

	public Produit addProduit(Produit cltIn);

	public int updateProduit(Produit cltIn);

	public boolean deleteProduit(Produit cltIn);

	public Produit getProduitById(Produit cltIn);
	
	public List<Produit> getProduitByCategorie(Categorie catIn);

	public List<Produit> getProduitsByKeyWord(String keyword);
	
	public List<Produit> getProduitsByKeyWordByCat(String keyword, Categorie catIn);
	
	public List<Produit> getProduitByCategorieOrdrDesc(Categorie catIn);
	
	public List<Produit> getProduitByCategorieOrdrAsc(Categorie catIn);

		
}
