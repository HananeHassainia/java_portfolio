package fr.adaming.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "BackCommande")
public class BackCommandes implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private long id_cmd;

	private java.util.Date date;

	@ManyToOne
	@JoinColumn(name = "c_id", referencedColumnName = "id_c")
	private Client client;

//	Produit
	private Long idProd;
	private String designationProd;
	private String descriptionProd;
	private double prixProd;
	@Transient
	private String imageProd;
	@Lob
	@Column(name = "photo", columnDefinition = "LONGBLOB")
	private byte[] photoProd;

//	Ligne de commande
	private int quantitLc;
	private double prixLc;
	private boolean promo;
public boolean isPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	// Declaration des constructeurs:
	// Vide
	public BackCommandes() {
		super();
	}

	public BackCommandes(int id, long id_cmd, Date date, Long idProd, String designationProd,
		String descriptionProd, double prixProd, String imageProd, byte[] photoProd, int quantitLc, double prixLc) {
	super();
	this.id = id;
	this.id_cmd = id_cmd;
	this.date = date;
	this.idProd = idProd;
	this.designationProd = designationProd;
	this.descriptionProd = descriptionProd;
	this.prixProd = prixProd;
	this.imageProd = imageProd;
	this.photoProd = photoProd;
	this.quantitLc = quantitLc;
	this.prixLc = prixLc;
}

	public BackCommandes(long id_cmd, Date date, Long idProd, String designationProd, String descriptionProd,
			double prixProd, String imageProd, byte[] photoProd, int quantitLc, double prixLc) {
		super();
		this.id_cmd = id_cmd;
		this.date = date;
		this.idProd = idProd;
		this.designationProd = designationProd;
		this.descriptionProd = descriptionProd;
		this.prixProd = prixProd;
		this.imageProd = imageProd;
		this.photoProd = photoProd;
		this.quantitLc = quantitLc;
		this.prixLc = prixLc;
	}

	// Declaration des getters et setters:
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Long getIdProd() {
		return idProd;
	}

	public void setIdProd(Long idProd) {
		this.idProd = idProd;
	}

	public String getDesignationProd() {
		return designationProd;
	}

	public void setDesignationProd(String designationProd) {
		this.designationProd = designationProd;
	}

	public String getDescriptionProd() {
		return descriptionProd;
	}

	public void setDescriptionProd(String descriptionProd) {
		this.descriptionProd = descriptionProd;
	}

	public double getPrixProd() {
		return prixProd;
	}

	public void setPrixProd(double prixProd) {
		this.prixProd = prixProd;
	}



	public String getImageProd() {
		return imageProd;
	}

	public void setImageProd(String imageProd) {
		this.imageProd = imageProd;
	}

	public byte[] getPhotoProd() {
		return photoProd;
	}

	public void setPhotoProd(byte[] photoProd) {
		this.photoProd = photoProd;
	}

	public int getQuantitLc() {
		return quantitLc;
	}

	public void setQuantitLc(int quantitLc) {
		this.quantitLc = quantitLc;
	}

	public double getPrixLc() {
		return prixLc;
	}

	public void setPrixLc(double prixLc) {
		this.prixLc = prixLc;
	}

	public long getId_cmd() {
		return id_cmd;
	}

	public void setId_cmd(long id_cmd) {
		this.id_cmd = id_cmd;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BackCommandes [id=" + id + ", id_cmd=" + id_cmd + ", utilTimestamp=" + date + ", client="
				+ client + ", idProd=" + idProd + ", designationProd=" + designationProd + ", descriptionProd="
				+ descriptionProd + ", prixProd=" + prixProd + ", imageProd=" + imageProd + ", photoProd="
				+ Arrays.toString(photoProd) + ", quantitLc=" + quantitLc + ", prixLc=" + prixLc + "]";
	}

}
