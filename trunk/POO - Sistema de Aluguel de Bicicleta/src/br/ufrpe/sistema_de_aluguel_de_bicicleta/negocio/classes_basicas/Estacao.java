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

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Bicicleta[] getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta[] bicicleta) {
		this.bicicleta = bicicleta;
	}

}
