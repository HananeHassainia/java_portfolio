package fr.adaming.service;

import java.util.List;

import fr.adaming.dao.ITransactionDao;
import fr.adaming.dao.TransactionDao;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Transaction;

public class TransactionService implements ITransactionService {
	ITransactionDao tDao = new TransactionDao();
	
	@Override
	public int addTransaction(Transaction tr) {
		return tDao.addTransaction(tr);
	}
	
	@Override
	public List<Transaction> getAllTransactionbyCC(CompteCourant ccIn) {
		return tDao.getAllTransactionbyCC(ccIn);
	}
	@Override
	public List<Transaction> getAllTransactionbyCE(CompteEpargne ceIn) {
		return tDao.getAllTransactionbyCE(ceIn);

	}

	@Override
	public int addTransactionCe(Transaction tr) {
		return tDao.addTransactionCe(tr);

	}

}
