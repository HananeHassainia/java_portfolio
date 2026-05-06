package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {
//	Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ct")
	private int id;
	private String nomCategorie;

	@Transient
	private String image;

	@Lob
	@Column(name = "photo", columnDefinition = "LONGBLOB")
	private byte[] photo;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private String description;

//	Transformation de l'assocation UML en JAVA
	@OneToMany(mappedBy = "categorie")
	private List<Produit> produits;

//	Declaration des constructeurs
	public Categorie() {
		super();
	}

	public Categorie(String nomCategorie, byte[] photo, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	public Categorie(int id, String nomCategorie, byte[] photo, String description) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

//	Declaration des getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
