package fr.eni.dispocar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.bo.Indisponibilite;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.IndisponibiliteManager;

/**
 * Servlet implementation class IndisponibiliteInsertServlet
 */
@WebServlet("/private/indisponibilite/add")
public class IndisponibiliteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndisponibiliteManager indispoMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndisponibiliteInsertServlet() {
        super();
        indispoMgr = new IndisponibiliteManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Insérer une indisponibilité";
		request.setAttribute("title", title);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/indisponibilites/insertIndispo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récuperation des données
		String idIndispo = request.getParameter("idIndispo").toUpperCase();
		String libelle = request.getParameter("libelle").toLowerCase();
		Indisponibilite indispo = new Indisponibilite(idIndispo, libelle);
		
		try {
			indispoMgr.insertIndispo(indispo);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/private/indisponibilites");
		rd.forward(request, response);
	}

}
