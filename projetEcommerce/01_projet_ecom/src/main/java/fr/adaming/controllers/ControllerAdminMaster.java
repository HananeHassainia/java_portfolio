package fr.adaming.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.Admin;
import fr.adaming.entities.Role;
import fr.adaming.service.IAdminService;

@Controller
@RequestMapping("/master")
public class ControllerAdminMaster {

	// transformer l'association uml en java
	@Autowired
	private IAdminService adminService;

	// =====================Gestion des admins
	// Afficher l'accueil de la partie Admin

	// 1: Recuperer la liste des Admins
	@GetMapping("/listeAdmin")
	public String afficheListeAdmin(Model modelMVC) {
		// recuperer la liste des Admins connecté
		
		List<Admin> liste = adminService.getAllAdmins();
		// ajouter la liste recuperé comme attribut du model MVC pour que la page
		// getAllAdmin puisse l afficher
		if ((liste.size() != 0)) {
			modelMVC.addAttribute("Admins", liste);
			modelMVC.addAttribute("aSearch", new Admin());
			modelMVC.addAttribute("aUpdate", new Admin());
			modelMVC.addAttribute("admin", liste.get(0));
			
		}
		return "accueilAdmin";
	}

	// 2: ajouter un Admin
	@GetMapping("/ajouteAdmin")
	public ModelAndView afficheAjoutAdmin() {
		// l'objet modelAndView contient l identifiant de la page et l admin associé au
		// formulaire
		return new ModelAndView("addAdmin", "aAdd", new Admin());
	}

	@PostMapping("/submitAdd")
	public String soumettreAjoutAdmin(ModelMap modelMVC, @ModelAttribute("aAdd") Admin admin, RedirectAttributes rda,  @RequestParam("pActive") boolean active) {
		// Appel de la methode service afin d ajouter un admin dans la base de données
		admin.setActive(active);
		Admin aOut = adminService.addAdmin(admin);
		if (aOut == null) {
			modelMVC.addAttribute("msg", "L'ajout n'a pas abouti");
			return "redirect:ajouteAdmin";
		} else {
			modelMVC.addAttribute("msg1", "L'admin a été bien ajouté");
			return "redirect:listeAdmin";
		}

	}

	// 3: La fonctionalité Rechercher

	@PostMapping("/submitSearch")
	public String soumettreRechercheAdmin(Model modelMVC, @ModelAttribute("aSearch") Admin admin,
			RedirectAttributes rda) {

		// Appel la méthode service afin de rechercher Admin dans la BD
		Admin aOut = adminService.getAdminById(admin);
		List<Admin> listeAdmins = adminService.getAllAdmins();

		if (aOut != null) {
			System.out.println(aOut.getId());
			modelMVC.addAttribute("Admins", listeAdmins);
			modelMVC.addAttribute("aUpdate", new Admin());
			modelMVC.addAttribute("admin", aOut);
			return "accueilAdmin";

		} else {
			rda.addFlashAttribute("msg1", "Cet admin n'existe pas");
			return "redirect:listeAdmin";
		}
	}

	// 4: Modifier un Admin
	@PostMapping("/submitUpdate")
	public String soumettreModifAdmin(Model modelMVC, @ModelAttribute("aUpdate") Admin admin, RedirectAttributes rda, @RequestParam("pActive") boolean active
			) {

		// Appel de la methode service afin de modifier l admin dans la bd
		admin.setActive(active);
		int aModif = adminService.updateAdmin(admin);
		Admin admin1 = adminService.getAdminById(admin);

		List<Admin> listeAdmins = adminService.getAllAdmins();
		if (aModif == 0 ) {
			// ajouter un message d'erreur dans le modele MVC
			rda.addFlashAttribute("msg2", "L'admin n'a pas été modifié");
			return "redirect:listeAdmin";
		} else {

			modelMVC.addAttribute("Admins", listeAdmins);
			modelMVC.addAttribute("aSearch", new Admin());
			modelMVC.addAttribute("aUpdate", new Admin());
			modelMVC.addAttribute("admin", admin1);
			
			return "accueilAdmin";
		}
	}
	
	//===== Afficher la page de modifAdminMaster 

	// 5: Supprimer un Admin
	@GetMapping("/displayDelete")
	public ModelAndView suppressionAdmin() {

		return new ModelAndView("supprAdmin", "aDelete", new Admin());
	}
	
	@PostMapping("/submitDeleteAdmin")
	public String soumettreSupprAdmin(Model modelMVC, @ModelAttribute("aDelete") Admin admin, RedirectAttributes rda) {

		// Appel de la méthode service
		boolean verif = adminService.deleteAdmin(admin);

		// Récupérer la liste des admins
		List<Admin> listeAdmins = adminService.getAllAdmins();
		
		if (verif) {

			modelMVC.addAttribute("aSearch", new Admin());
			modelMVC.addAttribute("aUpdate", new Admin());
			
			// S'assurer que la liste n'est pas vide (éviter une potentielle erreur)
			if (listeAdmins.size()!=0) {
				
				modelMVC.addAttribute("Admins", listeAdmins);
				modelMVC.addAttribute("admin", listeAdmins.get(0));
				modelMVC.addAttribute("msg2", "Suppression effectuée");
				
				
			}
			
			return "accueilAdmin";

		} else {

			// Ajouter un message d'erreur dans le model MVC
			modelMVC.addAttribute("msg1", "La suppression a échoué");
			modelMVC.addAttribute("admin", admin);
		}

		return "accueilAdmin";

	}
	// 4: Modifier un AdminMaster
	
	@GetMapping("/displayModif")
	public ModelAndView modificationAdminMaster() {
		// l'objet modelAndView contient l identifiant de la page et l etudiant associé
		// au formulaire
		Admin adIn = new Admin();
		adIn.setId(1);
		Admin adminMaster = adminService.getAdminById(adIn);
		System.out.println("admin master :"+adminMaster.getRole().getRolename());
		return new ModelAndView("updateAdminMaster", "admUpdate", adminMaster);

	}
	
		@PostMapping("/submitAdminUpdate")
		public String soumettreModifAdminMaster(Model modelMVC, @ModelAttribute("admUpdate") Admin admin, RedirectAttributes rda, @RequestParam("pActive") boolean active
				) {
			// Appel de la methode service afin de modifier l admin dans la bd
			admin.setActive(true);
			int aModif = adminService.updateAdmin(admin);

			if (aModif == 0 ) {
				// ajouter un message d'erreur dans le modele MVC
				modelMVC.addAttribute("msg", "L'admin n'a pas été modifié");
				return "updateAdminMaster";
			} else {
				modelMVC.addAttribute("msg1", "modification okay");
				modelMVC.addAttribute("admUpdate", admin);
				return "updateAdminMaster";
			}
		}
}
