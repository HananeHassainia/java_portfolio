package fr.adaming.entities;

public abstract class Individu {
//	Définition des attributs
	protected int id;
	protected String nom;
	protected String prenom; 
	protected String adresse;
	protected long codePostal;
	protected String ville;
	protected String telephone;
	protected String mail;
	protected String mdp;
	
//	Déclaration des constructeurs
	// Constructeur vide
	public Individu() {
		super();
	}
	
	//	Constructeur surchargé avec les attributs mail et mdp
	public Individu(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}

	// Constructeur surchargé (sans l'attributs idClt)
	public Individu(String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.mail = mail;
		this.mdp = mdp;
	}

	// Constructeur surchargé avec l'attribut id sans mdp
		public Individu(int id, String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail) {
			super();
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.codePostal = codePostal;
			this.ville = ville;
			this.telephone = telephone;
			this.mail = mail;
		}
		
	// Constructeur surchargé avec l'attribut id
	public Individu(int id, String nom, String prenom, String adresse, long codePostal, String ville, String telephone, String mail, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.mail = mail;
		this.mdp = mdp;
	}

//Déclaration des getters et setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the codePostal
	 */
	public long getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(long codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


//Redéfinition de la méthode toString()
	@Override
	public String toString() {
		return "Individu [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal="
				+ codePostal + ", ville=" + ville + ", telephone=" + telephone + ", mail=" + mail + ", mdp=" + mdp
				+ "]";
	}


	

}
