package fr.eni.dispocar.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
import fr.eni.dispocar.exception.ToolsException;
import fr.eni.dispocar.manager.UtilisateurManager;


/**
 * Servlet implementation class UtilisateurUpdateServlet
 */
@WebServlet("/private/utilisateur/update")
public class UtilisateurUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager util;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurUpdateServlet() {
        super();
        util = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Page de modification d'utilisateur";
		request.setAttribute("title", title);
		
		String id = request.getParameter("idUtilisateurUpdate");
		int idUtilisateur = Integer.parseInt(id);
		System.out.println(idUtilisateur);
		try {
			Utilisateur u = util.selectUtilisateurById(idUtilisateur);
			request.setAttribute("utilUpdate", u);
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		

		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/updateUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateurUpdate"));
		Utilisateur updateUtil = null;
		try {
			updateUtil = util.selectUtilisateurById(idUtilisateur);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Boolean administrateur = Boolean.valueOf(request.getParameter("administrateur"));
		
		Utilisateur u = new Utilisateur(updateUtil.getIdUtilisateur(), email, password, administrateur);
		System.out.println(u.getIdUtilisateur());
		try {
			util.updateUtilisateur(u);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ToolsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
	RequestDispatcher rd = request.getRequestDispatcher("/private/utilisateurs");
	rd.forward(request, response);
	}

}
