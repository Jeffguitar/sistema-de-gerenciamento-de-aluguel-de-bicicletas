package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Administrador extends Usuario {

	public Administrador(String nome, String cpf) {
		super(nome, cpf);
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getCpf() {
		return this.cpf;
	}

	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
