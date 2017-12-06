package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Doacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;

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

	public Doacao getDoacao(int id) {
		Doacao doacao = null;
		Query query = manager.createQuery("SELECT d FROM Doacao d WHERE d.idAnuncio = ?0");
		query.setParameter(0, id);
		try{
			doacao = (Doacao) query.getSingleResult();
			return doacao;
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}

	public void atualizar(Doacao doacao) {
		manager.merge(doacao);
	}
	
	public long getNumeroDoacoesRecebidas() {
		Query query = manager.createQuery("SELECT count(d.id) "
				+ "FROM Doacao d");
		long numeroDoacoes;
		try{
			numeroDoacoes = (Long) query.getSingleResult();
			return numeroDoacoes;
		}catch(NoResultException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<Voluntario> getVoluntariosDoadores(int ongId){
		Query query = manager.createQuery("SELECT v FROM Voluntario v, Doacao d WHERE "
				+ "d.idUsuario = v.id and d.idReceptor = ?1");
		query.setParameter(1, ongId);
		try {
			ArrayList<Voluntario> voluntarios = (ArrayList<Voluntario>) query.getResultList();
			return voluntarios;
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public String nomeAnuncio(int voluntarioId) {
		Query query = manager.createQuery("SELECT a.titulo FROM Anuncio a, Doacao d WHERE "
				+ "d.idAnuncio = a.id and d.idUsuario = ?1");
		query.setParameter(1, voluntarioId);
		try {
			String nome = (String) query.getSingleResult();
			return nome;
		}catch(NoResultException e) {
			return null;
		}
	}
}
