package br.edu.ifsp.saocarlos.dw2.donatingbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Interesse {
	
	@Id
	@GeneratedValue
	private int id;
	private int idMembro;	
	private int idAnuncio;
	
	public int getIdMembro() {
		return idMembro;
	}
	public void setIdMembro(int idMembro) {
		this.idMembro = idMembro;
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
	
	
}
