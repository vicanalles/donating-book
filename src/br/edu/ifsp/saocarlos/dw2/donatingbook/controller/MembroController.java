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
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Doacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Interesse;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Membro;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Pedido;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Usuario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AnuncioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.DoacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.InteresseRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.MembroRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.PedidoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;

@ManagedBean
public class MembroController extends Controller {

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
	private String codigoOng;
	private ArrayList<Membro> membros;
	
	public String getCodigoOng() {
		return codigoOng;
	}
	public void setCodigoOng(String codigoOng) {
		this.codigoOng = codigoOng;
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
	
	public String cadastra() throws NoSuchAlgorithmException{
		
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		MembroRepository membroRepository = new MembroRepository(manager);
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		Membro membro = new Membro();
		if(codigoOng != null && senha != null && senha2 != null && email != null) {			
			int codigo = organizacaoRepository.getOngIdByCodigo(codigoOng);
			if(codigo != 0 && senha.equals(senha2)) {
				membro.setOngId(codigo);
				membro.setEmail(email);
				membro.setSenha(senha);
				membro.setNome(nome);
				membro.setCpf(cpf);
				membro.setTelefone(telefone);
				membro.setRua(rua);
				membro.setNumero(numero);
				membro.setComplemento(complemento);
				membro.setBairro(bairro);
				membro.setEstado(estado);
				membro.setCidade(cidade);
				membro.setTipo("membro");
				membro.setStatus(1);
			}
			
			membroRepository.inserir(membro);
			return "/index.xhtml";
		}else {
			return "/cadastro_membro_ong.xhtml";
		}
	}
	
	public ArrayList<Membro> getMembrosAtivosOng() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		String emailUser = (String) session.getAttribute("usuario");
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		int organizacaoId = organizacaoRepository.getOngByEmail(emailUser);
		
		MembroRepository membroRepository = new MembroRepository(manager);
		membros = membroRepository.getMembrosByOngId(organizacaoId);
		
		return membros;
	}
	
	public ArrayList<Membro> getMembrosDesativadosOng() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		String emailUser = (String) session.getAttribute("usuario");
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		int organizacaoId = organizacaoRepository.getOngByEmail(emailUser);
		
		MembroRepository membroRepository = new MembroRepository(manager);
		membros = membroRepository.getMembrosDesativadosOng(organizacaoId);
		
		return membros;
	}
	
	public String desativarMembro(Membro membro) throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		MembroRepository membroRepository = new MembroRepository(manager);
		membro.setStatus(0);
		membroRepository.desativar(membro);
		
		return "/client/organizacao/participantes.xhtml";
	}
	
	public String ativarMembro(Membro membro) throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		MembroRepository membroRepository = new MembroRepository(manager);
		membro.setStatus(1);
		membroRepository.ativar(membro);
		
		return "/client/organizacao/participantes.xhtml";
	}
	
	public String novoPedido(Pedido pedido) throws NoSuchAlgorithmException{
		
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		PedidoRepository pedidoRepository = new PedidoRepository(manager);		
		pedidoRepository.inserir(pedido);
		
		return "/client/membro/home_membro.xhtml";
	}
	
	public ArrayList<Anuncio> getAnuncios(){
		
		HttpSession session = getSession();
		EntityManager manager = getEntityManager();
		MembroRepository membroRepository = new MembroRepository(manager);
		int ongId = membroRepository.getOngIdByMembroId((Integer) session.getAttribute("id"));
		
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		return anuncioRepository.getAnunciosByIdProp(ongId);
	}
	
	public String demonstrarInteresse(Anuncio anuncio){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		HttpSession session = getSession();
		int idMembro = (Integer) session.getAttribute("id");
		InteresseRepository interesseRepository = new InteresseRepository(manager);
		Interesse interesse = new Interesse();
		
		interesse.setIdAnuncio(anuncio.getId());
		interesse.setIdMembro(idMembro);
		
		interesseRepository.inserir(interesse);
		
		return "/client/membro/anuncios_organizacao.xhtml";
	}
	
	public String editarMembro() throws NoSuchAlgorithmException {
		
		EntityManager manager = getEntityManager();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		MembroRepository membroRepository = new MembroRepository(manager);
		int userId = (Integer) session.getAttribute("id");
		System.out.println(session.getAttribute("id"));
		Membro membro = membroRepository.getMembroById(userId);
		this.email = membro.getEmail();
		this.nome = membro.getNome();
		this.cpf = membro.getCpf();
		this.telefone = membro.getTelefone();
		this.rua = membro.getRua();
		this.numero = membro.getNumero();
		this.complemento = membro.getComplemento();
		this.bairro = membro.getBairro();
		this.estado = membro.getEstado();
		this.cidade = membro.getCidade();
		
		return "/client/membro/editar_dados_membro.xhtml";
	}
	
	public String atualizarMembro() throws NoSuchAlgorithmException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		MembroRepository membroRepository = new MembroRepository(manager);
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		String senhaAtual = (String) session.getAttribute("senhaAtual");
		int idUser = membroRepository.getMembroByEmail(emailUser);
		
		Usuario usuario = usuarioRepository.getUserById(idUser);
		Membro membro = membroRepository.getMembroById(idUser);
		
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
		membro.setNome(nome);
		membro.setCpf(cpf);
		membro.setTelefone(telefone);
		membro.setRua(rua);
		membro.setNumero(numero);
		membro.setComplemento(complemento);
		membro.setBairro(bairro);
		membro.setEstado(estado);
		membro.setCidade(cidade);
		
		usuarioRepository.atualizar(usuario);
		membroRepository.atualizar(membro);
		
		return "/client/membro/home_membro.xhtml";
	}
}
