package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Livro;

public class LivroRepository {

	private EntityManager manager;
	
	public LivroRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Livro livro) {
		manager.persist(livro);
	}
}
