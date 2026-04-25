package fr.adaming.dao;

import java.util.ArrayList;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;

public interface ICompteEpargneDao {
	public int addCompteEpargne(CompteEpargne ceIn);
	
	public int deleteCompteEpargne(CompteEpargne ceIn);
	
	public int updateCompteEpargne(CompteEpargne ceIn);
	
	public ArrayList<CompteEpargne> getAllCompteEpargne(Conseiller cIn);
	
	public CompteEpargne getCompteEpargne(CompteEpargne ceIn);
	
	public CompteEpargne getCompteEpargneIdClt(Client cltIn);
	
	public int removeAddMoney(CompteEpargne ceIn);
}
