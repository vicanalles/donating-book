package br.edu.ifsp.saocarlos.dw2.donatingbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue
	private int id;
	private String titulo;
	private String descricao;
	private int idProp;
	@Transient
	private String nomeOng;
	@ManyToOne
	@JoinColumn(name="idLivro")
	private Livro livro;
	//private int quantidade;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
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
	public int getIdProp() {
		return idProp;
	}
	public void setIdProp(int idProp) {
		this.idProp = idProp;
	}
	public String getNomeOng() {
		return nomeOng;
	}
	public void setNomeOng(String nomeOng) {
		this.nomeOng = nomeOng;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}/*
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}*/
	
}
