package fr.adaming.dao;

import fr.adaming.entities.Client;
import fr.adaming.entities.Conseiller;

public interface IConseillerDao {
	
	public Conseiller inspect(Conseiller cIn);

	public int updateConseiller(Conseiller cIn);

}
