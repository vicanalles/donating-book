package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Anuncio;
import br.edu.ifsp.saocarlos.dw2.donatingbook.model.Doacao;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.AnuncioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.DoacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.VoluntarioRepository;

@ManagedBean
public class AnuncioController extends Controller{
	
	private String titulo;
	private String descricao;
	private int idProprietario;
	private ArrayList<Anuncio> anuncios;
	private String nomeOng;
	
	public String getNomeOng() {
		return nomeOng;
	}
	public void setNomeOng(String nomeOng) {
		this.nomeOng = nomeOng;
	}
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
			anuncio.setDescricao(descricao);
			
			String emailUser = (String) session.getAttribute("usuario");
			int idProp = organizacaoRepository.getOngByEmail(emailUser);
			anuncio.setIdProp(idProp);
			anuncioRepository.inserir(anuncio);
			
			return "meus_anuncios";
		}else {
			return "novo_anuncio";
		}
	}
	
	public ArrayList<Anuncio> getMeusAnunciosByIdProp() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		int idProp = organizacaoRepository.getOngByEmail(emailUser);
		
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		anuncios = anuncioRepository.getAnunciosByIdProp(idProp);
		
		return anuncios;
	}
	
	public ArrayList<Anuncio> getAnuncios() throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);

		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		anuncios = anuncioRepository.getAnuncios();
		OrganizacaoController organizacaoController = new OrganizacaoController();
		for(Anuncio anuncio : anuncios) {
			nomeOng = organizacaoController.getOngById(anuncio.getIdProp());
			anuncio.setNomeOng(nomeOng);
		}
		
		return anuncios;
	}
	
	public String removerAnuncio(Anuncio anuncio) throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		int idProp = organizacaoRepository.getOngByEmail(emailUser);
		
		anuncioRepository.remover(anuncio);
		
		return "/donating-book/client/organizacao/meus_anuncios.xhtml";
	}
	
	public String editarAnuncio(Anuncio anuncio) throws NoSuchAlgorithmException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		session.setAttribute("anuncio", anuncio);
		System.out.println(anuncio.getId());
		this.titulo = anuncio.getTitulo();
		this.descricao = anuncio.getDescricao();
		
		return "/client/organizacao/editar_anuncio.xhtml";
	}
	
	public String atualizarAnuncio() throws NoSuchAlgorithmException{		
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.TRUE);
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		OrganizacaoRepository organizacaoRepository = new OrganizacaoRepository(manager);
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		String emailUser = (String) session.getAttribute("usuario");
		int idProp = organizacaoRepository.getOngByEmail(emailUser);
		
		Anuncio anuncio = (Anuncio) session.getAttribute("anuncio");
		anuncio.setDescricao(descricao);
		anuncio.setTitulo(titulo);
		anuncio.setIdProp(idProp);
		
		anuncioRepository.atualizar(anuncio);
		
		return "/client/organizacao/meus_anuncios.xhtml";
	}
	
	public ArrayList<Anuncio> getAnunciosByIdProp(int id) throws NoSuchAlgorithmException{
		
		EntityManager manager = getEntityManager();
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		
		AnuncioRepository anuncioRepository = new AnuncioRepository(manager);
		anuncios = anuncioRepository.getAnunciosByIdProp(id);
		
		return anuncios;
	}
	
	public String doar(Anuncio anuncio) {		
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		
		int idUsuario = (Integer) session.getAttribute("id");
		
		DoacaoRepository doacaoRepository = new DoacaoRepository(manager);
		Doacao doacao = new Doacao();
		
		doacao.setIdAnuncio(anuncio.getId());
		doacao.setIdUsuario(idUsuario);
		doacao.setIdReceptor(anuncio.getIdProp());
		
		doacaoRepository.inserir(doacao);
		
		return "/donating-book/client/doador/home_doador.xhtml";
	}
	
}
