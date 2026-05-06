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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@Controller
@RequestMapping("/prod")
public class ControllerAdminProd {

	@Autowired
	IProduitService prodService;
	
	@Autowired
	ICategorieService catService;

	// =================== Partie Gestion Produit
	// -- Afficher l'accueil de la partie produit
	@GetMapping("/prodHome")
	public String displayProductH(Model modelMVC) {

		// Récupérer la liste de l'admin concerné
		List<Categorie> listCategorie = catService.getAllCategories();

		List<Produit> listeProduits = prodService.getAllProduits();

		// Ajouter la liste récupérée comme attribut du model MVC pour que la page
		// accueil puisse l'afficher
		if (listeProduits.size() != 0) {
			modelMVC.addAttribute("listProd", listeProduits);
			modelMVC.addAttribute("prodGet", new Produit());
			modelMVC.addAttribute("prodUpdate", new Produit());
			modelMVC.addAttribute("listCat", listCategorie);

			modelMVC.addAttribute("produit", listeProduits.get(0));

		}

		return "accueilProduits";
	}

	// -- Afficher la page ajout produit
	@GetMapping("/displayAddProd")
	public String displayAddProd(Model modelMVC) {
		
		// Récupérer la liste des catégories
		List<Categorie> listCat = catService.getAllCategories();
		
		modelMVC.addAttribute("prodAdd", new Produit());
		modelMVC.addAttribute("listCat", listCat);
		
		return "ajoutProduit";
	}

	// -- submit Produit
	@PostMapping("/submitAddProd")
	public String ajoutProd(Model modelMVC, @RequestParam("pCategorie") int idCategorie, 
@RequestParam("pPhoto") CommonsMultipartFile filePhoto,
			@ModelAttribute("prodAdd") Produit prodIn, RedirectAttributes rda) {
		
		// Instancier une nouvelle catégorie
		Categorie cat = new Categorie();
		
		// Assigner l'id
		cat.setId(idCategorie);
		
		// Assigner la catégorie au produit
		prodIn.setCategorie(catService.getCategorieById(cat));
		
		// Transformation du file en byte
		// appel de la methode service afin d'ajouter le produit dans la BdD
		prodIn.setPhoto(filePhoto.getBytes());
		Produit prodOut = prodService.addProduit(prodIn);
		if (prodOut == null) {
			modelMVC.addAttribute("msg", "L'ajout n'a pas abouti");
			return "ajoutProduit"; // redirection vers un autre url une autre page
		} else {
			rda.addFlashAttribute("msg1", "Le produit a été ajouté");
			return "redirect:displayAddProd";
		}
	}

	// -- Chercher un produit
	@PostMapping("/submitGetProd")
	public String ChercherProd(Model modelMVC, @ModelAttribute("prodGet") Produit prodIn, RedirectAttributes rda) {
		List<Categorie> listCategorie = catService.getAllCategories();

		Produit prodOut = prodService.getProduitById(prodIn);
		List<Produit> listProduits = prodService.getAllProduits();

		if (prodOut == null) {
			rda.addFlashAttribute("msg1", "Ce produit n'existe pas");
			return "redirect:prodHome"; // redirection vers un autre url une autre page
		} else {
			modelMVC.addAttribute("listProd", listProduits);
			modelMVC.addAttribute("prodUpdate", new Produit());
			modelMVC.addAttribute("produit", prodOut);
			modelMVC.addAttribute("listCat", listCategorie);

			return "accueilProduits";
		}
	}

	// -- Modifier un produit
	@PostMapping("/submitUpdateProd")
	public String ModifierProd(Model modelMVC, @ModelAttribute("prodUpdate") Produit prodIn, RedirectAttributes rda,
			@RequestParam("pPhoto") CommonsMultipartFile filePhoto) {
		List<Categorie> listCategorie = catService.getAllCategories();

		if (filePhoto.isEmpty()) {
			Produit prodPhoto = prodService.getProduitById(prodIn);
			prodIn.setPhoto(prodPhoto.getPhoto());
			
		} else {
			
			prodIn.setPhoto(filePhoto.getBytes());
			
		}

		int prodOut = prodService.updateProduit(prodIn);

		List<Produit> listProduits = prodService.getAllProduits();

		if (prodOut == 0) {

			rda.addFlashAttribute("msg3", "Le Produit n'a pas été modifié");
			return "redirect:catHome"; // redirection vers un autre url une autre page
		
		} else {
			
			Produit prod = prodService.getProduitById(prodIn);
			modelMVC.addAttribute("listProd", listProduits);
			modelMVC.addAttribute("prodGet", new Produit());
			modelMVC.addAttribute("prodUpdate", new Produit());
			modelMVC.addAttribute("listCat", listCategorie);

			modelMVC.addAttribute("produit", prod);
			return "accueilProduits";
		}
	}

	// -- Supprimer un produit
	@PostMapping("/submitDeleteProd")
	public String SupprimerProd(Model modelMVC, @ModelAttribute("prodUpdate") Produit prodIn, RedirectAttributes rda) {
		List<Categorie> listCategorie = catService.getAllCategories();

		boolean prodOut = prodService.deleteProduit(prodIn);

		List<Produit> listProduits = prodService.getAllProduits();

		if (prodOut) {
			Produit prod = prodService.getProduitById(prodIn);
			
			modelMVC.addAttribute("prodGet", new Produit());
			modelMVC.addAttribute("prodUpdate", new Produit());
			
			if (listProduits.size()!=0) {
				modelMVC.addAttribute("produit", listProduits.get(0));
				modelMVC.addAttribute("listProd", listProduits);
				modelMVC.addAttribute("listCat", listCategorie);

			}
		
			return "accueilProduits";

		} else {
			rda.addFlashAttribute("msg3", "Le produit n'a pas été supprimé");
			modelMVC.addAttribute("produit", prodIn);
			modelMVC.addAttribute("listCat", listCategorie);
			modelMVC.addAttribute("listProd", listProduits);

			return "redirect:prodHome"; // redirection vers un autre url une autre page
		}
	}

}
