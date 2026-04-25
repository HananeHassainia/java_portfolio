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
import fr.adaming.service.CompteEpargneService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteCourantService;
import fr.adaming.service.ICompteEpargneService;
import fr.adaming.utils.CreatePdf;

@WebServlet(name = "ajouterCE", urlPatterns = "/addCE")
public class AjouterCE extends HttpServlet{
	
//  Transformation de l'association UML en JAVA
	private IClientService cltService = new ClientService();
	private ICompteEpargneService ceService = new CompteEpargneService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("passe par get Ajouter");
		// Récupérer la session http à partir de l'objet req si elle existe
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		// récupérer la liste des etudiant du formateur connecté
		ArrayList<Client> listeClt = cltService.getAllClient(conseiller);

		ArrayList<Client> listeCltNoCE = new ArrayList<Client>();

		for (Client ele : listeClt) {
			if (ele.getCe() == null) {
				listeCltNoCE.add(ele);
			}
		}
		
		if(listeCltNoCE.size()!=0) {
			req.setAttribute("listeCltNoCE", listeCltNoCE);
		}
		
		// deleguer la requête à la jsp login.jsp
		// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
		// vue du design pattern mcv)
		RequestDispatcher rd = req.getRequestDispatcher("addCE.jsp");

		// Délégué la requete
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("passe par post ajout");
		
		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		// Recupérer les parametres de la requete
		double solde = Double.parseDouble(req.getParameter("pSolde"));
		int idClt = Integer.parseInt(req.getParameter("pIdClt"));
		double tde = Double.parseDouble(req.getParameter("pTde"));

		
		Client cltIn = new Client();
		cltIn.setId(idClt);

		Client cltOut = cltService.getClient(cltIn, conseiller);
		
		
		if (cltOut != null) {
			CompteEpargne ceIn = new CompteEpargne(solde, tde, cltOut);
			int verif = ceService.addCompteEpargne(ceIn);

			if (verif != 0) {
				List<CompteEpargne> listeCE = ceService.getAllCompteEpargne(conseiller);				
				
				cltOut = cltService.getClient(cltIn, conseiller);

				req.setAttribute("compteE", cltOut.getCe());
				req.setAttribute("listeCE", listeCE);
				if(cltOut.getCc()==null) {
					CreatePdf.newPdf(cltOut.getNom()+cltOut.getPrenom()+String.valueOf(cltOut.getId())+"CE", cltOut, cltOut.getCe(), conseiller);
				}
				RequestDispatcher rd = req.getRequestDispatcher("homeCE.jsp");

				rd.forward(req, resp);
			} else {
				// La variable message va jouer le rôle du model du design pattern
				String message = "L'ajout a échoué";

				// ajouter message comme attribut à la req qui va être délégué à la jsp :
				// transmission à la jsp login
				req.setAttribute("msg", message);
				RequestDispatcher rd = req.getRequestDispatcher("addCE.jsp");

				// deleguer contraitement la requête
				rd.forward(req, resp);
			}
		} else {
			// La variable message va jouer le rôle du model du design pattern
			String message = "Le client n'existe pas";

			// ajouter message comme attribut à la req qui va être délégué à la jsp :
			// transmission à la jsp login
			ArrayList<Client> listeClt = cltService.getAllClient(conseiller);
			ArrayList<Client> listeCltNoCE = new ArrayList<Client>();

			for (Client ele : listeClt) {
				if (ele.getCe() == null) {
					listeCltNoCE.add(ele);
				}
			}

			CompteEpargne cNoExist = new CompteEpargne(solde, tde, cltIn);
			req.setAttribute("listeCltNoCE", listeCltNoCE);
			
			req.setAttribute("msg", message);
			
			req.setAttribute("cNoExist", cNoExist);
			RequestDispatcher rd = req.getRequestDispatcher("addCE.jsp");

			// deleguer contraitement la requête
			rd.forward(req, resp);
		}

	}

}
