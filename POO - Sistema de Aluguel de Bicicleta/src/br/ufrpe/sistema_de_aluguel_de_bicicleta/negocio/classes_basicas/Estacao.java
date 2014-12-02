package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Estacao {
	private long codigo;
	private String descricao;
	private Bicicleta bicicleta[];

	public Estacao(long codigo, String descricao) {
		this(descricao);
		this.codigo = codigo;
		this.bicicleta = new Bicicleta[20];
	}

	public Estacao(String descricao, Bicicleta[] bicicleta) {
		this(descricao);
		this.bicicleta = new Bicicleta[20];

	}

	public Estacao(String descricao) {
		this.descricao = descricao;
		this.bicicleta = new Bicicleta[20];
	}

	public Estacao() {
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Bicicleta[] getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta[] bicicleta) {
		this.bicicleta = bicicleta;
	}

}
