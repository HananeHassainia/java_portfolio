package fr.adaming.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import fr.adaming.dao.ICategorieDao;

import fr.adaming.entities.Categorie;

@Controller
@RequestMapping("/cat")
public class ControllerAdminCat {

	@Autowired
	ICategorieDao catService;

	// =================== Partie Gestion Catégorie
	// -- Afficher l'accueil de la partie catégorie
	@GetMapping("/catHome")
	public String displayCatH(Model modelMVC) {
		// recuperer la liste du formateur connecté
		List<Categorie> listCategorie = catService.getAllCategories();

		// ajouter la liste recupérer comme attirbut du model MVC pour que la page
		// accueil puisse l'afficher
		if (listCategorie.size() != 0) {
			modelMVC.addAttribute("listCat", listCategorie);
			modelMVC.addAttribute("catGet", new Categorie());
			modelMVC.addAttribute("catUpdate", new Categorie());
			modelMVC.addAttribute("categorie", listCategorie.get(0));
		}

		return "accueilCategorie";
	}

	// -- Afficher la page ajout catégorie
	@GetMapping("/displayAddCat")
	public ModelAndView displayAddCat() {
		return new ModelAndView("ajoutCat", "catAdd", new Categorie());
	}

	// -- submit Categorie
	@PostMapping("/submitAddCat")
	public String ajoutCat(Model modelMVC, @RequestParam("pPhoto") CommonsMultipartFile filePhoto,
			@ModelAttribute("catAdd") Categorie catIn, RedirectAttributes rda) {
		// Transformation du file en byte
		// appel de la methode service afin d'ajouter l'etudiant dans la bdd
		catIn.setPhoto(filePhoto.getBytes());
		Categorie catOut = catService.addCategorie(catIn);
		if (catOut == null) {
			modelMVC.addAttribute("msg", "L'ajout n'a pas aboutit");
			return "ajoutCat"; // redirection vers un autre url une autre page
		} else {
			rda.addFlashAttribute("msg1", "La categorie a été ajouté");
			return "redirect:displayAddCat";
		}
	}

	// -- Chercher une catégorie
	@PostMapping("/submitGetCat")
	public String ChercherCat(Model modelMVC, @ModelAttribute("catGet") Categorie catIn, RedirectAttributes rda) {
		Categorie catOut = catService.getCategorieById(catIn);
		List<Categorie> listCategorie = catService.getAllCategories();

		if (catOut == null) {
			rda.addFlashAttribute("msg1", "Cette catégorie n'existe pas");
			return "redirect:catHome"; // redirection vers un autre url une autre page
		} else {
			modelMVC.addAttribute("listCat", listCategorie);
			modelMVC.addAttribute("catUpdate", new Categorie());
			modelMVC.addAttribute("categorie", catOut);
			return "accueilCategorie";
		}
	}

	// -- Modifier une catégorie
	@PostMapping("/submitUpdateCat")
	public String ModifierCat(Model modelMVC, @ModelAttribute("catUpdate") Categorie catIn, RedirectAttributes rda,
			@RequestParam("pPhoto") CommonsMultipartFile filePhoto) {
		
		if (filePhoto.isEmpty()) {
			Categorie catPhoto = catService.getCategorieById(catIn);
			catIn.setPhoto(catPhoto.getPhoto());
			
		} else {
			
			catIn.setPhoto(filePhoto.getBytes());
			
		}

		int catOut = catService.updateCategorie(catIn);

		List<Categorie> listCategorie = catService.getAllCategories();

		if (catOut == 0) {

			rda.addFlashAttribute("msg3", "La Catégorie n'a pas été modifié");
			return "redirect:catHome"; // redirection vers un autre url une autre page
	
		} else {
			
			Categorie cat = catService.getCategorieById(catIn);
			modelMVC.addAttribute("listCat", listCategorie);
			modelMVC.addAttribute("catGet", new Categorie());
			modelMVC.addAttribute("catUpdate", new Categorie());
			modelMVC.addAttribute("categorie", cat);
			return "accueilCategorie";
			
		}
	}

	// -- Supprimer une categorie
	@PostMapping("/submitDeleteCat")
	public String SupprimerCat(Model modelMVC, @ModelAttribute("catUpdate") Categorie catIn) {
		boolean catOut = catService.deleteCategorie(catIn);


		if (catOut) {
			List<Categorie> listCategorie = catService.getAllCategories();

			modelMVC.addAttribute("catGet", new Categorie());
			modelMVC.addAttribute("catUpdate", new Categorie());
			
			if (listCategorie.size() != 0) {
				modelMVC.addAttribute("categorie", listCategorie.get(0));
				modelMVC.addAttribute("listCat", listCategorie);

			}
			return "accueilCategorie";

		} else {
			modelMVC.addAttribute("msg3", "La Catégorie n'a pas été supprimé");
			modelMVC.addAttribute("categorie", catIn);
			return "accueilCategorie"; // redirection vers un autre url une autre page
		}
	}

}
