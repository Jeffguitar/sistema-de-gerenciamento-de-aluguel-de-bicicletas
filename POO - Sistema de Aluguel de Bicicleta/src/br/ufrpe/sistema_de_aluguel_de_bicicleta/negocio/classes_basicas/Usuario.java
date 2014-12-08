package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;

abstract class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nome;
	protected String cpf;

	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public Usuario() {

	}

	public abstract String getNome();

	public abstract void setNome(String nome);

	public abstract String getCpf();

	public abstract void setCpf(String cpf);
}
