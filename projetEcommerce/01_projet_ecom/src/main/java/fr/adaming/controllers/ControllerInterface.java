package fr.adaming.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.BackCommandes;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Panier;
import fr.adaming.entities.Produit;
import fr.adaming.service.IBackCommandeService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IHistComService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;
import fr.adaming.util.Mail;
import fr.adaming.util.PdfFacture;

@Controller
public class ControllerInterface {

	@Autowired
	ICategorieService catService;

	@Autowired
	IProduitService prodService;

	@Autowired
	ILigneCommandeService lCservice;

	@Autowired
	ICommandeService cmdService;

	@Autowired
	IClientService cltService;

	@Autowired
	private IHistComService histComService;

	@Autowired
	private ICommandeService comService;

	@Autowired
	private IBackCommandeService bcService;

	@Autowired
	Panier panier;

	@Autowired
	Client client;

	@Bean
	@Scope("session")
	@PostConstruct
	public Client initClt() {
		this.client = new Client();
		return client;
	}

	@Bean
	@Scope("session")
	@PostConstruct
	public Panier init() {
		this.panier.setLignesCommandes(new ArrayList<LigneCommande>());
		return panier;
	}

	// Afficher la page d'accueil
	@GetMapping("/intHome")
	public String AfficherAccueilInt(Model modelMVC) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		List<Categorie> listCategorie = catService.getAllCategories();

		// Ajout de 2 attributs au model : un objet de type produit vide et une list
		// contenant toutes les catégories
		modelMVC.addAttribute("prdGetHeader", new Produit());
		modelMVC.addAttribute("cltAdd", new Client());

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "accueil";
	}

	// Afficher les produits en fonction du mot clé recherché
	@PostMapping("/submitGetProdAll")
	public String ChercherProduitMotCle(Model modelMVC, @RequestParam("pMotCleProd") String motCle) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		List<Categorie> listCategorie = catService.getAllCategories();

		// Récupérer la liste de toutes les catégories qui contiennent des produits
		// ayant une designation contenant le mot clé recherché
		List<Categorie> listCategorieByProductKey = catService.getCategoriesByProductKeyWord(motCle);

		// Récupérer la liste de produit ayant une designation contenant le mot clé
		// recherché
		List<Produit> listProduitByKeyWord = prodService.getProduitsByKeyWord(motCle);
		if (listProduitByKeyWord.size() != 0) {
			modelMVC.addAttribute("listCatProd", listCategorieByProductKey);
			modelMVC.addAttribute("listProdKey", listProduitByKeyWord);
		}

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("motCle", motCle);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "chercherProdCat";
	}

	// Afficher les produits en fonction du de la catégorie
	@PostMapping("/displayProdByCat")
	public String AfficherProduitCat(Model modelMVC, @RequestParam("pIdCategorie") int idCat) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();

		// Déclarer et instancer une nouvelle catégorie avec l'id de la catégorie sur
		// laquelle le client aura cliqué
		Categorie catIn = new Categorie();
		catIn.setId(idCat);

		// Récupérer la catégorie ayant pour id idCat
		Categorie catOut = catService.getCategorieById(catIn);

		// Récupérer la liste de produit de la catégorie récupérer au dessus
		List<Produit> listProdCat = prodService.getProduitByCategorie(catIn);

		for (Produit prd : listProdCat) {
			System.out.println("qt : " + prd.getQuantite());
		}

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("listProdByCat", listProdCat);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		return "afficherProdCat";

	}

	// Afficher les produits en fonction du de la catégorie après une recherche par
	// mot clé
	@PostMapping("/displayProdByCatKey")
	public String AfficherProduitCatByKey(Model modelMVC, @RequestParam("pIdCategorie") int idCat,
			@RequestParam("pMotCle") String motCle) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		// Récupérer la liste de toutes les catégories qui contiennent des produits
		// ayant une designation contenant le mot clé recherché
		List<Categorie> listCategorieByProductKey = catService.getCategoriesByProductKeyWord(motCle);

		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();

		// Déclarer et instancer une nouvelle catégorie avec l'id de la catégorie sur
		// laquelle le client aura cliqué
		Categorie catIn = new Categorie();
		catIn.setId(idCat);

		// Récupérer la catégorie ayant pour id idCat
		Categorie catOut = catService.getCategorieById(catIn);

		// Récupérer la liste de produit de la catégorie récupérer au dessu s
		List<Produit> listProdCat = prodService.getProduitsByKeyWordByCat(motCle, catIn);

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("listProdKey", listProdCat);
		modelMVC.addAttribute("listCatProd", listCategorieByProductKey);
		modelMVC.addAttribute("motCle", motCle);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "chercherProdCat";
	}

