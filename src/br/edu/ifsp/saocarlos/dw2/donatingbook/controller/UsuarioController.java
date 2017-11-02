package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UsuarioController {

	private String email;
	private String senha;
	private String senha2;
	
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
}
