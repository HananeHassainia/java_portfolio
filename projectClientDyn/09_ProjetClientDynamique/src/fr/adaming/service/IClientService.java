package fr.adaming.service;

import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.Conseiller;

public interface IClientService {
	public ArrayList<Client> getAllClient(Conseiller cIn);

	public Client getClient(Client cltIn, Conseiller cIn);
	
	public int addClient(Client cltIn, Conseiller cIn);
	
	public int deleteClt(Client cltIn, Conseiller cIn);
	
	public int updateClient(Client cltIn, Conseiller cIn);

}
