package fr.eni.dispocar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.DestinationManager;

/**
 * Servlet implementation class DestinationDeleteServlet
 */
@WebServlet("/private/destination/delete")
public class DestinationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DestinationManager destiMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationDeleteServlet() {
        super();
        destiMgr = new DestinationManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idDestination = Integer.parseInt(request.getParameter("idDestination"));
		try {
			destiMgr.deleteDestination(idDestination);
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet DeleteDestination", e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/private/destinations");
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
