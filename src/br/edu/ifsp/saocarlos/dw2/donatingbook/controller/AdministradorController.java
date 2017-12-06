package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Administrador;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Usuario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AdministradorRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.DoacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.InteresseRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.VoluntarioRepository;

@ManagedBean
public class AdministradorController extends Controller{

	private String email;
	private String senha;
	private String senha2;
	private String nome;
	private ArrayList<Voluntario> voluntarios;
	private ArrayList<Organizacao> organizacoes;
	private Organizacao organizacao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	public String cadastra() throws NoSuchAlgorithmException{
		EntityManager manager = getEntityManager();
		if(senha.equals(senha2)) {
			AdministradorRepository administradorRepository = new AdministradorRepository(manager);
			Administrador administrador = new Administrador();
			administrador.setEmail(email);
			administrador.setSenha(senha);
			administrador.setNome(nome);
			administrador.setTipo("Administrador");
			administradorRepository.inserir(administrador);
			
			return "/login.xhtml";
		}else {
			return "/cadastro_administrador.xhtml";
		}
	}
	
	public ArrayList<Organizacao> getOngNotAproved() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacoes = organizacaoRepository.getOrganizacaoNotAproved();
		return organizacoes;
	}
	
	public String aprovarOrganizacao(Organizacao organizacao){
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacao.setStatus(1);
		organizacaoRepository.aprovar(organizacao);
		
		return "/donating-book/client/admin/home_admin.xhtml";
	}
	
	public String desativarOrganizacao(Organizacao organizacao) {
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacao.setStatus(2);
		organizacaoRepository.desativar(organizacao);
		
		return "/donating-book/client/admin/organizacoes.xhtml";
	}
	
	public String desativarVoluntario(Voluntario voluntario) {
		EntityManager manager = getEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		voluntario.setStatus(0);
		voluntarioRepository.desativar(voluntario);
		
		return "/donating-book/client/admin/doadores.xhtml";
	}
	
	public String ativarVoluntario(Voluntario voluntario) {
		EntityManager manager = getEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		voluntario.setStatus(1);
		voluntarioRepository.ativar(voluntario);
		
		return "/donating-book/client/admin/doadores.xhtml";
	}
	
	public String ativarOrganizacao(Organizacao organizacao) {
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		organizacao.setStatus(1);
		organizacaoRepository.ativar(organizacao);
		
		return "/donating-book/client/admin/organizacoes.xhtml";
	}
	
	public ArrayList<Organizacao> getOrganizacoesAproved() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacoes = organizacaoRepository.getOrganizacoesAproved();
		return organizacoes;
	}
	
	public ArrayList<Organizacao> getOrganizacoesDesativadas() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacoes = organizacaoRepository.getOrganizacoesDesativadas();
		return organizacoes;
	}
	
	public ArrayList<Voluntario> getVoluntarios() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		voluntarios = voluntarioRepository.getVoluntarios();
		return voluntarios;
	}
	
	public ArrayList<Voluntario> getVoluntariosDesativados() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		voluntarios = voluntarioRepository.getVoluntariosDesativados();
		return voluntarios;
	}
	
	public long getNumeroDoacoesRecebidas() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		DoacaoRepository doacaoRepository = new DoacaoRepository(manager);
		
		return doacaoRepository.getNumeroDoacoesRecebidas();
	}
	
	public long getNumeroDoacoesRepassadas() {
		
		EntityManager manager = getEntityManager();
		
		InteresseRepository interesseRepository = new InteresseRepository(manager);
		
		return interesseRepository.getNumeroDoacoesRepassadas();
	}
	
	public String editarAdministrador() throws NoSuchAlgorithmException {
		
		EntityManager manager = getEntityManager();
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		String email = (String) session.getAttribute("usuario");
		
		UsuarioRepository usuarioRepo = new UsuarioRepository(manager);
		int userId = usuarioRepo.getUserIdByEmail(email);
		
		AdministradorRepository administradorRepository = new AdministradorRepository(manager);
		Administrador administrador = administradorRepository.getAdministradorById(userId);
		
		this.email = administrador.getEmail();
		this.nome = administrador.getNome();
		
		return "/client/admin/editar_perfil_admin.xhtml";
	}
	
	public String atualizarAdministrador() throws NoSuchAlgorithmException{
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		AdministradorRepository administradorRepository = new AdministradorRepository(manager);
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		String senhaAtual = (String) session.getAttribute("senhaAtual");
		int idUser = administradorRepository.getAdministradorByEmail(emailUser);
		Usuario usuario = usuarioRepository.getUserById(idUser);
		Administrador administrador = administradorRepository.getAdministradorById(idUser);
		
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
		administrador.setNome(nome);
		
		usuarioRepository.atualizar(usuario);
		administradorRepository.atualizar(administrador);
		
		return "/client/admin/home_admin.xhtml";
	}
	
}
