package br.edu.ifsp.saocarlos.dw2.donatingbook.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "adm")
@PrimaryKeyJoinColumn(name = "id")
public class Administrador extends Usuario{
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
