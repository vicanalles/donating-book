package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;

public class VoluntarioRepository {
	
	private EntityManager manager;
	
	public VoluntarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Voluntario voluntario) {
		manager.persist(voluntario);
	}
	
	public void desativar(Voluntario voluntario) {
		manager.persist(voluntario);
	}
	
	public void ativar(Voluntario voluntario) {
		manager.persist(voluntario);
	}
	
	/*
	 * Códigos para verificação de status:
	 * 0 - Voluntário desativado, não pode acessar o sistema
	 * 1 - Voluntário ativado, acessa o sistema normalmente
	 */

	public ArrayList<Voluntario> getVoluntarios() {
		
		ArrayList<Voluntario> voluntarios = new ArrayList<Voluntario>();
		Query query = manager.createQuery("SELECT v FROM Voluntario v WHERE status = 1");
		
		try {
			voluntarios = (ArrayList<Voluntario>)query.getResultList();
			return voluntarios;
		}catch(NoResultException e) {
			e.printStackTrace();
			return voluntarios;
		}
	}
	
	public ArrayList<Voluntario> getVoluntariosDesativados(){
		
		ArrayList<Voluntario> voluntarios = new ArrayList<Voluntario>();
		Query query = manager.createQuery("SELECT v FROM Voluntario v WHERE status = 0");
		
		try {
			voluntarios = (ArrayList<Voluntario>)query.getResultList();
			return voluntarios;
		}catch(NoResultException e) {
			e.printStackTrace();
			return voluntarios;
		}
	}

	public int status(int id) {
		
		Query query = manager.createQuery("SELECT v FROM Voluntario v WHERE v.id LIKE ?1");
		query.setParameter(1, id);
		
		try {
			Voluntario voluntario = null;
			voluntario = (Voluntario)query.getSingleResult();
			
			return voluntario.getStatus();
		}catch(NoResultException e) {
			e.printStackTrace();
			return 3;
		}
	}

	public int getVoluntarioByEmail(String emailUser) {
		
		Voluntario voluntario = null;
		Query query = manager.createQuery("SELECT v FROM Voluntario v WHERE v.email = ?1");
		query.setParameter(1, emailUser);
		try {
			voluntario = (Voluntario) query.getSingleResult();
			return voluntario.getId();
		}catch(NoResultException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Voluntario getVoluntarioById(int id) {
		
		Voluntario voluntario = null;
		Query query = manager.createQuery("SELECT v FROM Voluntario v WHERE v.id = ?1");
		query.setParameter(1, id);
		try {
			voluntario = (Voluntario) query.getSingleResult();
			return voluntario;
		}catch(NoResultException e) {
			e.printStackTrace();
			return voluntario;
		}
	}
}
