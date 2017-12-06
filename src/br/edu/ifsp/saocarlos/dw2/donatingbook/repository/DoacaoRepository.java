package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Doacao;

public class DoacaoRepository{

private EntityManager manager;
	
	public DoacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Doacao doacao) {
		manager.persist(doacao);
	}

	public long getNumeroDoacoesVoluntario(int voluntarioId) {
		Query query = manager.createQuery("SELECT count(d.id) "
				+ "FROM Doacao d, Voluntario v where d.idUsuario = ?0");
		query.setParameter(0, voluntarioId);
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
