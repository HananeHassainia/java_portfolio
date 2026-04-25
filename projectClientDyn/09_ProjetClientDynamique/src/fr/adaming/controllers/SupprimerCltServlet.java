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
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ClientService;
import fr.adaming.service.IClientService;

@WebServlet(name = "supprimer", urlPatterns = "/deleteClt")
public class SupprimerCltServlet extends HttpServlet {

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
		
		//Récupérer le Client de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		//appel de la méthode service
		
		int verif = cltService.deleteClt(clt1, conseiller);
		
		if(verif!=0) {
			//aller dans acceuil et récupérer la nouvelle liste
			//récupérer la liste des etudiant du formateur connecté 
			List<Client> listeClt = cltService.getAllClient(conseiller);
			
			if(listeClt.size()!=0) {
				req.setAttribute("listeClt", listeClt);
				req.setAttribute("client", listeClt.get(0));
			}
			
			//deleguer la requête à la jsp login.jsp
			//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
			RequestDispatcher rd= req.getRequestDispatcher("homeCons.jsp");
			//Délégué la requete 
			rd.forward(req, resp);
		}
		else {
			//La variable message va jouer le rôle du model du design pattern 
			String message="La suppression a échoué";
			
			// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
			req.setAttribute("msg", message);
			
			//deleguer la requête à la jsp login.jsp
			//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
			RequestDispatcher rd= req.getRequestDispatcher("homeCons.jsp");
			
			//deleguer contraitement la requête
			rd.forward(req, resp);
		}
	
	}
}
