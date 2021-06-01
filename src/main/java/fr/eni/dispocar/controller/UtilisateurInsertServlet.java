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
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.UtilisateurManager;

/**
 * Servlet implementation class UtilisateurInsertServlet
 */
@WebServlet("/private/utilisateur/add")
public class UtilisateurInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurInsertServlet() {
        super();
        utilMgr = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TITRE DE LA PAGE 
		String title = "Page d'inscription";
		request.setAttribute("title", title);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/insertUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Boolean administrateur = Boolean.valueOf(request.getParameter("administrateur"));
		Utilisateur u = null;
		String message = null;
		
		Utilisateur utilisateur = new Utilisateur(email, password, administrateur);
		try {
			u =utilMgr.ajouterUtilisateur(utilisateur);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (u != null) {
			message = "Un utilisateur a bien été créé";
			
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("/private/utilisateurs");
		rd.forward(request, response);
	}

}
