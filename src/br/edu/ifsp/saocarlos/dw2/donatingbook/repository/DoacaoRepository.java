package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Doacao;

public class DoacaoRepository{

	private EntityManager manager;
	
	public DoacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Doacao doacao) {
		manager.persist(doacao);
	}
	
}
