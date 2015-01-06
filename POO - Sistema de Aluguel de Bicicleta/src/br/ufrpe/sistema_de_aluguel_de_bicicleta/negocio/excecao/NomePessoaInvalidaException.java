package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class NomePessoaInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NomePessoaInvalidaException() {
		super("O nome informado é inválido.");
	}
}
