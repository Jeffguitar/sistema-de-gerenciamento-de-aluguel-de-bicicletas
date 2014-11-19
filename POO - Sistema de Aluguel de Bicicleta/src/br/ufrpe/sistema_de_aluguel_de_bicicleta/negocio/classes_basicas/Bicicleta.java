package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Bicicleta {
	private long codigo;
	private boolean alugou;

	public Bicicleta(long codigo, boolean alugou, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.alugou = false;
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
