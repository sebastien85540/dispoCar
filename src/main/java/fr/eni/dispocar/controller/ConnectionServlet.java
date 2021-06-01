package fr.eni.dispocar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.dispocar.bo.Utilisateur;
import fr.eni.dispocar.manager.UtilisateurManager;


@WebServlet("/public/connection")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UtilisateurManager utilManag;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionServlet() {
        super();
        utilManag = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Page de connexion";
		request.setAttribute("title", title);
		
		// COOKIES RECUPERE
		Cookie[] cookies = request.getCookies();
		String nom = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cookieId")) {

					nom = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
					request.setAttribute("nom", nom);
					break;
				}
				
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Récupération des identifiants de connexion
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		System.out.println(email);
//		System.out.println(password);
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilManag.authentification(email, password);
			if (utilisateur != null) {
				
						HttpSession session = request.getSession(true);
						session.setAttribute("utilisateur",utilisateur);
						if (request.getParameter("memoriser") != null) {
						Cookie cookie = new Cookie("cookieId", java.net.URLEncoder.encode(email, "UTF-8"));
						System.out.println("new cookie est " + cookie);
						cookie.setMaxAge(100);
						cookie.setHttpOnly(true);
						cookie.setComment("Votre pseudo");
						response.addCookie(cookie);
						}
						request.getRequestDispatcher("/private/calendrier").forward(request, response);
				} else {
					Exception e = new Exception("L'un des deux champs est érronné");
					request.setAttribute("erreur", e.getMessage());
					throw e;
				}

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
		}
	}

}
