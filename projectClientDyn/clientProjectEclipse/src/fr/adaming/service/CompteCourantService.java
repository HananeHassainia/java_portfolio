package fr.adaming.service;

import java.util.ArrayList;

import fr.adaming.dao.CompteCourantDao;
import fr.adaming.dao.ICompteCourantDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.Conseiller;

public class CompteCourantService implements ICompteCourantService{
	ICompteCourantDao ccDao = new CompteCourantDao();
	
	@Override
	public int addCompteCourant(CompteCourant ccIn) {
		return ccDao.addCompteCourant(ccIn);
	}

	@Override
	public int deleteCompteCourant(CompteCourant ccIn) {
		return ccDao.deleteCompteCourant(ccIn);
	}

	@Override
	public int updateCompteCourant(CompteCourant ccIn) {
		return ccDao.updateCompteCourant(ccIn);
	}

	@Override
	public ArrayList<CompteCourant> getAllCompteCourant(Conseiller cIn) {
		return ccDao.getAllCompteCourant(cIn);
	}

	@Override
	public CompteCourant getCompteCourant(CompteCourant ccIn) {
		return ccDao.getCompteCourant(ccIn);
	}

	@Override
	public CompteCourant getCompteCourantIdClt(Client cltIn) {
		return ccDao.getCompteCourantIdClt(cltIn);
	}

	@Override
	public int removeAddMoney(CompteCourant ccIn) {
		return ccDao.removeAddMoney(ccIn);
	}

	@Override
	public int getIdOfLastClient() {
		return ccDao.getIdOfLastClient();
	}

}
