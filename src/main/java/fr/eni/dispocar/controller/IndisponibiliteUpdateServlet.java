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
 * Servlet implementation class IndisponibiliteUpdateServlet
 */
@WebServlet("/private/indisponibilite/update")
public class IndisponibiliteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndisponibiliteManager indispoMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndisponibiliteUpdateServlet() {
        super();
        indispoMgr = new IndisponibiliteManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Modification de l'indisponibilité";
		request.setAttribute("title", title);
		String idIndispo = request.getParameter("idIndispoUpdate");
		Indisponibilite indis;
		try {
			indis = indispoMgr.selectById(idIndispo);
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet Indisponibilite update", e);
		}
		request.setAttribute("idIndispo", indis);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/indisponibilites/updateIndispo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupération de l'id
		String id = request.getParameter("idIndispoUpdate");
		String idIndispo = request.getParameter("idIndispo");
		String libelle = request.getParameter("libelle");
		Indisponibilite indispo = new Indisponibilite(idIndispo, libelle);
		try {
			indispoMgr.indispoUpdate(id, indispo);
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet du updateIndisponibilité", e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/private/indisponibilites");
		rd.forward(request, response);
	}

}
