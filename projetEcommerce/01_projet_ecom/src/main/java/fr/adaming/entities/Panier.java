package fr.adaming.entities;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Panier {
//	Transformation de l'assocation UML en java
	private List<LigneCommande> lignesCommandes;

//	Declarations des constructeurs
	public Panier() {
		super();
	}

//	public Panier(List<LigneCommande> lignesCommandes) {
//		super();
//		this.lignesCommandes = lignesCommandes;
//	}

//	Declaration getters et setters
	public List<LigneCommande> getLignesCommandes() {
		return lignesCommandes;
	}

	public void setLignesCommandes(List<LigneCommande> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}
	
	
}
