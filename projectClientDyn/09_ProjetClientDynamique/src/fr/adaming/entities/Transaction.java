package fr.adaming.entities;

import java.sql.Date;

public class Transaction {
	private int id;
	private String typeCpt;
	private Date dateTr;
	private String operation;
	private double montant;
	private Compte cpt;
	
	public Transaction() {
		super();
	}

	public Transaction(String typeCpt, String operation, double montant, Compte cpt) {
		super();
		this.typeCpt = typeCpt;
		this.dateTr = dateTr;
		this.operation = operation;
		this.montant = montant;
		this.cpt = cpt;
	}

	public Transaction(int id, String typeCpt, Date dateTr, String operation, double montant, Compte cpt) {
		super();
		this.id = id;
		this.typeCpt = typeCpt;
		this.dateTr = dateTr;
		this.operation = operation;
		this.montant = montant;
		this.cpt = cpt;
	}


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
	 * @return the typeCpt
	 */
	public String getTypeCpt() {
		return typeCpt;
	}

	/**
	 * @param typeCpt the typeCpt to set
	 */
	public void setTypeCpt(String typeCpt) {
		this.typeCpt = typeCpt;
	}

	/**
	 * @return the dateTr
	 */
	public Date getDateTr() {
		return dateTr;
	}

	/**
	 * @param dateTr the dateTr to set
	 */
	public void setDateTr(Date dateTr) {
		this.dateTr = dateTr;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	/**
	 * @return the cpt
	 */
	public Compte getCpt() {
		return cpt;
	}

	/**
	 * @param cpt the cpt to set
	 */
	public void setCpt(Compte cpt) {
		this.cpt = cpt;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", typeCpt=" + typeCpt + ", dateTr=" + dateTr + ", operation=" + operation
				+ ", montant=" + montant + ", cpt=" + cpt + "]";
	}
	
	
	
}
