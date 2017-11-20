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
	
	public int loginId(String email, String senha) {
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email LIKE ?1 AND u.senha = ?2");
		query.setParameter(1, email).setParameter(2, senha);
		try {
			Usuario usuario = null;
			usuario = (Usuario)query.getSingleResult();
			
			return usuario.getId();
		}catch(NoResultException e) {
			return 0;
		}
	}
}
