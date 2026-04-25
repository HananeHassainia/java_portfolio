package fr.adaming.entities;

public class CompteCourant extends Compte{
//	Déclaration des attributs
	private double decouvert;
	
	//Tranformation de l'association UML entre la classe Client et la classe Compte Courant
	private Client clt;

	
// 	Déclaration	des constructeurs
	//Constructeur vide 
	public CompteCourant() {
		super();
	}

	public CompteCourant(double solde, double decouvert, Client clt) {
		super(solde);
		this.decouvert = decouvert;
		this.clt = clt;
	}
	
	//Constructeur surchargé (avec les attributs hérités de la classe mère Compte) 
	public CompteCourant(int id, double solde, double decouvert, Client clt) {
		super(id, solde);
		this.decouvert = decouvert;
		this.clt = clt;
	}
	
	public CompteCourant(int id, double solde, double decouvert) {
		super(id, solde);
		this.decouvert = decouvert;
	}

	
//	Déclaration des  getters et setters

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public Client getClt() {
		return clt;
	}

	public void setClt(Client clt) {
		this.clt = clt;
	}

	
//	Redéfiniton de la méthode toString();
	
	@Override
	public String toString() {
		return "CompteCourant [decouvert=" + decouvert + ", id=" + id + ", solde=" + solde + "]";
	}
}
