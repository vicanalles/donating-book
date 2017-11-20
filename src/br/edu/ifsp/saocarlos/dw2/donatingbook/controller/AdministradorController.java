package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Administrador;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Voluntario;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AdministradorRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
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
	
	public ArrayList<Organizacao> getOrganizacoesAproved() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		
		organizacoes = organizacaoRepository.getOrganizacoesAproved();
		return organizacoes;
	}
	
	public ArrayList<Voluntario> getVoluntarios() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		voluntarios = voluntarioRepository.getVoluntarios();
		return voluntarios;
	}
}
