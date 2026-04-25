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

import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.entities.Transaction;
import fr.adaming.service.ClientService;
import fr.adaming.service.CompteEpargneService;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICompteEpargneService;
import fr.adaming.service.ITransactionService;
import fr.adaming.service.TransactionService;

@WebServlet(name = "TousCE", urlPatterns = "/homeCE")
public class TousLesCE extends HttpServlet{

		//Transformation de l'association UML en JAVA 
		private ICompteEpargneService ceService = new CompteEpargneService();
		private ITransactionService trService = new TransactionService();

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Récupérer la session http si elle existe sinon il retourne null
			HttpSession maSession=req.getSession(false);
			
			//Récupérer le formateur de la session
			Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
			
			List<CompteEpargne> listeCE = ceService.getAllCompteEpargne(conseiller);
			
			if(listeCE!=null) {
				//aller dans acceuil et récupérer la nouvelle liste
				//récupérer la liste des etudiant du formateur connecté 
				if(listeCE.size()!=0) {
					System.out.println("okkk taille !=0");
					req.setAttribute("listeCE", listeCE);
					req.setAttribute("compteE", listeCE.get(0));
					
					List<Transaction> listeTr = trService.getAllTransactionbyCE(listeCE.get(0));
					
					if(listeTr.size()!=0) {
						req.setAttribute("listeTr", listeTr);
					}
					//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
					RequestDispatcher rd= req.getRequestDispatcher("homeCE.jsp");
					
					//Délégué la requete 
					rd.forward(req, resp);
				}
				
				else {
					//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
					RequestDispatcher rd= req.getRequestDispatcher("addCE");
					
					//Délégué la requete 
					rd.forward(req, resp);	
				}

			}
			
			else {	
				//deleguer la requête à la jsp login.jsp
				//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
				RequestDispatcher rd= req.getRequestDispatcher("addCE");
				
				//deleguer contraitement la requête
				rd.forward(req, resp);
			}
			
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
