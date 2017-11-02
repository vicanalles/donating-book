package br.edu.ifsp.saocarlos.dw2.donatingbook.config;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = {"Faces Servlet"})
public class InterceptorConfig implements Filter {

	private EntityManagerFactory factory;
	
	public void destroy() {
		factory.close();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		request.setAttribute("EntityManager", manager);
		
		chain.doFilter(request, response);
		
		try {
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}finally {
			manager.close();
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		factory = Persistence.createEntityManagerFactory("donating-book");
	}
}
