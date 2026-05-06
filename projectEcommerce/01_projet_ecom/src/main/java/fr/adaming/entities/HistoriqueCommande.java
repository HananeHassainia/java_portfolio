package fr.adaming.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")


public class HistoriqueCommande implements Serializable{
	
	
	private Long idCommande;
	private String descCommande;
	private String desiCommande;
	private int quantite;
	private double prix;
	@Temporal(TemporalType.DATE) //convertir le java.util.date au format SQL
	private Date date;

	public HistoriqueCommande() {
		super();
	}
	public HistoriqueCommande(Long idCommande, String descCommande, String desiCommande, int quantite, double prix,
			Date date) {
		super();
		this.idCommande = idCommande;
		this.descCommande = descCommande;
		this.desiCommande = desiCommande;
		this.quantite = quantite;
		this.prix = prix;
		this.date = date;
	}

	public Long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	public String getDescCommande() {
		return descCommande;
	}
	public void setDescCommande(String descCommande) {
		this.descCommande = descCommande;
	}
	public String getDesiCommande() {
		return desiCommande;
	}
	public void setDesiCommande(String desiCommande) {
		this.desiCommande = desiCommande;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "HistoriqueCommande [idCommande=" + idCommande + ", descCommande=" + descCommande + ", desiCommande="
				+ desiCommande + ", quantite=" + quantite + ", prix=" + prix + ", date=" + date + "]";
	}

	
	
	

}
