package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;

public class AnuncioRepository {

	private EntityManager manager;
	
	public AnuncioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Anuncio anuncio) {
		manager.persist(anuncio);
	}
	
	public void remover(Anuncio anuncio) {
		manager.remove(anuncio);
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
}