//	Afficher les produits en fonction du de la catégorie après une recherche par mot clé
	@PostMapping("/DisplayProductPage")
	public String ConsulterProduit(Model modelMVC, @RequestParam("pIdProd") long idProd) {
		// Pour le nav catégorie
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}

		List<Categorie> listCategorie = catService.getAllCategories();

		// Déclarer et instancer une nouveau avec l'id du produit sur
		// lequel le client aura cliqué
		Produit pIn = new Produit();
		pIn.setId(idProd);
		// Récupérer la catégorie ayant pour id idCat
		Produit pOut = prodService.getProduitById(pIn);
		List<Integer> qt = new ArrayList<Integer>();

		for (Integer i = 1; i <= pOut.getQuantite(); i++) {
			qt.add(i);
		}

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("produitCons", pOut);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		if (qt.size() != 0) {
			modelMVC.addAttribute("listQt", qt);
		}
		return "consulterProduit";
	}

//	Afficher les produits en fonction du de la catégorie après une recherche par mot clé
	@PostMapping("/addProductToSc")
	public String ajoutPanier(Model modelMVC, @RequestParam("pIdProd") long idProd,
			@RequestParam("pProduitQuantite") int qtAchat) {
		// Pour le nav catégorie
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}

		List<Categorie> listCategorie = catService.getAllCategories();

		// Déclarer et instancer une nouveau avec l'id du produit sur
		// lequel le client aura cliqué
		Produit pIn = new Produit();
		pIn.setId(idProd);
		// Récupérer la catégorie ayant pour id idCat
		Produit pOut = prodService.getProduitById(pIn);

		int c = 0;

		for (LigneCommande lc : panier.getLignesCommandes()) {
			if (lc.getProduit().getId().equals(pOut.getId())) {
				c++;
				lc.setQuantite(lc.getQuantite() + qtAchat);
				lc.setPrix(lc.getPrix() + pOut.getPrix() * qtAchat);
				pOut.setQuantite(pOut.getQuantite() - qtAchat);
				lCservice.updateLigneCommande(lc);
				prodService.updateProduit(pOut);

			}
		}

		if (c == 0) {

			// Declaration et instanciation d'une nouvelle ligne de commande
			LigneCommande lC = new LigneCommande(qtAchat, pOut.getPrix() * qtAchat);

			// transformation de l'association uml en java
			lC.setProduit(pOut);

			LigneCommande lcIn = lCservice.addLigneCommande(lC);
			if (lcIn != null) {
				panier.getLignesCommandes().add(lcIn);
				pOut.setQuantite(pOut.getQuantite() - qtAchat);
				prodService.updateProduit(pOut);
				modelMVC.addAttribute("msg", "ajout dans le panier");
			} else {
				modelMVC.addAttribute("msg1", "erreur");
			}
		}

		// Liste quantité
		List<Integer> qt = new ArrayList<Integer>();

		for (Integer i = 1; i <= pOut.getQuantite(); i++) {
			qt.add(i);
		}

		modelMVC.addAttribute("msg", "ajout LigneCommande dans la bdd");
		modelMVC.addAttribute("produitCons", pOut);
		modelMVC.addAttribute("listQt", qt);
		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "consulterProduit";
	}

