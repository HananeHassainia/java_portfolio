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
import fr.adaming.entities.CompteEpargne;
import fr.adaming.entities.Conseiller;
import fr.adaming.service.ConseillerService;
import fr.adaming.service.IConseillerService;

@WebServlet(name = "modifMdpCons", urlPatterns = "/updateMdpCons")
public class ModifierMdpCons extends HttpServlet {
	
	IConseillerService cService = new ConseillerService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Recupérer les parametres de la requete
		String nvxMotDePasse = req.getParameter("pMdpNew");

		// Récupérer la session http si elle existe sinon il retourne null
		HttpSession maSession = req.getSession(false);

		// Récupérer le formateur de la session
		Conseiller conseiller = (Conseiller) maSession.getAttribute("cSession");

		conseiller.setMdp(nvxMotDePasse);
		
		int verif = cService.updateConseiller(conseiller);

		if (verif != 0) {
			// deleguer la requête à la jsp login.jsp
			// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
			// vue du design pattern mcv)
			RequestDispatcher rd = req.getRequestDispatcher("loginCons.jsp");

			// Délégué la requete
			rd.forward(req, resp);
		} else {
			// La variable message va jouer le rôle du model du design pattern
			String message = "La modification a échoué";

			// ajouter message comme attribut à la req qui va être délégué à la jsp :
			// transmission à la jsp login
			req.setAttribute("msg", message);

			// deleguer la requête à la jsp login.jsp
			// Récupérer le support de délégation(la jsp login.jsp va jouer le rôle de la
			// vue du design pattern mcv)
			RequestDispatcher rd = req.getRequestDispatcher("mdpCons.jsp");

			// deleguer contraitement la requête
			rd.forward(req, resp);
		}
	}

}
