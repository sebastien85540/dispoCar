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

import fr.eni.dispocar.bo.Salarie;
import fr.eni.dispocar.exception.ManagerException;
import fr.eni.dispocar.manager.SalarieManager;

/**
 * Servlet implementation class SalariesServlet
 */
@WebServlet("/private/salaries")
public class SalariesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalarieManager salMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalariesServlet() {
        super();
        salMgr = new SalarieManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "Liste des salariés";
		request.setAttribute("title", title);
		List<Salarie> salaries = new ArrayList<Salarie>();
		try {
			salaries = salMgr.selectAllSalaries();
		} catch (ManagerException e) {
			throw new ServletException("Une erreur est survenue lors de la recuperation des salariés dans la servlet", e);
		}
		request.setAttribute("salaries", salaries);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/salaries/salaries.jsp");
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
