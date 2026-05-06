package fr.adaming.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignescommandes")
public class LigneCommande implements Serializable{
//	Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lc")
	private int id;
	private int quantite;
	private double prix;
	
//	Transformation de l'assocation UML en JAVA
	@ManyToOne
	@JoinColumn(name="cmd_id", referencedColumnName = "id_cmd")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="p_id", referencedColumnName = "id_p")
	private Produit produit;
	
//	Declaration des constructeurs
	public LigneCommande() {
		super();
	}
	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}
	public LigneCommande(int id, int quantite, double prix) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.prix = prix;
	}
	
//	Declaration des getters et setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
}
