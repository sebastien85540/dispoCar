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

import com.google.gson.Gson;

import fr.eni.dispocar.bo.ReservationFullCalendar;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.FullCalendarManager;

/**
 * Servlet implementation class CalendrierServlet
 */
@WebServlet("/private/calendrier")
public class CalendrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FullCalendarManager mngCalendar;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendrierServlet() {
        super();
        mngCalendar = new FullCalendarManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TITRE DE LA PAGE
		String title = "Page calendrier";
		request.setAttribute("title", title);
		String message = null;
		
		//Récupérer les datas à afficher dans le calendrier
		//Ici normalement il faut faire appel à un manager qui fait appel à la bdd
		//Mais pour l'exemple, je crée en dur la liste de réservations (La classe ReservationFullCalendar est orientée IHM, 
		//il faut donc que le manager retourne une liste de Reservation et que vous créiez un convertisseur "Reservation" vers "ReservationFullCalendar")
		List<ReservationFullCalendar> reservations = new ArrayList<ReservationFullCalendar>();
		
		try {
			reservations = mngCalendar.selestAllFullCalendar();
			if (reservations == null) {
				message = "Aucune réservations";
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		
		//reservations.add(new ReservationFullCalendar("2021-06-25T17:30:00","2021-05-27T09:00:00","AA123ZZ par Fred,Yann",request.getContextPath()+"/resa?id="+15));
//		reservations.add(new ReservationFullCalendar("2021-05-25T17:30:00","2021-05-27T09:00:00","AS456TY  par Bruno,Thierry",request.getContextPath()+"/resa?id="+23));
//		reservations.add(new ReservationFullCalendar("2021-05-24T09:00:00","2021-05-24T11:00:00","FD448GG par Vincent",request.getContextPath()+"/resa?id="+42));
		//Je place une version JSON pour le calendrier(utilisation de la librairie gson-2.8.6.jar)
		Gson gson = new Gson();
		request.setAttribute("reservationsJSON", gson.toJson(reservations));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/calendrier.jsp");
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
