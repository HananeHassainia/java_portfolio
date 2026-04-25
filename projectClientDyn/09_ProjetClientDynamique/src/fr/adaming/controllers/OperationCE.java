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

import fr.adaming.entities.CompteCourant;
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.CompteEpargneService;
import fr.adaming.service.ICompteEpargneService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;
import fr.adaming.utils.IntegerOfString;

@WebServlet(name = "operationCE", urlPatterns = "/opRCE")

public class OperationCE extends HttpServlet {

	private ICompteEpargneService ceService = new CompteEpargneService();
	private ITransactionService tService = new TransactionService();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("pId"));
		String type = req.getParameter("pTypeOp");
		int somme = Integer.parseInt(req.getParameter("pIdSommeV"));

		CompteEpargne ceIn = new CompteEpargne();
		ceIn.setId(id);

		CompteEpargne ceOut = ceService.getCompteEpargne(ceIn);
		int verifClt1 = 0;
		CompteEpargne ccOutV = null;
		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		switch (type) {
		case "depot":
			ceOut.setSolde(ceOut.getSolde() + somme);
			verifClt1 = ceService.updateCompteEpargne(ceOut);
			break;

		case "retrait":
			ceOut.setSolde(ceOut.getSolde() - somme);
			verifClt1 = ceService.updateCompteEpargne(ceOut);
			somme = -somme;
			break;

		case "virement":
			String cltVstr = req.getParameter("pCompteE");
			int cltV = IntegerOfString.extractInt(cltVstr);

			CompteEpargne ccInV = new CompteEpargne();
			ccInV.setId(cltV);

			ceOut.setSolde(ceOut.getSolde() - somme);

			verifClt1 = ceService.updateCompteEpargne(ceOut);

			ccOutV = ceService.getCompteEpargne(ccInV);
			ccOutV.setSolde(ccOutV.getSolde() + somme);

			int verifClt2 = ceService.updateCompteEpargne(ccOutV);

			if (verifClt2 == 0) {
				// La variable message va jouer le rôle du model du design pattern
				String message = "Virement impossible";

				// ajouter message comme attribut à la req qui va être délégué à la jsp :
				// transmission à la jsp login
				req.setAttribute("msg", message);
				
RequestDispatcher rd= req.getRequestDispatcher("homeCE.jsp");
				
				//Délégué la requete 
				rd.forward(req, resp);
			}
			break;

		default:
			// La variable message va jouer le rôle du model du design pattern
			String message = "type Inconnu";

			// ajouter message comme attribut à la req qui va être délégué à la jsp :
			// transmission à la jsp login
			req.setAttribute("msg", message);

			break;
		}

		if (verifClt1 != 0) {
			req.setAttribute("compteE", ceOut);
			
			if(type.equals("virement")) {
				String type1="virement du compte n°"+String.valueOf(ceOut.getId());
				String type2="virement vers le compte n°"+String.valueOf(ccOutV.getId());

				
				Transaction tr = new Transaction("cc", type1, somme, ccOutV);
				tService.addTransactionCe(tr);
				
				type=type2;
				somme = -somme;
			}	
			
			
			Transaction tr = new Transaction("ce", type, somme, ceOut);
			tService.addTransaction(tr);
			List<Transaction> listeTr = tService.getAllTransactionbyCE(ceOut);
			if(listeTr.size()!=0) {
				req.setAttribute("listeTr", listeTr);
			}
		}

		else {
			// La variable message va jouer le rôle du model du design pattern
			String message = "Virement impossible";

			// ajouter message comme attribut à la req qui va être délégué à la jsp :
			// transmission à la jsp login
			req.setAttribute("msg", message);
		}


		List<CompteEpargne> listeCE = ceService.getAllCompteEpargne(conseiller);

		req.setAttribute("listeCE", listeCE);
		RequestDispatcher rd = req.getRequestDispatcher("homeCE.jsp");

		// Délégué la requete
		rd.forward(req, resp);
	}

}