//	Afficher les produits en fonction du de la catégorie après une recherche par mot clé
	@PostMapping("/addProductToPfast")
	public String ajoutPanierRapide(Model modelMVC, @RequestParam("pIdProd") long idProd,
			@RequestParam("pMotCleProd") String motCle) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();

		// Déclarer et instancer une nouveau avec l'id du produit sur
		// lequel le client aura cliqué
		Produit pIn = new Produit();
		pIn.setId(idProd);
		// Récupérer la catégorie ayant pour id idCat
		Produit pOut = prodService.getProduitById(pIn);

		// Verifier si le produit est déjà dans le panier pour éviter les doublons
		// Si le produit est déjà dans le panier : on change juste la quantité de la
		// ligne de commande
		int c = 0;

		for (LigneCommande lc : panier.getLignesCommandes()) {
			if (lc.getProduit().getId().equals(pOut.getId())) {
				c++;
				lc.setQuantite(lc.getQuantite() + 1);
				lc.setPrix(lc.getPrix() + pOut.getPrix());
				pOut.setQuantite(pOut.getQuantite() - 1);
				lCservice.updateLigneCommande(lc);
				prodService.updateProduit(pOut);

			}
		}

		if (c == 0) {
			// Declaration et instanciation d'une nouvelle ligne de commande
			LigneCommande lC = new LigneCommande(1, pOut.getPrix());

			// transformation de l'association uml en java
			lC.setProduit(pOut);
			LigneCommande lcIn = lCservice.addLigneCommande(lC);
			if (lcIn != null) {
				panier.getLignesCommandes().add(lcIn);
				pOut.setQuantite(pOut.getQuantite() - 1);
				prodService.updateProduit(pOut);
				modelMVC.addAttribute("msg", "ajout dans le panier");
			} else {
				modelMVC.addAttribute("msg1", "erreur");
			}
		}

		// Récupérer la liste de produit ayant une designation contenant le mot clé
		// recherché
		List<Produit> listProduitByKeyWord = prodService.getProduitsByKeyWord(motCle);
		List<Categorie> listCategorieByProductKey = catService.getCategoriesByProductKeyWord(motCle);
//		List<Produit> listProdCat = prodService.getProduitsByKeyWordByCat(motCle, catService.getCategorieById(pOut.getCategorie()));

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("listCatProd", listCategorieByProductKey);
		modelMVC.addAttribute("listProdKey", listProduitByKeyWord);
		modelMVC.addAttribute("motCle", motCle);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "chercherProdCat";
	}

//	Afficher les produits en fonction du de la catégorie après une recherche par mot clé
	@PostMapping("/addProductToPfastCat")
	public String ajoutPanierRapideParCat(Model modelMVC, @RequestParam("pIdProd") long idProd) {
		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();

		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}

		// Déclarer et instancer une nouveau avec l'id du produit sur
		// lequel le client aura cliqué
		Produit pIn = new Produit();
		pIn.setId(idProd);
		// Récupérer la catégorie ayant pour id idCat
		Produit pOut = prodService.getProduitById(pIn);

		// Verifier si le produit est déjà dans le panier pour éviter les doublons
		// Si le produit est déjà dans le panier : on change juste la quantité de la
		// ligne de commande
		int c = 0;

		for (LigneCommande lc : panier.getLignesCommandes()) {
			if (lc.getProduit().getId().equals(pOut.getId())) {
				c++;
				lc.setQuantite(lc.getQuantite() + 1);
				lc.setPrix(lc.getPrix() + pOut.getPrix());
				lCservice.updateLigneCommande(lc);
				pOut.setQuantite(pOut.getQuantite() - 1);
				prodService.updateProduit(pOut);

			}
		}

		if (c == 0) {
			// Declaration et instanciation d'une nouvelle ligne de commande
			LigneCommande lC = new LigneCommande(1, pOut.getPrix());

			// transformation de l'association uml en java
			lC.setProduit(pOut);
			LigneCommande lcIn = lCservice.addLigneCommande(lC);
			if (lcIn != null) {
				panier.getLignesCommandes().add(lcIn);
				pOut.setQuantite(pOut.getQuantite() - 1);
				prodService.updateProduit(pOut);
				modelMVC.addAttribute("msg", "ajout dans le panier");
			} else {
				modelMVC.addAttribute("msg1", "erreur");
			}
		}

		Categorie catIn = new Categorie();
		catIn.setId(pOut.getCategorie().getId());

		// Récupérer la catégorie ayant pour id idCat
		Categorie catOut = catService.getCategorieById(catIn);

		// Récupérer la liste de produit de la catégorie récupérer au dessus
		List<Produit> listProdCat = prodService.getProduitByCategorie(catIn);

		for (Produit prd : listProdCat) {
			if (prd.getId() == pOut.getId()) {
				System.out.println("okkk equals");
				prd.setQuantite(pOut.getQuantite());

			}
			System.out.println("qt : " + prd.getQuantite());
		}

		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("listProdByCat", listProdCat);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "afficherProdCat";
	}

	@GetMapping("/displayPanier")
	public String AfficherLePanier(Model modelMVC) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();

		modelMVC.addAttribute("listCat", listCategorie);
		if (panier.getLignesCommandes().size() != 0) {

			double total = 0;
			for (LigneCommande lc : panier.getLignesCommandes()) {
				System.out.println("total = " + total);
				total = total + lc.getPrix();
			}

			modelMVC.addAttribute("total", total);
			modelMVC.addAttribute("panier", panier.getLignesCommandes());
		}
		return "panier";
	}



