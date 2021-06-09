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

import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.VehiculeManager;

/**
 * Servlet implementation class VehiculesServlet
 */
@WebServlet("/private/vehicules")
public class VehiculesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehiculeManager vehiMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehiculesServlet() {
        super();
        vehiMgr = new VehiculeManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Liste des vehicules";
		request.setAttribute("title", title);
		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		
		try {
			vehicules = vehiMgr.selectAllVehicules();
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet vehicules");
		} 
		request.setAttribute("vehicules", vehicules);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/vehicules/vehicules.jsp");
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
