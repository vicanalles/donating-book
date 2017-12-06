package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Usuario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AnuncioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.DoacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.VoluntarioRepository;

@ManagedBean
public class VoluntarioController extends Controller {
	
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
	private ArrayList<Voluntario> voluntarios;
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
	
	public String cadastra() throws NoSuchAlgorithmException {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();	
		if(senha.equals(senha2)) {
			VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
			Voluntario voluntario = new Voluntario();
			voluntario.setEmail(email);
			voluntario.setSenha(senha);
			voluntario.setNome(nome);
			voluntario.setCpf(cpf);
			voluntario.setTelefone(telefone);
			voluntario.setRua(rua);
			voluntario.setNumero(numero);
			voluntario.setComplemento(complemento);
			voluntario.setBairro(bairro);
			voluntario.setEstado(estado);
			voluntario.setCidade(cidade);
			voluntario.setTipo("Voluntario");
			voluntario.setStatus(1);
			voluntarioRepository.inserir(voluntario);
			return "/index.xhtml";
		}else {
			return "/cadastro_voluntario.xhtml";
		}
	}
	
	public String desativarVoluntario() {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		String email = (String) session.getAttribute("usuario");
		
		UsuarioRepository usuarioRepo = new UsuarioRepository(manager);
		int userId = usuarioRepo.getUserIdByEmail(email);
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		Voluntario voluntario = voluntarioRepository.getVoluntarioById(userId); 
		voluntario.setStatus(0);
		voluntarioRepository.desativar(voluntario);
		
		return "/index.xhtml";
	}
	
	public String editarVoluntario() throws NoSuchAlgorithmException {
		
		EntityManager manager = getEntityManager();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		String email = (String) session.getAttribute("usuario");
		
		UsuarioRepository usuarioRepo = new UsuarioRepository(manager);
		int userId = usuarioRepo.getUserIdByEmail(email);
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		Voluntario voluntario = voluntarioRepository.getVoluntarioById(userId);
		
		this.email = voluntario.getEmail();
		this.nome = voluntario.getNome();
		this.cpf = voluntario.getCpf();
		this.telefone = voluntario.getTelefone();
		this.rua = voluntario.getRua();
		this.numero = voluntario.getNumero();
		this.complemento = voluntario.getComplemento();
		this.bairro = voluntario.getBairro();
		this.estado = voluntario.getEstado();
		this.cidade = voluntario.getCidade();
		
		return "/client/doador/editar_dados_doador.xhtml";
	}
	
	public String atualizarVoluntario() throws NoSuchAlgorithmException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		String senhaAtual = (String) session.getAttribute("senhaAtual");
		int idUser = voluntarioRepository.getVoluntarioByEmail(emailUser);
		
		Usuario usuario = usuarioRepository.getUserById(idUser);
		Voluntario voluntario = voluntarioRepository.getVoluntarioById(idUser);
		
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
		voluntario.setNome(nome);
		voluntario.setCpf(cpf);
		voluntario.setTelefone(telefone);
		voluntario.setRua(rua);
		voluntario.setNumero(numero);
		voluntario.setComplemento(complemento);
		voluntario.setBairro(bairro);
		voluntario.setEstado(estado);
		voluntario.setCidade(cidade);
		
		usuarioRepository.atualizar(usuario);
		voluntarioRepository.atualizar(voluntario);
		
		return "/client/doador/home_doador.xhtml";
	}
	
	public ArrayList<Voluntario> getVoluntarios() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		voluntarios = voluntarioRepository.getVoluntariosRelatorio();
		
		return voluntarios;
	}
	
	public long numeroDoacoes(int voluntarioId){
		
		EntityManager manager = getEntityManager();
		
		DoacaoRepository doacaoRepository = new DoacaoRepository(manager);
		return doacaoRepository.getNumeroDoacoesVoluntario(voluntarioId);
	}
}
