package fr.adaming.entities;

import java.util.ArrayList;

public class Conseiller extends Individu{
//	Declaration des attributs 
	//Transformation de l'association UML en JAVA
	private ArrayList<Client> listClt = new ArrayList<Client>();

//	Déclaration des constructeurs
	//	Constructeur vide
	public Conseiller() {
		super();
	}

	//	Constructeur surchargé que avec le mdp et l'adresse mail
	public Conseiller(String mail, String mdp) {
		super(mail, mdp);
	}
	
	//	Constructeur surchargé sans l'attribut id
	public Conseiller(String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, String mdp) {
		super(nom, prenom, adresse, codePostal, ville, telephone, mail, mdp);
	}

	//	Constructeur surchargé avec l'attribut id
	public Conseiller(int id, String nom, String prenom, String adresse, long codePostal, String ville, String telephone,String mail, String mdp) {
		super(id, nom, prenom, adresse, codePostal, ville, telephone, mail, mdp);
	}
	
	

//	Déclaration des getters et setters
	/**
	 * @return the listClt
	 */
	public ArrayList<Client> getListClt() {
		return listClt;
	}

	/**
	 * @param listClt the listClt to set
	 */
	public void setListClt(ArrayList<Client> listClt) {
		this.listClt = listClt;
	}

//	Redéfinition de la méthode toString()
	@Override
	public String toString() {
		return "Agent [toString()=" + super.toString() + "listClt=" + listClt +", ]";
	}
	

	
	

	
	
	
	
	
	
	

}
