package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class SenhaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
