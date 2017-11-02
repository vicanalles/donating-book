package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;

public class OrganizacaoRepository {
	
	private EntityManager manager;
	
	public OrganizacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Organizacao organizacao) {
		manager.persist(organizacao);
	}
}
