package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;

public class AnuncioRepository {

	private EntityManager manager;
	
	public AnuncioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Anuncio anuncio) {
		manager.persist(anuncio);
	}
}
