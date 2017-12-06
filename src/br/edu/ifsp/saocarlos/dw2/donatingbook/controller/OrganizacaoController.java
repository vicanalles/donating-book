package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Pedido;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Usuario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AnuncioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.DoacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.PedidoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.VoluntarioRepository;

@ManagedBean
public class OrganizacaoController extends Controller {

	private int id;
	private String email;
	private String senha;
	private String senha2;
	private String nome;
	private String cpf;
	private String telefone;
	private String rua;
	private int numero;
	private String complemento;
	private String bairro;
	private String estado;
	private String cidade;
	private ArrayList<Organizacao> organizacoes;
	private ArrayList<Anuncio> anuncios;
	private ArrayList<Pedido> pedidos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String cadastra() throws NoSuchAlgorithmException {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();	
		if(senha.equals(senha2)) {
			OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
			Organizacao organizacao = new Organizacao();
			organizacao.setEmail(email);
			organizacao.setSenha(senha);
			organizacao.setNome(nome);
			organizacao.setCpf(cpf);
			organizacao.setTelefone(telefone);
			organizacao.setRua(rua);
			organizacao.setNumero(numero);
			organizacao.setComplemento(complemento);
			organizacao.setBairro(bairro);
			organizacao.setEstado(estado);
			organizacao.setCidade(cidade);
			organizacao.setTipo("Organizacao");
			organizacao.setStatus(0);
			organizacao.setCodigo(nome.replace(" ", "").toLowerCase() + estado.toLowerCase());
			organizacaoRepository.inserir(organizacao);
		
			return "/index.xhtml";
		}else {
			return "/cadastro_organizacao.xhtml";
		}
	}
	
	public ArrayList<Organizacao> getOrganizacoesAproved() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacoes = organizacaoRepository.getOrganizacoesAproved();
		return organizacoes;
	}
	
	public String getOngById(int id) throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		String nomeOng = organizacaoRepository.getNomeOngById(id);
		return nomeOng;
	}
	
	public String visualizar(Organizacao ong) throws NoSuchAlgorithmException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		session.setAttribute("ong", ong);
		System.out.println(ong.getId());
		
		this.email = ong.getEmail();
		this.nome = ong.getNome();
		this.cpf = ong.getCpf();
		this.telefone = ong.getTelefone();
		this.rua = ong.getRua();
		this.numero = ong.getNumero();
		this.complemento = ong.getComplemento();
		this.bairro = ong.getBairro();
		this.estado = ong.getEstado();
		this.cidade = ong.getCidade();
		
		return "/client/organizacao/visualizar.xhtml";
	}
	
	public ArrayList<Anuncio> getAnuncios() throws NoSuchAlgorithmException{	
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		Organizacao ong = (Organizacao) session.getAttribute("ong");
		
		EntityManager manager = getEntityManager();
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		anuncios = anuncioRepository.getAnunciosByIdProp(ong.getId());
		return anuncios;
	}

	public ArrayList<Organizacao> getOrganizacoes() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		organizacoes = organizacaoRepository.getOrganizacoesRelatorio();
		
		return organizacoes;
	}
	
	public long numeroAnuncios(int ongId){
		
		EntityManager manager = getEntityManager();
		
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		return anuncioRepository.getNumeroAnuncios(ongId);
	}
	
	public ArrayList<Pedido> getPedidos() throws NoSuchAlgorithmException{
		HttpSession session = getSession();
		EntityManager manager = getEntityManager();
		
		int id = (Integer) session.getAttribute("id");
		
		PedidoRepository pedidoRepository = new PedidoRepository(manager);
		pedidos = pedidoRepository.getPedidos(id);
		
		return pedidos;
	}
	
	public ArrayList<Voluntario> getVoluntariosDoadores(){
		HttpSession session = getSession();
		EntityManager manager = getEntityManager();
		
		int id = (Integer) session.getAttribute("id");
		
		DoacaoRepository doacaoRepository = new DoacaoRepository(manager);
		return doacaoRepository.getVoluntariosDoadores(id);
	}
	
	public String nomeAnuncio(int id) {
		EntityManager manager = getEntityManager();
		
		DoacaoRepository doacaoRepository = new DoacaoRepository(manager);
		return doacaoRepository.nomeAnuncio(id);
	}
	
public String editarOrganizacao() throws NoSuchAlgorithmException {
		
		EntityManager manager = getEntityManager();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		String email = (String) session.getAttribute("usuario");
		
		UsuarioRepository usuarioRepo = new UsuarioRepository(manager);
		int userId = usuarioRepo.getUserIdByEmail(email);
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		Organizacao organizacao = organizacaoRepository.getOngById(userId);
		
		this.email = organizacao.getEmail();
		this.nome = organizacao.getNome();
		this.cpf = organizacao.getCpf();
		this.telefone = organizacao.getTelefone();
		this.rua = organizacao.getRua();
		this.numero = organizacao.getNumero();
		this.complemento = organizacao.getComplemento();
		this.bairro = organizacao.getBairro();
		this.estado = organizacao.getEstado();
		this.cidade = organizacao.getCidade();
		
		return "/client/organizacao/editar_perfil_organizacao.xhtml";
	}
	
	public String atualizarOrganizacao() throws NoSuchAlgorithmException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		String senhaAtual = (String) session.getAttribute("senhaAtual");
		int idUser = organizacaoRepository.getOngByEmail(emailUser);
		
		Usuario usuario = usuarioRepository.getUserById(idUser);
		Organizacao organizacao= organizacaoRepository.getOngById(idUser);
		
		if(senha.equals("") && senha2.equals("")) {
			usuario.setSenha(senhaAtual);
		}
		else if(senha.equals(senha2)) {
			usuario.setSenha(senha);
			session.setAttribute("senhaAtual", senha);
		}
		else {
			return "";
		}
		
		usuario.setEmail(email);
		organizacao.setNome(nome);
		organizacao.setCpf(cpf);
		organizacao.setTelefone(telefone);
		organizacao.setRua(rua);
		organizacao.setNumero(numero);
		organizacao.setComplemento(complemento);
		organizacao.setBairro(bairro);
		organizacao.setEstado(estado);
		organizacao.setCidade(cidade);
		
		usuarioRepository.atualizar(usuario);
		organizacaoRepository.atualizar(organizacao);
		
		return "/client/organizacao/home_ong.xhtml";
	}
}
