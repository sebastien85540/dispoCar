package fr.eni.dispocar.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.bo.ReservationFullCalendar;
import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.FullCalendarManager;

/**
 * Servlet implementation class ResaCalendarServlet
 */
@WebServlet("/private/resa")
public class ResaCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FullCalendarManager fullMgn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResaCalendarServlet() {
        super();
        fullMgn = new FullCalendarManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resa = request.getParameter("id");
		String title = "Reservation N°: " + resa;
		System.out.println(title);
		request.setAttribute("title", title);
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		try {
			Reservation reservation = fullMgn.selectById(id);
			ReservationFullCalendar resaFullCalendar = null;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String info = reservation.getVehiculeReservation().getImmatriculation();
            for (Salarie reservataire : reservation.getReservataires()) {
                info += " " + reservataire.getPrenom();
            }
            resaFullCalendar = new ReservationFullCalendar(reservation.getDateDebutReservation().format(dtf), reservation.getDateFinReservation().format(dtf),info,request.getContextPath()+"/private/resa?id="+reservation.getIdReservation());
            request.setAttribute("resaCalendar", resaFullCalendar);
		} catch (ManagerException e) {
			throw new ServletException("Un soucis dans la servlet fullManager", e);
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/calendrier/resa.jsp");
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
