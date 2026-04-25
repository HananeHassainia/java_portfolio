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
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteCourantService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteCourantService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;
import fr.adaming.utils.IntegerOfString;

@WebServlet(name = "operationCC", urlPatterns = "/opRCC")

public class OperationCC extends HttpServlet{

	private ICompteCourantService ccService = new CompteCourantService();
	private ITransactionService tService = new TransactionService();

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("pId"));
		String type = req.getParameter("pTypeOp");
		int somme =Integer.parseInt(req.getParameter("pIdSommeV"));

		CompteCourant ccIn = new CompteCourant();
		ccIn.setId(id);
		
		CompteCourant ccOut = ccService.getCompteCourant(ccIn);
		int verifClt1 = 0;
		CompteCourant ccOutV = null;
		
		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
			
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		
		switch (type) {
		case "depot":
			ccOut.setSolde(ccOut.getSolde()+somme);
			verifClt1 = ccService.updateCompteCourant(ccOut);
			break;

		case "retrait" : 
			ccOut.setSolde(ccOut.getSolde()-somme);
			verifClt1 = ccService.updateCompteCourant(ccOut);
			somme = -somme;
			break;
			
		case "virement" : 
			String cltVstr = req.getParameter("pCompteC");
			int cltV = IntegerOfString.extractInt(cltVstr);
			
			CompteCourant ccInV = new CompteCourant();
			ccInV.setId(cltV);
			
			ccOut.setSolde(ccOut.getSolde()-somme);
			
			verifClt1 = ccService.updateCompteCourant(ccOut);
			
			ccOutV = ccService.getCompteCourant(ccInV);	
			ccOutV.setSolde(ccOutV.getSolde()+somme);

			int verifClt2 = ccService.updateCompteCourant(ccOutV);
			
			if (verifClt2==0) {
				//La variable message va jouer le rôle du model du design pattern 
				String message="Virement impossible";
				
				// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
				req.setAttribute("msg", message);
				
				RequestDispatcher rd= req.getRequestDispatcher("homeCC.jsp");
				
				//Délégué la requete 
				rd.forward(req, resp);
			}
			break;
			
		default:
			//La variable message va jouer le rôle du model du design pattern 
			String message="type Inconnu";
			
			// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
			req.setAttribute("msg", message);
			
			break;
		}
		if(verifClt1 !=0) {
			req.setAttribute("compteC", ccOut);

			if(type.equals("virement")) {
				String type1="virement du compte n°"+String.valueOf(ccOut.getId());
				String type2="virement vers le compte n°"+String.valueOf(ccOutV.getId());

				
				Transaction tr = new Transaction("cc", type1, somme, ccOutV);
				tService.addTransaction(tr);
				
				type = type2;
				somme = -somme;
			}			
			
			Transaction tr = new Transaction("cc", type, somme, ccOut);

			tService.addTransaction(tr);
			
			List<Transaction> listeTr = tService.getAllTransactionbyCC(ccOut);
			if(listeTr.size()!=0) {
				req.setAttribute("listeTr", listeTr);
			}
		}
		else {
			//La variable message va jouer le rôle du model du design pattern 
			String message="Virement impossible";
			
			// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
			req.setAttribute("msg", message);
		}
		
		
		List<CompteCourant> listeCC = ccService.getAllCompteCourant(conseiller);
		
		req.setAttribute("listeCC", listeCC);
		RequestDispatcher rd= req.getRequestDispatcher("homeCC.jsp");
		
		//Délégué la requete 
		rd.forward(req, resp);
	}

}
