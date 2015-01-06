package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class RuaException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuaException() {
		super("O nome da rua informado é inválido.");
	}

}
