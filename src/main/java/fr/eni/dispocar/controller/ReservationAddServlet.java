package fr.eni.dispocar.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.bo.Agence;
import fr.eni.dispocar.bo.Destination;
import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.bo.Vehicule;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.AgenceManager;
import fr.eni.dispocar.manager.DestinationManager;
import fr.eni.dispocar.manager.SalarieManager;
import fr.eni.dispocar.manager.VehiculeManager;

/**
 * Servlet implementation class ReservationAddServlet
 */
@WebServlet("/private/reservation/add")
public class ReservationAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AgenceManager agenceMgr;
	private DestinationManager destiMgr;
	private SalarieManager salMgr;
	private VehiculeManager vehiMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationAddServlet() {
        super();
        agenceMgr = new AgenceManager();
        destiMgr = new DestinationManager();
        salMgr = new SalarieManager();
        vehiMgr = new VehiculeManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Ajout d'une rérevation";
		request.setAttribute("title", title);
		List<Agence> agences = new ArrayList<Agence>();
		List<Destination> destinations = new ArrayList<Destination>();
		List<Salarie> salaries = new ArrayList<Salarie>();
		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		try {
			agences = agenceMgr.selectAllAgences();
			destinations = destiMgr.selectAllDestination();
			salaries = salMgr.selectAllSalaries();
			vehicules = vehiMgr.selectAllVehicules();
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue dans la servlet reservationAdd", e);
		}
		request.setAttribute("agences", agences);
		request.setAttribute("destinations", destinations);
		request.setAttribute("salaries", salaries);
		request.setAttribute("vehicules", vehicules);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reservations/addReservation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				// Récupération des éléments
				LocalDateTime debut = LocalDateTime.parse(request.getParameter("debut"));
				LocalDateTime fin = LocalDateTime.parse(request.getParameter("fin"));
				String motif = request.getParameter("motif");
				String description = request.getParameter("description");
				int idAgence = Integer.parseInt(request.getParameter("depart"));
				Agence agence = agenceMgr.selectById(idAgence);
				int idDestination = Integer.parseInt(request.getParameter("destination"));
				Destination destination = destiMgr.selectById(idDestination);
				List<Salarie> salaries = new ArrayList<Salarie>();
				//int  = Integer.parseInt(request.getParameter("reservataires"));
				int idSalarie = Integer.parseInt(request.getParameter("reservataire1"));
				
				int idVehicule = Integer.parseInt(request.getParameter("vehicule"));
				Vehicule vehi = vehiMgr.selectById(idVehicule);
				Reservation resa;

				resa = new Reservation(debut, fin, motif, description, agence, destination, salaries, vehi);
			} catch (NumberFormatException | ManagerException e) {
				throw new ServletException("Une erreur est survenue dans la servlet addReservation", e);
			}
		
	}

}
