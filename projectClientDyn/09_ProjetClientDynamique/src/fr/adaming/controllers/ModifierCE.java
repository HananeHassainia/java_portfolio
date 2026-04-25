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
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteEpargneService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteEpargneService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;

@WebServlet(name = "modifCE", urlPatterns = "/updateCE")
public class ModifierCE extends HttpServlet {
	private ICompteEpargneService ceService = new CompteEpargneService();
	private IClientService cltService = new ClientService();
	private ITransactionService trService = new TransactionService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recupérer les parametres de la requete 
		int id = Integer.parseInt(req.getParameter("pId"));
		double solde = Double.parseDouble(req.getParameter("pSolde"));
		double tdr = Double.parseDouble(req.getParameter("pTdr"));
		int idClt = Integer.parseInt(req.getParameter("pIdClt"));
		
		
		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		//appel de la méthode service
		//instancier un étudiant avec les paramtres
		Client cltIn = new Client();
		cltIn.setId(idClt);
		
		Client cltOut = cltService.getClient(cltIn, conseiller);
		CompteEpargne ceIn = new CompteEpargne(id,solde, tdr ,cltOut);
		
		int verif = ceService.updateCompteEpargne(ceIn);
			
			if(verif!=0) {
				//aller dans acceuil et récupérer la nouvelle liste
				//récupérer la liste des etudiant du formateur connecté 
				List<CompteEpargne> listeCE = ceService.getAllCompteEpargne(conseiller);
				
				req.setAttribute("listeCE", listeCE);
				req.setAttribute("compteE", ceIn);
				
				List<Transaction> listeTr = trService.getAllTransactionbyCE(ceIn);
				
				if(listeTr.size()!=0) {
					req.setAttribute("listeTr", listeTr);
				}
				//deleguer la requête à la jsp login.jsp
				//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
				RequestDispatcher rd= req.getRequestDispatcher("homeCE.jsp");
				
				//Délégué la requete 
				rd.forward(req, resp);
			}
			else {
				//La variable message va jouer le rôle du model du design pattern 
				String message="La modification a échoué";
				
				// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
				req.setAttribute("msg", message);
				
				//deleguer la requête à la jsp login.jsp
				//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
				RequestDispatcher rd= req.getRequestDispatcher("homeCE.jsp");
				
				//deleguer contraitement la requête
				rd.forward(req, resp);
			}
		
	}
}
