package br.edu.ifsp.saocarlos.dw2.donatingbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue
	private int id;
	private String titulo;
	private int quantidade;
	private String descricao;
	private int idProp;
	
	public int getId() {
		return id;
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
	public int getIdProp() {
		return idProp;
	}
	public void setIdProp(int idProp) {
		this.idProp = idProp;
	}
}
