package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class NumeroCPFException extends Exception {

	private static final long serialVersionUID = 1L;

	public NumeroCPFException() {
		super("O RG informado é inválido.");
	}
}
