package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
//	Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_r")
	private int id;
	private String rolename;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<Admin> admin;

//	Declaration des constructeurs
	public Role() {
		super();
	}

	public Role(String rolename) {
		super();
		this.rolename = rolename;
	}

	public Role(int id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}

//	Declaration des getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<Admin> getAdmin() {
		return admin;
	}

	public void setAdmin(List<Admin> admin) {
		this.admin = admin;
	}

}
