package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;
import java.util.Calendar;

public class Aluguel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Estacao estacao;
	private Cliente cliente;
	private Calendar dataAluguel;
	private Calendar dataDevolucao;
	private double valor;

	public Aluguel(long id, Estacao estacao, Cliente cliente,
			Calendar dataAluguel, Calendar dataDevolucao, double valor) {
		this.id = id;
		this.estacao = estacao;
		this.cliente = cliente;
		this.dataAluguel = dataAluguel;
		this.dataDevolucao = dataDevolucao;
		this.valor = valor;
	}

	public Aluguel(Estacao estacao, Cliente cliente, Calendar dataAluguel,
			Calendar dataDevolucao, double valor) {
		this.estacao = estacao;
		this.cliente = cliente;
		this.dataAluguel = dataAluguel;
		this.dataDevolucao = dataDevolucao;
		this.valor = valor;
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

	public Calendar getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(Calendar dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
