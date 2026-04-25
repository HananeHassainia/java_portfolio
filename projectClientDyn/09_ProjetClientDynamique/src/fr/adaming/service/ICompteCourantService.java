package fr.adaming.service;

import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.Conseiller;

public interface ICompteCourantService {
	public int addCompteCourant(CompteCourant ccIn);
	
	public int deleteCompteCourant(CompteCourant ccIn);
	
	public int updateCompteCourant(CompteCourant ccIn);
	
	public ArrayList<CompteCourant> getAllCompteCourant(Conseiller cIn);
	
	public CompteCourant getCompteCourant(CompteCourant ccIn);
	
	public CompteCourant getCompteCourantIdClt(Client cltIn);
	
	public int removeAddMoney(CompteCourant ccIn);
	
	public int getIdOfLastClient();	
}
