package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;

public class AnuncioRepository {

	private EntityManager manager;
	private Anuncio anuncio;
	
	public AnuncioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Anuncio anuncio) {
		manager.persist(anuncio);
	}
	
	public void remover(Anuncio anuncio) {
		manager.remove(anuncio);
	}
	public void atualizar(Anuncio anuncio) {
		manager.merge(anuncio);
	}
	
	public ArrayList<Anuncio> getAnunciosByIdProp(int id){
		
		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
		Query query = manager.createQuery("SELECT a FROM Anuncio a WHERE idprop = ?1");
		query.setParameter(1, id);
		try {
			anuncios = ((ArrayList<Anuncio>) query.getResultList());
			return anuncios;
		}catch(NoResultException e) {
			e.printStackTrace();
			return anuncios;
		}
	}
	
	public Anuncio getAnuncioById(int id) {
		
		Query query = manager.createQuery("SELECT a FROM Anuncio a WHERE a.id = ?1");
		query.setParameter(1, id);
		try {
			anuncio = (Anuncio) query.getSingleResult();
			return anuncio;
		}catch(NoResultException e) {
			e.printStackTrace();
			return anuncio;
		}
	}
	
	public ArrayList<Anuncio> getAnuncios(){
		
		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
		Query query = manager.createQuery("SELECT a FROM Anuncio a");
		try {
			anuncios = ((ArrayList<Anuncio>) query.getResultList());
			return anuncios;
		}catch(NoResultException e) {
			e.printStackTrace();
			return anuncios;
		}
	}
}
