package fr.eni.dispocar.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.dispocar.bo.Utilisateur;
import fr.eni.dispocar.exception.ConnectionProviderException;
import fr.eni.dispocar.exception.DALException;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.UtilisateurManager;


/**
 * Servlet implementation class UtilisateursServlet
 */
@WebServlet("/private/utilisateurs")
public class UtilisateursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateursServlet() {
        super();
        utilMgr = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TITRE DE LA PAGE
		String title = "Page d'utilisateurs";
		request.setAttribute("title", title);
		// LISTE DES UTILISATEURS
		List<Utilisateur> util= new ArrayList<Utilisateur>();
				try {
					util = utilMgr.selectAllUtilisateur();
				} catch (ManagerException | ConnectionProviderException | SQLException | DALException e) {
					e.printStackTrace();
				}

			request.setAttribute("utilisateurs", util);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/utilisateurs.jsp");
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
