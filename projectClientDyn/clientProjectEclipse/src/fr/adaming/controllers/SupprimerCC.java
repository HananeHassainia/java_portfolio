package fr.adaming.controllers;

import java.io.IOException;
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
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteCourantService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteCourantService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;

@WebServlet(name = "suppCC", urlPatterns = "/deleteCC")
public class SupprimerCC extends HttpServlet {
	private IClientService cltService = new ClientService();
	private ICompteCourantService ccService = new CompteCourantService();
	private ITransactionService tService = new TransactionService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Recupérer les parametres de la requete
		int id = Integer.parseInt(req.getParameter("pId"));

		// instancier un étudiant avec les paramtres
		CompteCourant ccIn = new CompteCourant();
		ccIn.setId(id);

		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le Client de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		// appel de la méthode service

		int verif = ccService.deleteCompteCourant(ccIn);

		if (verif != 0) {
			// aller dans acceuil et récupérer la nouvelle liste
			// récupérer la liste des etudiant du formateur connecté
			System.out.println("supprime cc ok");
			List<CompteCourant> listeCC = ccService.getAllCompteCourant(conseiller);

			if (listeCC.size() != 0) {
				req.setAttribute("listeCC", listeCC);
				req.setAttribute("compteC", listeCC.get(0));

				List<Transaction> listeTr = tService.getAllTransactionbyCC(listeCC.get(0));

				if (listeTr.size() != 0) {
					req.setAttribute("listeTr", listeTr);
				}
			}
			// deleguer la requête à la jsp login.jsp
			// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
			// vue du design pattern mcv)
			RequestDispatcher rd = req.getRequestDispatcher("homeCC.jsp");

			// Délégué la requete
			rd.forward(req, resp);
		} else {
			// La variable message va jouer le rôle du model du design pattern
			String message = "La suppression a échoué";

			// ajouter message comme attribut à la req qui va être délégué à la jsp :
			// transmission à la jsp login
			req.setAttribute("msg", message);

			// deleguer la requête à la jsp login.jsp
			// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
			// vue du design pattern mcv)
			RequestDispatcher rd = req.getRequestDispatcher("homeCC.jsp");

			// deleguer contraitement la requête
			rd.forward(req, resp);
		}

	}
}
