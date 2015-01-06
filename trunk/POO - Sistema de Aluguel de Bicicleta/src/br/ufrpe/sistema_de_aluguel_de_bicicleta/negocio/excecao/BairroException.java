package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class BairroException extends Exception {

	private static final long serialVersionUID = 1L;

	public BairroException() {
		super("O bairro informado é inválido.");
	}
}
