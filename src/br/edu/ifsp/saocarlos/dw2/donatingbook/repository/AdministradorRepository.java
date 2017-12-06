package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Administrador;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Membro;

public class AdministradorRepository {

	private EntityManager manager;
	
	public AdministradorRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Administrador administrador) {
		manager.persist(administrador);
	}
	
	public void atualizar(Administrador administrador) {
		manager.merge(administrador);
	}
	
	public int getAdministradorByEmail(String emailUser) {
		Administrador administrador = null;
		Query query = manager.createQuery("SELECT a FROM Administrador a WHERE a.email = ?1");
		query.setParameter(1, emailUser);
		try {
			administrador = (Administrador) query.getSingleResult();
			return administrador.getId();
		}catch(NoResultException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Administrador getAdministradorById(int id) {
		Administrador administrador = new Administrador();
		Query query = manager.createQuery("SELECT a FROM Administrador a WHERE a.id = ?0");
		query.setParameter(0, id);
		try {
			administrador = (Administrador) query.getSingleResult();
			return administrador;
		}catch(NoResultException e) {
			e.printStackTrace();
			return administrador;
		}
	}
}