//	Supprimer article du panier
	@PostMapping("/deleteProdPanier")
	public String SupprimerArticlePanier(Model modelMVC, @RequestParam("pIdProd") long idProd) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();

		modelMVC.addAttribute("listCat", listCategorie);

		Panier newPanier = new Panier();
		newPanier.setLignesCommandes(new ArrayList<LigneCommande>());
		for (LigneCommande lc : panier.getLignesCommandes()) {
			if (!lc.getProduit().getId().equals(idProd)) {
				newPanier.getLignesCommandes().add(lc);
			} else {
				int qt = lc.getQuantite();
				Produit prod = prodService.getProduitById(lc.getProduit());
				prod.setQuantite(prod.getQuantite() + qt);
				prodService.updateProduit(prod);
			}
		}
		panier = newPanier;

		double total = 0;

		for (LigneCommande lc : panier.getLignesCommandes()) {
			System.out.println("total = " + total);
			total = total + lc.getPrix();
		}

		modelMVC.addAttribute("total", total);

		if (panier.getLignesCommandes().size() != 0) {
			modelMVC.addAttribute("panier", panier.getLignesCommandes());
		}
		return "panier";
	}

//	Supprimer article du panier
	@PostMapping("/order")
	public String passerCommande(Model modelMVC, @RequestParam("pCodePromo") String codePromo) {
		if (client.getNom() != null) {
			modelMVC.addAttribute("client", client);
		}
		// Pour le nav catégorie
		List<Categorie> listCategorie = catService.getAllCategories();
		modelMVC.addAttribute("listCat", listCategorie);
		Commande cmd = new Commande();

		if (client.getNom() != null) {
			Client cltIn = client;

			cmd.setClient(cltService.getClientById(cltIn));
			cmd.setLignesCommandes(panier.getLignesCommandes());

			Commande cmdOut = cmdService.addCommande(cmd);
			if (cmdOut != null) {
				double total =0;
				double totalFinal;
				for (LigneCommande lc : panier.getLignesCommandes()) {
					lc.setCommande(cmdService.getLastCommande());
					lCservice.updateLigneCommande(lc);
					Date date = new Date();

					BackCommandes bc = new BackCommandes(cmdService.getLastCommande().getId(), date,
							lc.getProduit().getId(), lc.getProduit().getDesignation(), lc.getProduit().getDescription(),
							lc.getProduit().getPrix(), lc.getProduit().getImage(), lc.getProduit().getPhoto(),
							lc.getQuantite(), lc.getPrix());
					bc.setClient(client);
					
					if (codePromo.equals("Nomane2022")) {
						bc.setPromo(true);	
					} 
					else {
						bc.setPromo(false);
					}

					BackCommandes bcadd = bcService.addBackCommande(bc);
					total = total + lc.getPrix();

				}

				boolean promo;
				// calculer le total de la commande avec code promo
				if (codePromo.equals("Nomane2022")) {
					totalFinal = total -(total * 0.3);
					promo=true;
					
				} else {
					promo=false;
				}

				PdfFacture.newPdf(cltService.getClientById(cltIn), panier, promo);
				Mail.sendMail(cltService.getClientById(cltIn));
				panier.setLignesCommandes(new ArrayList<LigneCommande>());
				modelMVC.addAttribute("panier", panier.getLignesCommandes());

				modelMVC.addAttribute("msg2", "Commande validé");
			}

			else {
				modelMVC.addAttribute("msg1", "Erreur la commande n'est pas passée");
			}

			return "apresCommande";
		} else {
			modelMVC.addAttribute("cltLogin", new Client());
			modelMVC.addAttribute("cltAdd", new Client());
			modelMVC.addAttribute("panier", panier.getLignesCommandes());

			modelMVC.addAttribute("CommandeNvxClient", "yes");

			return "loginClient";
		}

	}

	@GetMapping("/displayEspaceClient")
	public String espaceClient(Model modelMVC) {
		System.out.println(client.getMail());
		if (this.client.getNom() != null) {
			List<Categorie> listCategorie = catService.getAllCategories();
			modelMVC.addAttribute("client", client);
			modelMVC.addAttribute("listCat", listCategorie);
			return "modifClient";
		}
		modelMVC.addAttribute("cltLogin", new Client());
		modelMVC.addAttribute("cltAdd", new Client());

		return "loginClient";
	}

	@PostMapping("/submitLoginClt")
	public String submitLoginClient(ModelMap modelMVC, @ModelAttribute("cltLogin") Client cltIn,
			@RequestParam("pCommande") String cmd) {
		Client cltOut = cltService.getClientByMailMdp(cltIn);
		List<Categorie> listCategorie = catService.getAllCategories();
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		if (cltOut != null) {
			this.client = cltService.getClientByMailMdp(cltOut);

			if (cltOut.isActive()) {
				modelMVC.addAttribute("listCat", listCategorie);
				modelMVC.addAttribute("client", client);
				if (cmd.equals("yes")) {
					return "redirect:displayPanier";
				} else {
					return "accueil";
				}
			} else {
				modelMVC.addAttribute("msg", "Votre compte n'est pas activé un admin s'en charge");
				return "loginClient";
			}
		}

		else {
			modelMVC.addAttribute("msg", "mail ou mot de passe incorrect");
			return "loginClient";
		}

	}

	@GetMapping("/createClt")
	public String afficheAjout(Model modelMVC, @RequestParam("pCommande") String cmd) {

		if (cmd.contentEquals("yes")) {
			modelMVC.addAttribute("CommandeNvxClient", "yes");
		}
		modelMVC.addAttribute("cltAdd", new Client());
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		return "creerCompteClient";

	}

	@PostMapping("/submitAdd")
	public String soumettreAjout(Model modelMVC, @ModelAttribute("cltAdd") Client clt,
			@RequestParam("pCommande") String cmd, RedirectAttributes rda) {
		List<Categorie> listCategorie = catService.getAllCategories();
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		// appel de la methode service afin d'ajouter l'etudaint dans la bd
		Client cltOut = cltService.addClient(clt);

		if (cltOut != null) {
			Mail.sendMailVerif(cltOut);
			client = cltService.getLastClient();
			modelMVC.addAttribute("listCat", listCategorie);
			modelMVC.addAttribute("client", client);
			if (cmd.equals("yes")) {
				return "redirect:displayPanier";
			} else {
				return "accueil";
			}
		} else {
			// si le mail est deja utilisé renvoie une erreur
			modelMVC.addAttribute("msg", "le mail est deja utilisé");
			return "creerCompteClient";
		}
	}

	@PostMapping("/submitUpd")
	public String soumettreModif(Model modelMVC, @ModelAttribute("client") Client clt, RedirectAttributes rda) {
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		// le client ne peut pas toucher a l'id de son identifiant

		// appel de la methode service afin de modifier le client dans la bd
		int cOut = cltService.updateClient(clt);

		if (cOut == 0) {
			// ajouter une message d'erreur dans le modele mvc
			modelMVC.addAttribute("msg1", "la modif est KO");
			return "modifClient";
		} else {
			client = clt;

			if (clt.getMail().equals(client.getMail()) && clt.getMdp().equals(client.getMdp())) {
				// pour prendre en compte les modifs
				modelMVC.addAttribute("msg2", "modification okay");
				return "modifClient";
			} else {

				modelMVC.addAttribute("msg", "le mdp ou le mail a bien été modifié veuillez vous reconnecter");
				return "loginClient";
			}
		}
	}

	@GetMapping("/decoClient")
	public String decoClient(Model modelMVC) {
		client = new Client();
		panier.setLignesCommandes(new ArrayList<LigneCommande>());
		List<Categorie> listCategorie = catService.getAllCategories();

		modelMVC.addAttribute("msgDeco", "vous êtes deconnecté");
		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());
		return "accueil";
	}

	@GetMapping("/cltHome")
	public ModelAndView displayClientH() {
		ModelAndView modelMVC = new ModelAndView("accueilClient", "client", client);

		List<BackCommandes> listeCmd = bcService.getAllBcByIdClt(client);

		List<Categorie> listCategorie = catService.getAllCategories();
		modelMVC.addObject("panier", panier.getLignesCommandes());

		if (listeCmd.size() != 0) {
			List<BackCommandes> cmdProd = bcService.getBackCommandesById(listeCmd.get(0).getId_cmd());

			double sommeT = 0;

			for (BackCommandes bc : cmdProd) {
				sommeT += bc.getPrixLc();
			}

			double promo = sommeT-(sommeT*0.3);
			modelMVC.addObject("promo", promo);

			modelMVC.addObject("listCommande", listeCmd);
			modelMVC.addObject("listPrdCmd", cmdProd);
			modelMVC.addObject("total", sommeT);
			
		}

		modelMVC.addObject("listCat", listCategorie);
		return modelMVC;
	}

	@PostMapping("/DetailCmd")
	public String detailCommande(Model modelMVC, @ModelAttribute("pCommande") int idCmd, RedirectAttributes rda) {
		System.out.println("command numero : " + idCmd);
		List<BackCommandes> listeCmd = bcService.getAllBcByIdClt(client);

		List<Categorie> listCategorie = catService.getAllCategories();
		List<BackCommandes> cmdProd = bcService.getBackCommandesById(idCmd);

		double sommeT = 0;
		List<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();

		for (BackCommandes bc : cmdProd) {
			sommeT += bc.getPrixLc();
			LigneCommande lc = new LigneCommande();
			lc.setQuantite(bc.getQuantitLc());
			lc.setPrix(bc.getPrixLc());
			listeLigneCommande.add(lc);
		}

//		String hrefpdf = PdfFacture.newPdfCommande(client, cmdProd);
//		modelMVC.addAttribute("hrefpdf", hrefpdf);
		double promo = sommeT-(sommeT*0.3);
		modelMVC.addAttribute("promo", promo);

		modelMVC.addAttribute("listCommande", listeCmd);
		modelMVC.addAttribute("listPrdCmd", cmdProd);
		modelMVC.addAttribute("selectedValue", idCmd);
		modelMVC.addAttribute("listCat", listCategorie);
		modelMVC.addAttribute("total", sommeT);
		modelMVC.addAttribute("client", client);
		modelMVC.addAttribute("panier", panier.getLignesCommandes());

		return "accueilClient";

	}
}
