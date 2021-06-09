package fr.eni.dispocar.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.eni.dispocar.bo.Reservation;
import fr.eni.dispocar.bo.ReservationFullCalendar;
import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.FullCalendarManager;

/**
 * Servlet implementation class CalendrierServlet
 */
@WebServlet("/private/calendrier")
public class CalendrierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendrierServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TITRE DE LA PAGE
		String title = "Page calendrier";
		request.setAttribute("title", title);

        // Ici normalement, il faut faire appel à  un manager qui fait appel à  la bdd.
        FullCalendarManager mngCalendar = new FullCalendarManager();
        // Liste de reservation pour construire le calendrier
        List<Reservation> reservations = null;
        
            try {
				reservations = mngCalendar.selestAllFullCalendar();
			} catch (ManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // Mais pour l'exemple, je crée en dur la liste de réservations (La classe ReservationFullCalendar est orientée IHM, 
            // il faut donc que le manager retourne une liste de Reservation et que vous créiez un convertisseur "Reservation" vers "ReservationFullCalendar")
            List<ReservationFullCalendar> reservationsFullCalendar = new ArrayList<ReservationFullCalendar>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Reservation reservation : reservations) {
                String info = reservation.getVehiculeReservation().getImmatriculation();
                for (Salarie reservataire : reservation.getReservataires()) {
                    info += " " + reservataire.getPrenom();
                }
                reservationsFullCalendar.add(new ReservationFullCalendar(reservation.getDateDebutReservation().format(dtf), reservation.getDateFinReservation().format(dtf),info,request.getContextPath()+"/private/resa?id="+reservation.getIdReservation()));
            }

            //Je place une version JSON pour le calendrier(utilisation de la librairie gson-2.8.6.jar)
            Gson gson = new Gson();
            request.setAttribute("reservationsJSON", gson.toJson(reservationsFullCalendar));
       
        //Je transfère le tout à  la jsp qui va s'occuper d'affecter ces données au code javascript
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
