package fr.adaming.entities;

import java.sql.Date;

public class Client extends Individu{

//	Attributs
	private Date birth;
	
//	Transformation de l'association en UML entre la classe Client et Compte Courant et Client et Compte Epargne
	private CompteCourant cc;
	private CompteEpargne ce;

//	Transformation de l'association UML en JAVA
	private Conseiller cs;
	
//	Déclaration des constructeurs

	// Constructeur vide
	public Client() {
		super();
	}

	public Client(String mail, String mdp) {
		super(mail, mdp);
	}
		
	// Constructeur surchargé (sans l'attributs idClt)
	public Client(String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, String mdp, Date birth) {
		super(nom, prenom, adresse, codePostal, ville, telephone, mail, mdp);
		this.birth = birth;
	}

	//Constructeur surchargé sans les attributs de type CompteCourant et CompteEpargne
	public Client(int id, String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, String mdp, Date birth) {
		super(id, nom, prenom, adresse, codePostal, ville, telephone, mail, mdp);
		this.birth = birth;
	}

	//Constructeur surchargé sans les attributs de type CompteCourant et CompteEpargne
	public Client(int id, String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, Date birth) {
		super(id, nom, prenom, adresse, codePostal, ville, telephone, mail);
		this.birth = birth;
	}
	
//	//Constructeur surchargé avec tous les attributs
//	public Client(int id, String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, String mdp, Date birth, String sexe, CompteCourant cc, CompteEpargne ce) {
//		super(id, nom, prenom, adresse, codePostal, ville, telephone, mail, mdp);
//		this.birth = birth;
//		this.sexe = sexe;
//		this.cc = cc;
//		this.ce = ce;
//	}

//	Declaration des Getters et Setters
	public CompteCourant getCc() {
		return cc;
	}

	public void setCc(CompteCourant cc) {
		this.cc = cc;
	}

	public CompteEpargne getCe() {
		return ce;
	}

	public void setCe(CompteEpargne ce) {
		this.ce = ce;
	}


/**
	 * @return the cs
	 */
	public Conseiller getCs() {
		return cs;
	}

	/**
	 * @param cs the cs to set
	 */
	public void setCs(Conseiller cs) {
		this.cs = cs;
	}

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	//	Redéfinition de la méthode toString()
	@Override
	public String toString() {
		return "Client ["+super.toString()+"cc=" + cc + ", ce=" + ce + "]";
	}

	
	
}
