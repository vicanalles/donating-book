package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Interesse;

public class InteresseRepository {
	
	private EntityManager manager;

	public InteresseRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Interesse interesse) {
		manager.persist(interesse);
	}
	
	public long getNumeroDoacoesRepassadas() {
		Query query = manager.createQuery("SELECT count(i.id) "
				+ "FROM Interesse i");
		long numeroDoacoes;
		try{
			numeroDoacoes = (Long) query.getSingleResult();
			return numeroDoacoes;
		}catch(NoResultException e){
			e.printStackTrace();
			return 0;
		}
	}
}
