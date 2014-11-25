package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.util.Calendar;

public class Cliente extends Usuario {
	private int identidade;
	private String endereco;
	private Calendar data;

	public Cliente(String nome, String cpf, int identidade, String endereco) {
		super(nome, cpf);
		this.identidade = identidade;
		this.endereco = endereco;
		this.data = null; // quando instanciar um cliente, necessariamente, ele
							// não terá data de aluguel.
	}

	public Cliente(String nome, String cpf, int identidade, String endereco,
			Calendar data) {
		super(nome, cpf);
		this.identidade = identidade;
		this.endereco = endereco;
		this.data = data; // quando instanciar um cliente, informar uma data de
							// aluguel.
	}

	public int getIdentidade() {
		return identidade;
	}

	public void setIdentidade(int identidade) {
		this.identidade = identidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;

	}

	@Override
	public String getCpf() {
		return cpf;
	}

	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
