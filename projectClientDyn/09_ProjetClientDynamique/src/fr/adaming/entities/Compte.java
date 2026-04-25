package fr.adaming.entities;

public abstract class Compte {
//	Déclaration des attributs 
	protected int id;
	protected double solde;
	
//	Déclaration des constructeurs
	//Constructeur vide 
	public Compte() {
		super();
	}
	
	//Constructeur surchargé
	public Compte(double solde) {
		super();
		this.solde = solde;
	}
	
	//Constructeur surchargé
	public Compte(int id, double solde) {
		super();
		this.id = id;
		this.solde = solde;
	}

//	Déclaration des getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
// Redéfinition de la méthode toString();
	@Override
	public String toString() {
		return "Compte [id=" + id + ", solde=" + solde + "]";
	}
}
