package fr.adaming.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.adaming.entities.Client;
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ClientService;
import fr.adaming.service.ConseillerService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IConseillerService;

@WebServlet(name = "modiferCons", urlPatterns = "/updateCons")
public class ModifierCons extends HttpServlet{
	private IConseillerService cService = new ConseillerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recupérer les parametres de la requete 
		String nom = req.getParameter("pNom");
		String prenom = req.getParameter("pPrenom");
		String adresse = req.getParameter("pAdresse");
		long codePostal = Long.parseLong(req.getParameter("pCp"));
		String ville = req.getParameter("pVille");
		String tel = req.getParameter("pTel");
		String mail = req.getParameter("pMail");

		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		Conseiller cIn = new Conseiller(conseiller.getId(),nom, prenom, adresse, codePostal, ville, tel, mail,conseiller.getMdp());
		System.out.println("conseillé : "+cIn);
		int verif = cService.updateConseiller(cIn);
				
			if(verif!=0) {
				maSession.setAttribute("cSession", cIn);
				//deleguer la requête à la jsp login.jsp
				//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
				RequestDispatcher rd= req.getRequestDispatcher("infosPerso.jsp");
				
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
				RequestDispatcher rd= req.getRequestDispatcher("infosPerso.jsp");
				
				//deleguer contraitement la requête
				rd.forward(req, resp);
			}
		
	}
}
