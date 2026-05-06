package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Role;

public interface IAdminService {
	
	public List<Admin> getAllAdmins();

	public Admin addAdmin(Admin adIn);

	public int updateAdmin(Admin adIn);

	public boolean deleteAdmin(Admin adIn);

	public Admin getAdminById(Admin adIn);
	
	public Admin getAdminByMail(Admin adIn);

	public Admin getAdminByRole(Admin adIn);

}
