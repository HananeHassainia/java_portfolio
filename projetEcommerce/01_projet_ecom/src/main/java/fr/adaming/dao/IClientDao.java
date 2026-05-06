package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Client;

public interface IClientDao {

	public Client getClientByMailMdp(Client cltIn);

	public Client getClientByMail(Client cltIn);
	
	public List<Client> getAllClients();

	public Client addClient(Client cltIn);

	public int updateClient(Client cltIn);

	public boolean deleteClient(Client cltIn);

	public Client getClientById(Client cltIn);
	
	public Client getLastClient();


}
