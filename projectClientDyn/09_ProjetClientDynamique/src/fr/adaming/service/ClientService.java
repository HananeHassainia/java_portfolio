package fr.adaming.service;

import java.util.ArrayList;

import fr.adaming.dao.ClientDao;
import fr.adaming.dao.ConseillerDao;
import fr.adaming.dao.IClientDao;
import fr.adaming.dao.IConseillerDao;
import fr.adaming.entities.Client;
import fr.adaming.entities.Conseiller;

public class ClientService implements IClientService{
	IClientDao cltDao = new ClientDao();
	
	@Override
	public ArrayList<Client> getAllClient(Conseiller cIn) {
		return cltDao.getAllClient(cIn);
	}

	@Override
	public Client getClient(Client cltIn, Conseiller cIn) {
		cltIn.setCs(cIn);
		return cltDao.getClient(cltIn);
	}

	@Override
	public int addClient(Client cltIn, Conseiller cIn) {
		cltIn.setCs(cIn);
		return cltDao.addClient(cltIn);
	}

	@Override
	public int deleteClt(Client cltIn, Conseiller cIn) {
		cltIn.setCs(cIn);
		return cltDao.deleteClt(cltIn);
	}

	@Override
	public int updateClient(Client cltIn, Conseiller cIn) {
		cltIn.setCs(cIn);
		return cltDao.updateClient(cltIn);
	}

}
