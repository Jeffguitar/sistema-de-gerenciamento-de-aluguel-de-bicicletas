package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class CidadeException extends Exception {

	private static final long serialVersionUID = 1L;

	public CidadeException() {
		super("A cidade informada é inválida.");
	}
}
