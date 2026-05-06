package fr.adaming.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.HistoriqueCommande;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IHistComService;

@Controller
@RequestMapping("/client")
//@Scope("session") //definir le scope du controller comme session; il va instancier un objet de cette classe pour toute la session

public class ControllerClient {

	@Autowired
	private IClientService cltService;

	@Autowired
	private IHistComService histComService;

	@Autowired
	private ICommandeService comService;


	private Client client;

	@InitBinder // cette annotation permet de definir la methode à appeler lors
	// d'une conversion des données
	public void initBinding(WebDataBinder binder) {
		// l'objet de type WebDataBinder permet de lier les params de la requete
		// aux attribut de l'objet java

		// specifier le format String de la date à converir en objet de type
		// java.util.Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// forcer la methode de conversion à lever une exception de type
		// ParseException si la date recue ne coressepond pas au pattern demande
		sdf.setLenient(false);

		// la methode registerCustomEditor() permet de configuer la conversion
		// du paramettre de la requete reçue au type de l'attribut concernés
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));

	}

	@GetMapping("/cltLogin")
	public ModelAndView displayLoginClt() {
		return new ModelAndView("loginClient", "cltLogin", new Client());
	}

	@PostMapping("/submitLoginClt")
	public String submitLoginClient(ModelMap modelMVC, @ModelAttribute("cltLogin") Client cltIn) {
		Client cltOut = cltService.getClientByMailMdp(cltIn);

		client = cltService.getClientByMailMdp(cltIn);

		if (cltOut != null) {
			if (cltOut.isActive()) {
				return "redirect:cltHome";
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

	@GetMapping("/cltHome")
	public ModelAndView displayClientH() {

		ModelAndView modelMVC = new ModelAndView("accueilClient", "client", client);
		List<HistoriqueCommande> list = histComService.getAllCommandeByIdClients(client);
		System.out.println("test" + list.get(1));
		System.out.println("test" + list.get(0));
		List<Commande> liste = comService.getAllCommandes();
		for (Commande historiqueCommande : liste) {
			System.out.println(historiqueCommande.getDate());

		}

		System.out.println("taille" + list.size());

		modelMVC.addObject("listCommande", list);

		return modelMVC;
	}

	@GetMapping("/gestionClient")
	public ModelAndView displaygestionClient() {
		return new ModelAndView("modifClient", "client", client);
	}

	// b: la methode en POST pour soumettre (traiter) le formmulaire de l'ajout
	@PostMapping("/submitUpd")
	public String soumettreModif(Model modelMVC, @ModelAttribute("client") Client clt, RedirectAttributes rda) {

		// le client ne peut pas toucher a l'id de son identifiant

		// appel de la methode service afin de modifier le client dans la bd
		int cOut = cltService.updateClient(clt);

		if (cOut == 0) {
			// ajouter une message d'erreur dans le modele mvc
			rda.addFlashAttribute("msg", "la modif est KO");
			return "redirect:gestionClient";
		} else {
			if (clt.getMail().equals(client.getMail()) && clt.getMdp().equals(client.getMdp())) {
				// pour prendre en compte les modifs
				client = clt;
				return "redirect:cltHome";
			} else {
				rda.addFlashAttribute("msg", "le mdp ou le mail a bien été modifié veuillez vous reconnecter");
				return "redirect:cltLogin";
			}
		}
	}

	// ================la fonctionnalite creation compte client
	// a: la methode en GET apres appuies sur le bouton creation du compte
	// pour afficher le formulaire et lui assoier un modele mvc

	@GetMapping("/createClt")
	public ModelAndView afficheAjout() {

		// l'objet ModelAndView contient l'identifiant de la page et l'etudiant associé
		// au formulaire
		return new ModelAndView("creerCompteClient", "cltAdd", new Client());

	}

	// b: la methode en POST pour soumettre (traiter) le formmulaire de l'ajout
	@PostMapping("/submitAdd")
	public String soumettreAjout(Model modelMVC, @ModelAttribute("cltAdd") Client clt, RedirectAttributes rda) {

		// appel de la methode service afin d'ajouter l'etudaint dans la bd
		Client cltOut = cltService.addClient(clt);

		if (cltOut != null) {

			System.out.println();
			return "redirect:cltLogin";
		} else {
			// si le mail est deja utilisé renvoie une erreur
			rda.addFlashAttribute("msg", "le mail est deja utilisé");

			return "redirect:createClt";
		}
	}

}
