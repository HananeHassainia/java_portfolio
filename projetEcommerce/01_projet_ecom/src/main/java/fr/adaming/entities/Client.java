package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity


@Table(name="clients")
@Component
public class Client implements Serializable {
//	Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_c")
	private Long id;
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean active;
	private String adresse;
	private String tel;

	@OneToMany(mappedBy = "client")
	private List<Commande> commandes;

	@OneToMany(mappedBy = "client")
	private List<BackCommandes> bCommande;
	
//	Declaration des constructeurs
	public Client() {
		super();
	}

	public Client(String nom, String prenom, String mail, String mdp, boolean active, String adresse, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.active = active;
		this.adresse = adresse;
		this.tel = tel;
	}

	public Client(Long id, String nom, String prenom, String mail, String mdp, boolean active, String adresse,
			String tel) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.active = active;
		this.adresse = adresse;
		this.tel = tel;
	}

//	Declaration des getters et setters

	public String getAdresse() {
		return adresse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

}
