package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;

public class VoluntarioRepository {
	
	private EntityManager manager;
	
	public VoluntarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Voluntario voluntario) {
		manager.persist(voluntario);
	}
}
