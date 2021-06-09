package fr.eni.dispocar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.IndisponibiliteManager;

/**
 * Servlet implementation class IndisponibiliteDeleteServlet
 */
@WebServlet("/private/indisponibilite/delete")
public class IndisponibiliteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndisponibiliteManager indispoMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndisponibiliteDeleteServlet() {
        super();
        indispoMgr = new IndisponibiliteManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idIndispoDelete");
		try {
			indispoMgr.indispoDelete(id);
		} catch (ManagerException e) {
			throw new ServletException("Une errreur est survenue dans la servlet de suppression d'une indisponibilité",e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/private/indisponibilites");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
