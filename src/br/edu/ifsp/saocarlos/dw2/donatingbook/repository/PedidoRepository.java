package br.edu.ifsp.saocarlos.dw2.donatingbook.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Pedido;

public class PedidoRepository {

	private EntityManager manager;
	
	public PedidoRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Pedido pedido) {
		manager.persist(pedido);
	}
	
	public ArrayList<Pedido> getPedidos(int ongId){
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		Query query = manager.createQuery("SELECT p FROM pedido p WHERE ongid = ?0");
		query.setParameter(0, ongId);
		try {
			pedidos = (ArrayList<Pedido>) query.getResultList();
			return pedidos;
		}catch(NoResultException e) {
			e.printStackTrace();
			return pedidos;
		}
	}
}
