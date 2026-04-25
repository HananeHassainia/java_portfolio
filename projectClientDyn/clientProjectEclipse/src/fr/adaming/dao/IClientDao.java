package fr.adaming.dao;

import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.Conseiller;

public interface IClientDao {
	
	public ArrayList<Client> getAllClient(Conseiller cIn);
	
	public Client getClient(Client cltIn);
	
	public int addClient(Client cltIn);
	
	public int deleteClt(Client cltIn);
	
	public int updateClient(Client cltIn);
	
}
