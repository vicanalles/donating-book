package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Membro;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;

public class MembroRepository {

	private EntityManager manager;
	
	public MembroRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Membro membro) {
		manager.persist(membro);
	}
	
	public void desativar(Membro membro) {
		manager.persist(membro);
	}
	
	public void ativar(Membro membro) {
		manager.persist(membro);
	}
	
	public void atualizar(Membro membro) {
		manager.merge(membro);
	}
	
	public ArrayList<Membro> getMembrosByOngId(int organizacaoId) {

		ArrayList<Membro> membros = new ArrayList<Membro>();
		Query query = manager.createQuery("SELECT m FROM Membro m WHERE ongid = ?1 and status = 1");
		query.setParameter(1, organizacaoId);
		try {
			membros = (ArrayList<Membro>) query.getResultList();
			return membros;
		}catch(NoResultException e) {
			e.printStackTrace();
			return membros;
		}
	}

	public ArrayList<Membro> getMembrosDesativadosOng(int organizacaoId) {

		ArrayList<Membro> membros = new ArrayList<Membro>();
		Query query = manager.createQuery("SELECT m FROM Membro m WHERE ongid = ?1 and status = 0");
		query.setParameter(1, organizacaoId);
		try {
			membros = (ArrayList<Membro>) query.getResultList();
			return membros;
		}catch(NoResultException e) {
			e.printStackTrace();
			return membros;
		}
	}
	
	public int status(int id) {
		System.out.println(id);
		Query query = manager.createQuery("SELECT m FROM Membro m WHERE m.id LIKE ?1");
		query.setParameter(1, id);
		try {
			Membro membro = null;
			membro = (Membro)query.getSingleResult();
			
			return membro.getStatus();
		}catch(NoResultException e) {
			return 3;
		}
	}

	public Membro getMembroById(int id) {
		Membro membro = new Membro();
		Query query = manager.createQuery("SELECT m FROM Membro m WHERE id = ?0");
		query.setParameter(0, id);
		try {
			membro = (Membro) query.getSingleResult();
			return membro;
		}catch(NoResultException e) {
			e.printStackTrace();
			return membro;
		}
	}
	
	public int getOngIdByMembroId(int membroId){
		Membro membro = new Membro();
		Query query = manager.createQuery("SELECT m FROM Membro m WHERE id = ?0");
		query.setParameter(0, membroId);
		try {
			membro = (Membro) query.getSingleResult();
			return membro.getOngId();
		}catch(NoResultException e) {
			e.printStackTrace();
			return membro.getOngId();
		}
	}
	
	public int getMembroByEmail(String emailUser) {
		Membro membro = null;
		Query query = manager.createQuery("SELECT m FROM Membro m WHERE m.email = ?1");
		query.setParameter(1, emailUser);
		try {
			membro = (Membro) query.getSingleResult();
			return membro.getId();
		}catch(NoResultException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
