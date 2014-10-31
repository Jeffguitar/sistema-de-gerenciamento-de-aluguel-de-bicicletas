package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Estacao {
	private long codigo;
	private Bicicleta bicicleta[];

	public Estacao(long codigo, int tamanho) {
		this.codigo = codigo;
		this.bicicleta = new Bicicleta[tamanho];
	}

	public long getCodigo() {
		return codigo;
	}

	public Bicicleta[] getBicicleta() {
		return bicicleta;
	}
}
