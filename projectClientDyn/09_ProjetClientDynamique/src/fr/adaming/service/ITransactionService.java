package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Transaction;

public interface ITransactionService {
	public int addTransaction(Transaction tr);

	public List<Transaction> getAllTransactionbyCC(CompteCourant ccIn);

	public List<Transaction> getAllTransactionbyCE(CompteEpargne ceIn);

	public int addTransactionCe(Transaction tr);

}
