package fr.adaming.service;

import java.util.ArrayList;

import fr.adaming.dao.CompteEpargneDao;
import fr.adaming.dao.ICompteEpargneDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;

public class CompteEpargneService implements ICompteEpargneService {
	ICompteEpargneDao ceDao = new CompteEpargneDao();

	@Override
	public int addCompteEpargne(CompteEpargne ceIn) {
		return ceDao.addCompteEpargne(ceIn);
	}

	@Override
	public int deleteCompteEpargne(CompteEpargne ceIn) {
		return ceDao.deleteCompteEpargne(ceIn);
	}

	@Override
	public int updateCompteEpargne(CompteEpargne ceIn) {
		return ceDao.updateCompteEpargne(ceIn);
	}

	@Override
	public ArrayList<CompteEpargne> getAllCompteEpargne(Conseiller cIn) {
		return ceDao.getAllCompteEpargne(cIn);
	}

	@Override
	public CompteEpargne getCompteEpargne(CompteEpargne ceIn) {
		return ceDao.getCompteEpargne(ceIn);
	}

	@Override
	public CompteEpargne getCompteEpargneIdClt(Client cltIn) {
		return ceDao.getCompteEpargneIdClt(cltIn);
	}

	@Override
	public int removeAddMoney(CompteEpargne ceIn) {
		return ceDao.removeAddMoney(ceIn);
	}

	
	
}
