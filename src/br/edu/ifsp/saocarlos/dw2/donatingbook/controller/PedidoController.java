package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Membro;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Pedido;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.MembroRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.PedidoRepository;

@ManagedBean
public class PedidoController extends Controller{

	private String titulo;
	private int quantidade;
	private String descricao;
	private ArrayList<Pedido> pedidos;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public String cadastrarPedido(){
		HttpSession httpSession = getSession();
		EntityManager manager = getEntityManager();
		
		Pedido pedido = new Pedido();
		pedido.setTitulo(titulo);
		pedido.setQuantidade(quantidade);
		pedido.setDescricao(descricao);
		
		int id = (Integer) httpSession.getAttribute("id");
		
		MembroRepository membroRepository = new MembroRepository(manager);
		Membro membro = membroRepository.getMembroById(id);
		
		pedido.setMembro(membro);

		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		Organizacao organizacao = organizacaoRepository.getOngById(membro.getOngId());
		
		pedido.setOrganizacao(organizacao);
		
		PedidoRepository pedidoRepository = new PedidoRepository(manager);
		pedidoRepository.inserir(pedido);
		
		return "/client/membro/home_membro.xhtml";
	}
	
	public ArrayList<Pedido> getPedidosMembro(){
		HttpSession httpSession = getSession();
		EntityManager manager = getEntityManager();
		
		int id = (Integer) httpSession.getAttribute("id");
		
		PedidoRepository pedidoRepository = new PedidoRepository(manager);
		pedidos = pedidoRepository.getPedidosByMembroId(id);
		
		return pedidos;
	}
}
