package fr.adaming.entities;

public class CompteEpargne extends Compte {
//	Déclaration des attributs
	private double tauxDeRemuneration = 0.03;

	// Tranformation de l'association UML entre la classe Client et la classe Compte
	// Epargne
	private Client clt;

// 	Déclaration	des constructeurs
	// Constructeur vide
	public CompteEpargne() {
		super();
	}

	// Constructeur surchargé (avec les attributs hérités de la classe mère)
	public CompteEpargne(double solde, double tauxDeRemuneration, Client clt) {
		super(solde);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.clt = clt;
	}

	// Constructeur surchargé (avec les attributs hérités de la classe mère)
	public CompteEpargne(int id, double solde, double tauxDeRemuneration, Client clt) {
		super(id, solde);
		this.tauxDeRemuneration = tauxDeRemuneration;
		this.clt = clt;
	}

	public CompteEpargne(int id, double solde, double tauxDeRemuneration) {
		super(id, solde);
		this.tauxDeRemuneration = tauxDeRemuneration;
	}

//	Déclaration des  getters et setters

	public double getTauxDeRemuneration() {
		return tauxDeRemuneration;
	}

	public void setTauxDeRemuneration(double tauxDeRemuneration) {
		this.tauxDeRemuneration = tauxDeRemuneration;
	}

	public Client getClt() {
		return clt;
	}

	public void setClt(Client clt) {
		this.clt = clt;
	}

// Redéfintion de la méthode toString()
	@Override
	public String toString() {
		return "CompteEpargne [tauxDeRemuneration=" + tauxDeRemuneration + ", id=" + id + ", solde=" + solde + "]";
	}

}
