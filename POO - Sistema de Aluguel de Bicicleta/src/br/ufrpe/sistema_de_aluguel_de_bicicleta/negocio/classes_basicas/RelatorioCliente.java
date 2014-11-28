package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.util.Date;

public class RelatorioCliente {
	private long id;
	private Cliente cliente;
	private Estacao estacao;
	private Date data;
	private double preco;

	public RelatorioCliente(long id, Cliente cliente, Estacao estacao,
			Date data, double preco) {
		this.id = id;
		this.cliente = cliente;
		this.estacao = estacao;
		this.data = data;
		this.preco = preco;
	}

	public RelatorioCliente(Cliente cliente, Estacao estacao, Date data,
			double preco) {
		this.cliente = cliente;
		this.estacao = estacao;
		this.data = data;
		this.preco = preco;
	}

	public RelatorioCliente(Date data) {
		this.data = data;
	}

	public RelatorioCliente() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
