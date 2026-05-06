package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Admin;
import fr.adaming.entities.Role;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminDao adDao;



	@Override
	public List<Admin> getAllAdmins() {
		// appel de la methode Dao
		return adDao.getAllAdmins();
	}

	@Override
	public Admin addAdmin(Admin adIn) {

		// appel de la methode dao
		return adDao.addAdmin(adIn);
	}

	@Override
	public int updateAdmin(Admin adIn) {

		// appel de la methode dao
		return adDao.updateAdmin(adIn);
	}



	@Override
	public Admin getAdminById(Admin adIn) {

		// appel de la metode dao
		return adDao.getAdminById(adIn);
	}

	@Override
	public Admin getAdminByMail(Admin adIn) {
		// appel de la metode dao
		return adDao.getAdminByMail(adIn);
	}

	@Override
	public boolean deleteAdmin(Admin adIn) {
		// Appel de la méthode Dao
		return adDao.deleteAdmin(adIn);
	}

	@Override
	public Admin getAdminByRole(Admin adIn) {
		return adDao.getAdminByRole(adIn);
	}

}
