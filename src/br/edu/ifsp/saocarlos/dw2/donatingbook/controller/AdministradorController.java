package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Administrador;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AdministradorRepository;

@ManagedBean
public class AdministradorController extends Controller{

	private String email;
	private String senha;
	private String senha2;
	private String nome;
	
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
		
		FacesContext fc = FacesContext.getCurrentInstance();
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
}
