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
import fr.adaming.service.IClientService;

@WebServlet(name = "modiferClt", urlPatterns = "/updateClt")
public class ModifierCltServlet extends HttpServlet{
	private IClientService cltService = new ClientService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		Client clt2 = cltService.getClient(clt1, conseiller);
					
		//ajouter l'étudiant dans la requête comme attribut de la requ req
		req.setAttribute("eModif", clt2);
		
		//deleguer la requête à la jsp login.jsp
		//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
		RequestDispatcher rd= req.getRequestDispatcher("modif.jsp");
		
		//deleguer contraitement la requête
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recupérer les parametres de la requete 
		int id = Integer.parseInt(req.getParameter("pId"));
		System.out.println("id= "+ id);
		String nom = req.getParameter("pNom");
		System.out.println("Nom= "+nom );
		String prenom = req.getParameter("pPrenom");
		System.out.println("prenom= "+ prenom);
		Date birth = Date.valueOf(req.getParameter("pDate"));
		System.out.println("date= "+ birth);
		String adresse = req.getParameter("pAdresse");
		System.out.println("adresse= "+ adresse);
		long codePostal = Long.parseLong(req.getParameter("pCp"));
		System.out.println("cp =  "+ codePostal);
		String ville = req.getParameter("pVille");
		System.out.println("ville= "+ville);
		String tel = req.getParameter("pTel");
		System.out.println("tel= "+ tel);
		String mail = req.getParameter("pMail");
		System.out.println("mail= "+ mail);

		
		//instancier un étudiant avec les paramtres
		Client clt1 = new Client(id, nom, prenom, adresse, codePostal, ville, tel, mail, birth);
		
		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		
		//appel de la méthode service
		
		int verif = cltService.updateClient(clt1, conseiller);
			
			if(verif!=0) {
				//aller dans acceuil et récupérer la nouvelle liste
				//récupérer la liste des etudiant du formateur connecté 
				List<Client> listeClt = cltService.getAllClient(conseiller);
				
				Client clt2 = cltService.getClient(clt1, conseiller);
				System.out.println("clt2 : "+clt2);
				if(clt2!=null) {
					req.setAttribute("listeClt", listeClt);
					req.setAttribute("client", clt2);
				}
				
				//deleguer la requête à la jsp login.jsp
				//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
				RequestDispatcher rd= req.getRequestDispatcher("homeCons.jsp");
				
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
				RequestDispatcher rd= req.getRequestDispatcher("updateClt.jsp");
				
				//deleguer contraitement la requête
				rd.forward(req, resp);
			}
		
	}
}
