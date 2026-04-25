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
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ClientService;
import fr.adaming.service.ConseillerService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IConseillerService;

@WebServlet(name = "connexionC", urlPatterns = "/loginC")
public class LoginCServlet extends HttpServlet {

	//Declaration et instanciation d'un attribut
	private IConseillerService cService = new ConseillerService();
	private IClientService cltService = new ClientService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Récupérer la session http à partir de l'objet req si elle existe 
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		//récupérer la liste des etudiant du formateur connecté 
		ArrayList<Client> listeClt = cltService.getAllClient(conseiller);

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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("cokay doPost");
		
	// 	Récupérer les valeurs des paramètres 
		String mail = req.getParameter("pMail");
		String mdp = req.getParameter("pMdp");
		
	//	Instancier formateur avec ces valeurs 
		Conseiller c = new Conseiller(mail, mdp);

		//	Appel de la méthode dans service
		Conseiller cOut = cService.inspect(c);
		System.out.println("cOut ="+cOut);
	
		if(cOut != null) {
			//Récupérer la session http à partir de l'objet req si elle existe 
			HttpSession maSession = req.getSession();
			
			//Ajouter le formateur ajouter comme attribut de la session
			maSession.setAttribute("cSession",cOut);
			
			//récupérer la liste des etudiant du formateur connecté 
			ArrayList<Client> listeClt = cltService.getAllClient(cOut);
			
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
			String message="L'Identifiant ou mot de passe érroné(s)";
			
			// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
			req.setAttribute("msg", message);
			
			
			//deleguer la requête à la jsp login.jsp
			//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
			RequestDispatcher rd= req.getRequestDispatcher("loginCons.jsp");
			
			//deleguer contraitement la requête
			rd.forward(req, resp);
		}
	}
}
