package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AnuncioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;

@ManagedBean
public class AnuncioController extends Controller{
	
	private String titulo;
	private int quantidade;
	private String descricao;
	private int idProprietario;
	
	public int getIdProprietario() {
		return idProprietario;
	}
	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}
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
	
	public String cadastraNovoAnuncio() throws NoSuchAlgorithmException{
		FacesContext context = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		if(!titulo.equals("") && !descricao.equals("")) {
			ExternalContext externalContext = context.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
			OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
			AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
			Anuncio anuncio = new Anuncio();
			anuncio.setTitulo(titulo);
			anuncio.setQuantidade(quantidade);
			anuncio.setDescricao(descricao);
			
			String emailUser = (String) session.getAttribute("usuario");
			int idProp = organizacaoRepository.getOngByEmail(emailUser);
			anuncio.setIdProp(idProp);			
			anuncioRepository.inserir(anuncio);
			
			return "/donating-book/client/organizacao/meus_anuncios.xhtml";
		}else {
			return "/donating-book/client/organizacao/novo_anuncio.xhtml";
		}
	}
}
