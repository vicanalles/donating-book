package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Membro;

public class MembroRepository {

	private EntityManager manager;
	
	public MembroRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Membro membro) {
		manager.persist(membro);
	}
}
