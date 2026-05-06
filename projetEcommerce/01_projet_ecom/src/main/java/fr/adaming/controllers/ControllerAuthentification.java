package fr.adaming.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.entities.Categorie;
import fr.adaming.service.ICategorieService;

@Controller
public class ControllerAuthentification {
	@Autowired
	ICategorieService catService;
	
	@GetMapping("/home")
	public ModelAndView displayHome() {
		return new ModelAndView("accueilGeneral");
	}
	
	@GetMapping("/login")
	public String afficheLogin() {
		return "loginPage";
	}

	@GetMapping("/echec")
	public String afficheErreurLogin(Model modelMVC) {
		modelMVC.addAttribute("msg", "Le login ou le mdp est erroné");
		return "loginPage";
	}

	@GetMapping("/denied")
	public String afficheAccesRefuse() {
		return "accesRefuse";
	}

	@GetMapping("/logout")
	public String afficheAccueilDeconnect(Model modelMVC) {
		List<Categorie> listCategorie = catService.getAllCategories();
		modelMVC.addAttribute("msg", "vous êtes bien déconnecté");
		modelMVC.addAttribute("listCat", listCategorie);
		return "accueil";
	}
}
