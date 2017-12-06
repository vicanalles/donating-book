package br.edu.ifsp.saocarlos.dw2.donatingbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Doacao {
	@Id
	@GeneratedValue
	private int id;
	private int idAnuncio;
	private int idUsuario;
	private int idReceptor;
	
	public int getIdReceptor() {
		return idReceptor;
	}
	public void setIdReceptor(int idReceptor) {
		this.idReceptor = idReceptor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAnuncio() {
		return idAnuncio;
	}
	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
