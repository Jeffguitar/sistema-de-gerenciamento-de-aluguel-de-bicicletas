package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class NumeroRGException extends Exception {

	private static final long serialVersionUID = 1L;

	public NumeroRGException() {
		super("O RG informado é inválido.");
	}
}
