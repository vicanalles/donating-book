package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Usuario;

public class UsuarioRepository {

	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public boolean verifyUserExistence(String email) {
		Query query = manager.createQuery("SELECT email FROM Usuario u WHERE u.email = ?1");
		query.setParameter(1, email);
		try {
			Object object;
			object = query.getSingleResult();
			
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public String login(String email, String senha) {
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email LIKE ?1 AND u.senha = ?2");
		query.setParameter(1, email).setParameter(2, senha);
		try {
			Usuario usuario = null;
			usuario = (Usuario)query.getSingleResult();
			
			return usuario.getTipo();
		} catch (NoResultException e) {
			return "false";
		}
	}
}
