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
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteCourantService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteCourantService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;


@WebServlet(name = "chercherCC", urlPatterns = "/getCC")
public class ChercherCC extends HttpServlet{
	private IClientService cltService = new ClientService();
	private ICompteCourantService ccService = new CompteCourantService();
	private ITransactionService tService = new TransactionService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recupérer les parametres de la requete 
		int id = Integer.parseInt(req.getParameter("pId"));
		String idCltCheck = req.getParameter("pIdcltCheck");
		
		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		List<CompteCourant> listeCC = ccService.getAllCompteCourant(conseiller);
		
		if(idCltCheck == null) {
			CompteCourant ccIn = new CompteCourant();
			ccIn.setId(id);
			CompteCourant verif = ccService.getCompteCourant(ccIn);
			if(verif!=null) {
				req.setAttribute("compteC", verif);
				List<Transaction> listeTr = tService.getAllTransactionbyCC(verif);
				if(listeTr.size()!=0) {
					req.setAttribute("listeTr", listeTr);
				}
			}
			else {
				//La variable message va jouer le rôle du model du design pattern 
				String message="Erreur aucun compte courant est enregistré sous cet id";
				
				// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
				req.setAttribute("msg", message);
				
				req.setAttribute("compteC", listeCC.get(0));

			}
		}
		
		else {
			Client cltIn = new Client();
			cltIn.setId(id);
			Client cltOut = cltService.getClient(cltIn, conseiller);
			
			if(cltOut != null) {
				CompteCourant verif = ccService.getCompteCourant(cltOut.getCc());
				if(verif != null) {
					req.setAttribute("compteC", verif);
					List<Transaction> listeTr = tService.getAllTransactionbyCC(verif);
					if(listeTr.size()!=0) {
						req.setAttribute("listeTr", listeTr);
					}
				}
				else {
					//La variable message va jouer le rôle du model du design pattern 
					String message="Erreur ce client ne possède pas de compte courant";
					
					// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
					req.setAttribute("msg", message);
					
					req.setAttribute("compteC", verif);

				}
			}
			else {
				//La variable message va jouer le rôle du model du design pattern 
				String message="Erreur ce client n'existe pas";
				
				req.setAttribute("compteC", listeCC.get(0));
				
				// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
				req.setAttribute("msg", message);
			}
		}
				
		req.setAttribute("listeCC", listeCC);
		RequestDispatcher rd= req.getRequestDispatcher("homeCC.jsp");
		
		//Délégué la requete 
		rd.forward(req, resp);
	
	}
}
