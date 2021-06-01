package fr.eni.dispocar.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.UtilisateurManager;



/**
 * Servlet implementation class UtilisateurDeleteServlet
 */
@WebServlet("/private/utilisateur/delete")
public class UtilisateurDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurDeleteServlet() {
        super();
        utilMgr = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idUtilisateur");
		int idUtilisateur = Integer.parseInt(id);
		String message = null;
		try {
			utilMgr.deleteUtilisateur(idUtilisateur);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "Le compte a bien été supprimé";
		request.setAttribute("message", message);
		
		RequestDispatcher rd = request.getRequestDispatcher("/private/utilisateurs");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
