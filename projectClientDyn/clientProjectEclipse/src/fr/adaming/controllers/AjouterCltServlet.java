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
import fr.adaming.service.CompteCourantService;
import fr.adaming.service.IClientService;
import fr.adaming.utils.PasswordUtil;


@WebServlet(name = "ajouterClt", urlPatterns = "/addClt")
public class AjouterCltServlet extends HttpServlet{

	//Transformation de l'association UML en JAVA 
	private IClientService cltService = new ClientService();
		
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Recupérer les parametres de la requete 
		String nom = req.getParameter("pNom");
		String prenom = req.getParameter("pPrenom");
		Date birth = Date.valueOf(req.getParameter("pDate"));
		String adresse = req.getParameter("pAdresse");
		long codePostal = Long.parseLong(req.getParameter("pCp"));
		String ville = req.getParameter("pVille");
		String tel = req.getParameter("pTel");
		String mail = req.getParameter("pMail");
		String mdp = PasswordUtil.generateRandomPassword(5);
		
		System.out.println("mdp = "+ mdp);
		//instancier un étudiant avec les paramtres
		Client clt1 = new Client(nom, prenom, adresse, codePostal, ville, tel, mail, mdp, birth);
		
		System.out.println("client 1 = "+clt1);
		//Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession=req.getSession(false);
		
		//Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");
		System.out.println("conseiller : "+conseiller);
		//appel de la méthode service
		
		int verif = cltService.addClient(clt1, conseiller);
		
		
		if(verif!=0) {
			//aller dans acceuil et récupérer la nouvelle liste
			//récupérer la liste des etudiant du formateur connecté 
			List<Client> listeClt = cltService.getAllClient(conseiller);
			clt1.setId(new CompteCourantService().getIdOfLastClient());
			
			req.setAttribute("listeClt", listeClt);
			req.setAttribute("client", clt1);

			
			//deleguer la requête à la jsp login.jsp
			//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
			RequestDispatcher rd= req.getRequestDispatcher("homeCons.jsp");
			
			//Délégué la requete 
			rd.forward(req, resp);
		}
		else {
			//La variable message va jouer le rôle du model du design pattern 
			String message="L'ajout a échoué";
			
			// ajouter message comme attribut à la req qui va être délégué à la jsp : transmission à la jsp login
			req.setAttribute("msg", message);
			
			//deleguer la requête à la jsp login.jsp
			//Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la vue du design pattern mcv)
			RequestDispatcher rd= req.getRequestDispatcher("addClient.jsp");
			
			//deleguer contraitement la requête
			rd.forward(req, resp);
		}
		
	}
}
