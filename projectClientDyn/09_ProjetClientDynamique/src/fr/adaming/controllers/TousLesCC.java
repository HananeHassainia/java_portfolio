package fr.adaming.controllers;

import java.io.IOException;
import java.sql.Date;
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
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteCourantService;
import fr.adaming.service.CompteEpargneService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteCourantService;
import fr.adaming.service.ICompteEpargneService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;
import fr.adaming.utils.PasswordUtil;

@WebServlet(name = "TousCC", urlPatterns = "/homeCC")
public class TousLesCC extends HttpServlet {

	// Transformation de l'association UML en JAVA
	private ICompteCourantService ccService = new CompteCourantService();
	private ITransactionService trService = new TransactionService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		System.out.println("conseiller");
		List<CompteCourant> listeCC = ccService.getAllCompteCourant(conseiller);

		if (listeCC != null) {
			// aller dans acceuil et récupérer la nouvelle liste
			// récupérer la liste des etudiant du formateur connecté
			if (listeCC.size() != 0) {
				req.setAttribute("listeCC", listeCC);
				req.setAttribute("compteC", listeCC.get(0));

				List<Transaction> listeTr = trService.getAllTransactionbyCC(listeCC.get(0));

				if (listeTr.size() != 0) {
					req.setAttribute("listeTr", listeTr);
				}
				// deleguer la requête à la jsp login.jsp
				// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
				// vue du design pattern mcv)
				RequestDispatcher rd = req.getRequestDispatcher("homeCC.jsp");

				// Délégué la requete
				rd.forward(req, resp);

			} else {
				// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
				// vue du design pattern mcv)
				RequestDispatcher rd = req.getRequestDispatcher("addCC");

				// Délégué la requete
				rd.forward(req, resp);
			}

		}

		else {
			// La variable message va jouer le rôle du model du design pattern
			String message = "Aucun client pour le moment";

			// ajouter message comme attribut à la req qui va être délégué à la jsp :
			// transmission à la jsp login
			req.setAttribute("msg", message);

			// deleguer la requête à la jsp login.jsp
			// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
			// vue du design pattern mcv)
			RequestDispatcher rd = req.getRequestDispatcher("homeCC");

			// deleguer contraitement la requête
			rd.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
