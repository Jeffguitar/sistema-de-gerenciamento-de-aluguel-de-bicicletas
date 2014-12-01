package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;


public class RelatorioCliente {
	private long id;
	private Aluguel aluguel;
	private double preco;

	public RelatorioCliente(long id, Aluguel aluguel, double preco) {
		this.id = id;
		this.aluguel = aluguel;
		this.preco = preco;
	}

	public RelatorioCliente(Aluguel aluguel, double preco) {
		this.aluguel = aluguel;
		this.preco = preco;
	}

	public RelatorioCliente(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public RelatorioCliente() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
