package fr.adaming.service;

import fr.adaming.dao.ConseillerDao;
import fr.adaming.dao.IConseillerDao;
import fr.adaming.entities.Conseiller;

public class ConseillerService implements IConseillerService{
	IConseillerDao cDao = new ConseillerDao();
	
	@Override
	public Conseiller inspect(Conseiller cIn) {
		return cDao.inspect(cIn);
	}

	@Override
	public int updateConseiller(Conseiller cIn) {
		return cDao.updateConseiller(cIn);
	}

}
