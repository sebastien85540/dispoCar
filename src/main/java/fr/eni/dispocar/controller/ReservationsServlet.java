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

import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.manager.ReservationManager;

/**
 * Servlet implementation class ReservationsServlet
 */
@WebServlet("/private/reservations")
public class ReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationManager resamgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationsServlet() {
        super();
        resamgr = new ReservationManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Page des reservations";
		request.setAttribute("title", title);
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		reservations = resamgr.selectAllReservations();
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reservations/reservations.jsp");
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
