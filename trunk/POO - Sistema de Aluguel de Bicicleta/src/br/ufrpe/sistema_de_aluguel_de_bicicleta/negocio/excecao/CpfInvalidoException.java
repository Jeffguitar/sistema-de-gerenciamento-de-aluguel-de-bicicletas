package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class CpfInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CpfInvalidoException() {
		super("O CPF informado é inválido.");
	}
}
