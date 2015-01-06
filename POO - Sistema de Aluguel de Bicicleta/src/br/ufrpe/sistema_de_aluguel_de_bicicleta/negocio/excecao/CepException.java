package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class CepException extends Exception {

	private static final long serialVersionUID = 1L;

	public CepException() {
		super("O CEP informado é inválido.");
	}

}
