package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IClientDao;
import fr.adaming.entities.Client;

@Service
public class ClientService implements IClientService {

	@Autowired
	IClientDao cltDao;

	@Override
	public Client getClientByMailMdp(Client cltIn) {
		return cltDao.getClientByMailMdp(cltIn);
	}
	
	@Override
	public Client getClientByMail(Client cltIn) {
		return cltDao.getClientByMailMdp(cltIn);
	}

	@Override
	public List<Client> getAllClients() {
		// appel de la methode Dao
		return cltDao.getAllClients();
	}

	@Override
	public Client addClient(Client cltIn) {
		
		// on verifie que le mail est unique 
		List<Client> list= cltDao.getAllClients();
		for (Client client : list) {
			if (cltIn.getAdresse().equals(client.getAdresse())){
				System.out.println(client.getAdresse());
				return null;
			}
			
		}
		
		// appel de la methode dao
		return cltDao.addClient(cltIn);
	}

	@Override
	public int updateClient(Client cltIn) {

		// appel de la methode dao
		return cltDao.updateClient(cltIn);
	}

	@Override
	public boolean deleteClient(Client cltIn) {
		// recuperer l'Client par son id
		Client cOut = this.getClientById(cltIn);
		if (cOut != null) {

			// appel de la methode dao
			return cltDao.deleteClient(cOut);
		}

		return false;
	}

	@Override
	public Client getClientById(Client cltIn) {

		// appel de la metode dao
		return cltDao.getClientById(cltIn);
	}

	@Override
	public Client getLastClient() {
		return cltDao.getLastClient();
	}

}
