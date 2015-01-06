package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class DescricaoInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public DescricaoInvalidaException() {
		super("A descrição informada é inválida.");
	}
}
