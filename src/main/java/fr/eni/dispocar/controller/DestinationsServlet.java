package fr.eni.dispocar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class DestinationsServlet
 */
@WebServlet("/private/destinations")
public class DestinationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DestinationManager destiMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationsServlet() {
        super();
        destiMgr = new DestinationManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Page des destinations";
		request.setAttribute("title", title);
		
		List<Destination> destinations = new ArrayList<Destination>();
		try {
			destinations = destiMgr.selectAllDestination();
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet du selectAllDestinations", e);
		}

		request.setAttribute("destinations", destinations);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/destinations/destinations.jsp");
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
