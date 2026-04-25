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
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteEpargneService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteEpargneService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;

@WebServlet(name = "chercherCE", urlPatterns = "/getCE")
public class ChercherCE extends HttpServlet {

	private IClientService cltService = new ClientService();
	private ICompteEpargneService ceService = new CompteEpargneService();
	private ITransactionService trService = new TransactionService();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Recupérer les parametres de la requete
		int id = Integer.parseInt(req.getParameter("pId"));
		String idCltCheck = req.getParameter("pIdcltCheck");
		String idCheck = req.getParameter("pIdCheck");


		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		List<CompteEpargne> listeCE = ceService.getAllCompteEpargne(conseiller);

		
		if (idCltCheck == null) {
			CompteEpargne ceIn = new CompteEpargne();
			ceIn.setId(id);
			CompteEpargne verif = ceService.getCompteEpargne(ceIn);
			if (verif != null) {
				req.setAttribute("compteE", verif);
				List<Transaction> listeTr = trService.getAllTransactionbyCE(verif);
				if(listeTr.size()!=0) {
					req.setAttribute("listeTr", listeTr);
				}
			} else {
				// La variable message va jouer le rôle du model du design pattern
				String message = "Erreur aucun compte epargne est enregistré sous cet id";

				req.setAttribute("msg", message);
				
				req.setAttribute("compteE", listeCE.get(0));

			}
		}

		else {
			Client cltIn = new Client();
			cltIn.setId(id);
			Client cltOut = cltService.getClient(cltIn, conseiller);

			if (cltOut != null) {
				CompteEpargne verif = ceService.getCompteEpargne(cltOut.getCe());
				if (verif != null) {
					req.setAttribute("compteE", verif);
					List<Transaction> listeTr = trService.getAllTransactionbyCE(verif);
					if(listeTr.size()!=0) {
						req.setAttribute("listeTr", listeTr);
					}

				} else {
					// La variable message va jouer le rôle du model du design pattern
					String message = "Erreur ce client ne possède pas de compte épargne";
					req.setAttribute("compteE", verif);
					// ajouter message comme attribut à la req qui va être délégué à la jsp :
					// transmission à la jsp login
					req.setAttribute("msg", message);
				}
			} else {
				// La variable message va jouer le rôle du model du design pattern
				String message = "Erreur ce client n'existe pas";
				req.setAttribute("compteE", listeCE.get(0));
				// ajouter message comme attribut à la req qui va être délégué à la jsp :
				// transmission à la jsp login
				req.setAttribute("msg", message);
			}
		}

		// deleguer la requête à la jsp login.jsp
		// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
		// vue du design pattern mcv)

		req.setAttribute("listeCE", listeCE);
		RequestDispatcher rd = req.getRequestDispatcher("homeCE.jsp");

		// Délégué la requete
		rd.forward(req, resp);

	}
}
