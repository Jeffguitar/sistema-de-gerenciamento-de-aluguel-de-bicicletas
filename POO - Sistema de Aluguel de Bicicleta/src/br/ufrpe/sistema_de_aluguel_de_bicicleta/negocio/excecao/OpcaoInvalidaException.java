package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class OpcaoInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public OpcaoInvalidaException() {
		super("Op��o de menu inv�lida.");
	}

}
