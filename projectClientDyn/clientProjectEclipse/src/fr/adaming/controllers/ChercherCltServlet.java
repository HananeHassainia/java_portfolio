package fr.adaming.controllers;

import java.io.IOException;
import java.util.ArrayList;

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
import fr.adaming.service.IClientService;

@WebServlet(name = "chercherClt", urlPatterns = "/getClt")
public class ChercherCltServlet extends HttpServlet{
	
	private IClientService cltService = new ClientService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recupérer les parametres de la requete 
		int id = Integer.parseInt(req.getParameter("pId"));
	
		//instancier un étudiant avec les paramtres
		Client clt1 = new Client();
		clt1.setId(id);
		
		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		//appel de la méthode service
		ArrayList<Client> listeClt = cltService.getAllClient(conseiller);
		req.setAttribute("listeClt", listeClt);
		
		Client clt2 = cltService.getClient(clt1, conseiller);
		System.out.println("étudiant : "+clt2);
		if(clt2!=null) {
			//aller dans acceuil et récupérer la nouvelle liste
			//récupérer la liste des etudiant du formateur connecté 
			
			req.setAttribute("client", clt2);
		}
		else {
			//La variable message va jouer le rôle du model du design pattern 
			String message="Erreur ce client n'existe pas";
			
			// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
			req.setAttribute("msg", message);
		}
		
		//deleguer la requête à la jsp login.jsp
		//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
		RequestDispatcher rd= req.getRequestDispatcher("homeCons.jsp");
		
		//Délégué la requete 
		rd.forward(req, resp);
	
	}
	
}
