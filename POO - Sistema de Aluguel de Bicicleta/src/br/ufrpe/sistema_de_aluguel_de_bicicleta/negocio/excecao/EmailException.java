package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class EmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailException() {
		super("O e-mail informado é inválido.");
	}
}
