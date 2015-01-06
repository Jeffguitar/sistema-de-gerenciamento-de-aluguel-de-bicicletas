package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class NumeroBicicletaInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public NumeroBicicletaInvalidoException() {
		super("Quantidade informada é inválida.");
	}
}
