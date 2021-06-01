/**
 * 
 */
package fr.eni.dispocar.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.dispocar.bo.Utilisateur;

/**
 * @author sebastien
 *
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST,
		DispatcherType.FORWARD,
		DispatcherType.INCLUDE
		}, urlPatterns = { "/private/*"
})
public class SecurityFilter implements Filter{
	

	/**
	 * 
	 */
	public SecurityFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		System.out.println("Filter");
		//vérifier l'existance de l'utilisateur en session
		HttpSession session = httpRequest.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		
		if(u == null) {
			//redirection vers la page de connexion
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/public/connection");
		}
		else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

}
