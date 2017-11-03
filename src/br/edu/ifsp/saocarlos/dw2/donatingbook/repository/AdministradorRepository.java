package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Administrador;

public class AdministradorRepository {

	private EntityManager manager;
	
	public AdministradorRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Administrador administrador) {
		manager.persist(administrador);
	}
}
