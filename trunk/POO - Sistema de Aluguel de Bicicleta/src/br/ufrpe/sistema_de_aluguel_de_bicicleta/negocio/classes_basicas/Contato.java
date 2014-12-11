package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;

public class Contato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String telefone;
	private String celular;

	public Contato(String email, String telefone, String celular) {
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
	}

	public Contato(String email, String telefone) {
		super();
		this.email = email;
		this.telefone = telefone;
	}

	public Contato(String telefone) {
		super();
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}
