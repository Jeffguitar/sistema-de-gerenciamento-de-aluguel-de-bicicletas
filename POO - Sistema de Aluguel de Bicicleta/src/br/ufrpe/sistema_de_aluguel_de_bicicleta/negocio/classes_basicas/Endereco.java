package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;

public class Endereco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String endereco;
	private String cidade;
	private String bairro;
	private String cep;

	public Endereco(String endereco, String cidade, String bairro, String cep) {
		this.endereco = endereco;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
	}

	public Endereco(String endereco, String cidade, String bairro) {
		super();
		this.endereco = endereco;
		this.cidade = cidade;
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}