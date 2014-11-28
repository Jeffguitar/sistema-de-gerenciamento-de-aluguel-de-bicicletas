package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Alugueis {
	private long id;
	private Estacao estacao;
	private Cliente cliente;

	public Alugueis(long id, Estacao estacao, Cliente cliente) {
		this(estacao, cliente);
		this.id = id;
	}

	public Alugueis(Estacao estacao, Cliente cliente) {
		this.estacao = estacao;
		this.cliente = cliente;
	}

	public Alugueis() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
