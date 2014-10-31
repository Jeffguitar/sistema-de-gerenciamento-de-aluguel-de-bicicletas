package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Bicicleta {
	private int codigo;
	private boolean alugou;
	private Cliente cliente;

	public Bicicleta(int codigo, boolean alugou, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.alugou = false;
		this.cliente = cliente;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean getAlugou() {
		return alugou;
	}

	public void setAlugou(boolean alugou) {
		this.alugou = alugou;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
