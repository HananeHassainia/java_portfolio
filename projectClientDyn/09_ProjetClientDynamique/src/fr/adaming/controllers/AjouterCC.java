package fr.adaming.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.adaming.entities.Client;
import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteCourantService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteCourantService;
import fr.adaming.utils.CreatePdf;

@WebServlet(name = "ajouterCC", urlPatterns = "/addCC")

public class AjouterCC extends HttpServlet {
	// Transformation de l'association UML en JAVA
	private IClientService cltService = new ClientService();
	private ICompteCourantService ccService = new CompteCourantService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récupérer la session http à partir de l'objet req si elle existe
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		// récupérer la liste des etudiant du formateur connecté
		ArrayList<Client> listeClt = cltService.getAllClient(conseiller);

		ArrayList<Client> listeCltNoCC = new ArrayList<Client>();

		for (Client ele : listeClt) {
			if (ele.getCc() == null) {
				listeCltNoCC.add(ele);
			}
		}
		if(listeCltNoCC.size()!=0) {
			req.setAttribute("listeCltNoCC", listeCltNoCC);
		}
		else {
			req.setAttribute("msg", "Tous les clients possède un compte épargne : ajouter un client");
		}
		// deleguer la requête à la jsp login.jsp
		// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
		// vue du design pattern mcv)
		RequestDispatcher rd = req.getRequestDispatcher("addCC.jsp");

		// Délégué la requete
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		// Recupérer les parametres de la requete
		double solde = Double.parseDouble(req.getParameter("pSolde"));
		int idClt = Integer.parseInt(req.getParameter("pIdClt"));
		double decouvert = Double.parseDouble(req.getParameter("pDecouvert"));
		
		Client cltIn = new Client();
		cltIn.setId(idClt);

		Client cltOut = cltService.getClient(cltIn, conseiller);

		if (cltOut != null) {
			CompteCourant ccIn = new CompteCourant(solde, decouvert, cltOut);
			int verif = ccService.addCompteCourant(ccIn);


			if (verif != 0) {
				List<CompteCourant> listeCC = ccService.getAllCompteCourant(conseiller);				
				
				cltOut = cltService.getClient(cltIn, conseiller);


				req.setAttribute("compteC", cltOut.getCc());
				req.setAttribute("listeCC", listeCC);

				RequestDispatcher rd = req.getRequestDispatcher("homeCC.jsp");

				rd.forward(req, resp);
				
				if(cltOut.getCe()==null) {
					CreatePdf.newPdf(cltOut.getNom()+cltOut.getPrenom()+String.valueOf(cltOut.getId())+"CC", cltOut, cltOut.getCc(), conseiller);
				}
			} else {
				// La variable message va jouer le rôle du model du design pattern
				String message = "L'ajout a échoué";

				// ajouter message comme attribut à la req qui va être délégué à la jsp :
				// transmission à la jsp login
				req.setAttribute("msg", message);
				RequestDispatcher rd = req.getRequestDispatcher("addCC.jsp");

				// deleguer contraitement la requête
				rd.forward(req, resp);
			}
		} else {
			// La variable message va jouer le rôle du model du design pattern
			String message = "Le client n'existe pas";

			ArrayList<Client> listeClt = cltService.getAllClient(conseiller);

			ArrayList<Client> listeCltNoCC = new ArrayList<Client>();

			for (Client ele : listeClt) {
				if (ele.getCe() == null) {
					listeCltNoCC.add(ele);
				}
			}
			
			CompteCourant cNoExist = new CompteCourant(solde, decouvert, cltIn);
			req.setAttribute("listeCltNoCC", listeCltNoCC);
			
			req.setAttribute("msg", message);
			
			req.setAttribute("cNoExist", cNoExist);
			RequestDispatcher rd = req.getRequestDispatcher("addCC.jsp");
			
			// deleguer contraitement la requête
			rd.forward(req, resp);
		}

	}

}
