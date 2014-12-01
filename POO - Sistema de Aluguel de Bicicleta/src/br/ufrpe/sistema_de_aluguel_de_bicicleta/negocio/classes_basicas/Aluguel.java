package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.util.Date;

public class Aluguel {
	private long id;
	private Estacao estacao;
	private Cliente cliente;
	private Date data;

	public Aluguel(long id, Estacao estacao, Cliente cliente, Date data) {
		this.id = id;
		this.estacao = estacao;
		this.cliente = cliente;
		this.data = data;
	}

	public Aluguel(Estacao estacao, Cliente cliente, Date data) {
		this.estacao = estacao;
		this.cliente = cliente;
		this.data = data;
	}

	public Aluguel(Cliente cliente) {
		this.cliente = cliente;
	}

	public Aluguel(Estacao estacao) {
		this.estacao = estacao;
	}

	public Aluguel(Date data) {
		this.data = data;
	}

	public Aluguel() {

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
