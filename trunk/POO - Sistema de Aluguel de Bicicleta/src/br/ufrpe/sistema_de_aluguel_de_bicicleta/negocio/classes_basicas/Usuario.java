package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Usuario {
	protected String nome;
	protected String cpf;
	protected int identidade;
	protected String endereco;
	protected int idade;

	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public Usuario(String nome, String cpf, int identidade, String endereco,
			int idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.identidade = identidade;
		this.endereco = endereco;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
}
