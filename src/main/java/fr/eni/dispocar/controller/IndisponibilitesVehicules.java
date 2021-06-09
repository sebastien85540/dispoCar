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

import fr.eni.dispocar.bo.IndisponibiliteVehicule;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.IndisponibiliteVehiculeManager;

/**
 * Servlet implementation class IndisponibilitesVehicules
 */
@WebServlet("/private/vehicules/indisponibles")
public class IndisponibilitesVehicules extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndisponibiliteVehiculeManager indispoVehi;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndisponibilitesVehicules() {
        super();
        indispoVehi = new IndisponibiliteVehiculeManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Liste des vehicules indisponibles";
		request.setAttribute("title", title);
		
		List<IndisponibiliteVehicule> indispoVehicules = new ArrayList<IndisponibiliteVehicule>();
		try {
			indispoVehicules = indispoVehi.selectAllVehicules();
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet selectAllVehiculesIndispo", e);
		}
		request.setAttribute("indispos", indispoVehicules);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/vehiculesIndispo/indispovehicules.jsp");
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
