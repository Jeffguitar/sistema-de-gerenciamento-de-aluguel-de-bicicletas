package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public enum SexoTipo {

	M("Masculino"), F("Femininos");

	private String descricao;

	private SexoTipo(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
