package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;

public abstract class Controller {
	
	public Boolean verificaEmail(String email) {
		EntityManager manager = getEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		
		return usuarioRepository.verificaEmail(email);
	}
	
	protected EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext externalContext = fc.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}
}