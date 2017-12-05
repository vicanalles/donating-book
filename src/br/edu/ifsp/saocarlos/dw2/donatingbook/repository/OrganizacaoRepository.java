package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;

public class OrganizacaoRepository {
	
	private EntityManager manager;
	
	public OrganizacaoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Organizacao organizacao) {
		manager.persist(organizacao);
	}
	
	public void aprovar(Organizacao organizacao) {
		manager.persist(organizacao);
	}
	
	public void desativar(Organizacao organizacao) {
		manager.persist(organizacao);
	}
	
	public void ativar(Organizacao organizacao) {
		manager.persist(organizacao);
	}
	
	/*
	 * C�digos para verifica��o de status:
	 * 0 - Organiza��o aguardando aprova��o do administrador, n�o pode acessar o sistema
	 * 1 - Organiza��o aprovada, pode acessar o sistema normalmente
	 * 2 - Organiza��o desativada, n�o pode acessar o sistema
	 */
	
	public int status(int id) {
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE o.id LIKE ?1");
		query.setParameter(1, id);
		try {
			Organizacao organizacao = null;
			organizacao = (Organizacao)query.getSingleResult();
			
			return organizacao.getStatus();
		}catch(NoResultException e) {
			return 3;
		}
	}
	
	public ArrayList<Organizacao> getOrganizacaoNotAproved() {
		ArrayList<Organizacao> organizacoes = new ArrayList<Organizacao>();
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE status = 0");
		try {
			organizacoes = (ArrayList<Organizacao>)query.getResultList();
			return organizacoes;
		}catch(NoResultException e) {
			e.printStackTrace();
			return organizacoes;
		}
	}
	
	public ArrayList<Organizacao> getOrganizacoesAproved(){
		ArrayList<Organizacao> organizacoes = new ArrayList<Organizacao>();
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE status = 1");
		try {
			organizacoes = (ArrayList<Organizacao>)query.getResultList();
			return organizacoes;
		}catch(NoResultException e) {
			e.printStackTrace();
			return organizacoes;
		}
	}
	
	public ArrayList<Organizacao> getOrganizacoesDesativadas() {
		
		ArrayList<Organizacao> organizacoes = new ArrayList<Organizacao>();
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE status = 2");
		try {
			organizacoes = (ArrayList<Organizacao>)query.getResultList();
			return organizacoes;
		}catch(NoResultException e) {
			e.printStackTrace();
			return organizacoes;
		}
	}

	public int getOngByEmail(String emailUser) {
		Organizacao organizacao = null;
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE o.email = ?1");
		query.setParameter(1, emailUser);
		try {
			organizacao = (Organizacao) query.getSingleResult();
			return organizacao.getId();
		}catch(NoResultException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getNomeOngById(int id) {
		Organizacao organizacao = null;
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE o.id = ?1");
		query.setParameter(1, id);
		try {
			organizacao = (Organizacao) query.getSingleResult();
			return organizacao.getNome();
		}catch(NoResultException e) {
			e.printStackTrace();
			return "";
		}
	}

	public int getOngIdByCodigo(String codigoOng) {
		Organizacao organizacao = null;
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE o.codigo = ?1");
		query.setParameter(1, codigoOng);
		try {
			organizacao = (Organizacao) query.getSingleResult();
			return organizacao.getId();
		}catch(NoResultException e) {
			e.printStackTrace();
			return organizacao.getId();
		}
	}

	public Organizacao getOngById(int ongId) {
		Organizacao organizacao = null;
		Query query = manager.createQuery("SELECT o FROM Organizacao o WHERE o.id = ?0");
		query.setParameter(0, ongId);
		try {
			organizacao = (Organizacao) query.getSingleResult();
			return organizacao;
		}catch(NoResultException e) {
			e.printStackTrace();
			return organizacao;
		}
	}
	
	public ArrayList<Organizacao> getOrganizacoesRelatorio(){
		ArrayList<Organizacao> organizacoes = null;
		Query query = manager.createQuery("SELECT o FROM Organizacao o");
		try {
			organizacoes = (ArrayList<Organizacao>) query.getResultList();
			return organizacoes;
		}catch(NoResultException e) {
			e.printStackTrace();
			return organizacoes;
		}
	}
}
