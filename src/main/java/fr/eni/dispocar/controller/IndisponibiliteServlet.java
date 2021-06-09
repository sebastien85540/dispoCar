package fr.eni.dispocar.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class IndisponibiliteServlet
 */
@WebServlet("/private/indisponibilites")
public class IndisponibiliteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndisponibiliteManager indispoMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndisponibiliteServlet() {
        super();
        indispoMgr = new IndisponibiliteManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Page des indisponibilités";
		request.setAttribute("title", title);
		List<Indisponibilite> indisponibilites;
		
		// Récupération des indisponibilités dans la base de données
		try {
			indisponibilites = indispoMgr.selectAllIndispo();
			request.setAttribute("indisponibilites", indisponibilites);
			
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet Indisponibilites");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/indisponibilites/indispo.jsp");
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
