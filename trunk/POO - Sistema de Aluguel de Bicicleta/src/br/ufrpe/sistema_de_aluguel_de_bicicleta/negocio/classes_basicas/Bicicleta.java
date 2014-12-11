package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;

public class Bicicleta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long codigo;
	private boolean alugou;

	public Bicicleta(long codigo, boolean alugou) {
		this.codigo = codigo;
		this.alugou = alugou;
	}

	public Bicicleta(long codigo) {
		this.codigo = codigo;
		this.alugou = false;
	}

	public Bicicleta(boolean alugou) {
		this.alugou = alugou;
	}

	public Bicicleta() {

	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public boolean getAlugou() {
		return alugou;
	}

	public void setAlugou(boolean alugou) {
		this.alugou = alugou;
	}

}
