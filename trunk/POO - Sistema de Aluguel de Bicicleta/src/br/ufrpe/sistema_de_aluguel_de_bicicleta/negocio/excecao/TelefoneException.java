package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class TelefoneException extends Exception {

	private static final long serialVersionUID = 1L;

	public TelefoneException() {
		super("O telefone informado é inválido.");
	}
}
