package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Categorie;

public interface ICategorieService {
	public List<Categorie> getAllCategories();

	public Categorie addCategorie(Categorie catIn);

	public int updateCategorie(Categorie catIn);

	public boolean deleteCategorie(Categorie catIn);

	public Categorie getCategorieById(Categorie catIn);

	public List<Categorie> getCategoriesByProductKeyWord (String keyword);

}
