package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;

public interface IProduitService {

	public List<Produit> getAllProduits();

	public Produit addProduit(Produit pIn);

	public int updateProduit(Produit pIn);

	public boolean deleteProduit(Produit pIn);

	public Produit getProduitById(Produit pIn);

	public List<Produit> getProduitsByKeyWord(String keyword);

	public List<Produit> getProduitByCategorie(Categorie catIn);

	public List<Produit> getProduitsByKeyWordByCat(String keyword, Categorie catIn);

	public List<Produit> getProduitByCategorieOrdrDesc(Categorie catIn);

	public List<Produit> getProduitByCategorieOrdrAsc(Categorie catIn);

}
