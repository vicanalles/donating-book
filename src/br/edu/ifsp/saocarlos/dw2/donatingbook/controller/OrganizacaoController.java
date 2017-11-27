package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Organizacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AnuncioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;

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
			organizacao.setCodigo("minhaorganizacao");
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
		
		String nomeOng = organizacaoRepository.getOngById(id);
		return nomeOng;
	}
	
}
