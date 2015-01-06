package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class SexoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public SexoInvalidoException() {
		super("Sexo inválido.");
	}
}