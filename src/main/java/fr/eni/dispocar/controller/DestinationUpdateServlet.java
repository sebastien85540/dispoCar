package fr.eni.dispocar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.bo.Destination;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.DestinationManager;

/**
 * Servlet implementation class DestinationUpdateServlet
 */
@WebServlet("/private/destination/update")
public class DestinationUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DestinationManager destiMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationUpdateServlet() {
        super();
        destiMgr = new DestinationManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Modification d'une destination";
		request.setAttribute("title", title);
		int id = Integer.parseInt(request.getParameter("idDestination"));
		Destination desti = null;
		try {
			desti = destiMgr.selectById(id);
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue lors de la recuperation d'une destination dans la servlet UpdateDestination", e);
		}
		request.setAttribute("desti", desti);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/destinations/updateDestination.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'ID
		int id = Integer.parseInt(request.getParameter("idDestination"));
		// Récupération des éléments
		String libelle = request.getParameter("libelle");
		int numero = Integer.parseInt(request.getParameter("numero"));
		String rue = request.getParameter("rue");
		String complement = request.getParameter("complement");
		int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		String ville = request.getParameter("ville");
		Boolean agence = Boolean.valueOf(request.getParameter("agence"));
		// les données sont mises dans un constructeur 
		Destination desti = new Destination(libelle, numero, rue, complement, codePostal, ville, agence);
		
		try {
			destiMgr.updateDestination(id, desti);
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la mise à jour de la destination dans la servlet", e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/private/destinations");
		rd.forward(request, response);
		
	}

}
