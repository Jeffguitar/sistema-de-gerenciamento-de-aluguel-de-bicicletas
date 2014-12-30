package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class EstacaoExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public EstacaoExistenteException(String message) {
		super(message);
	}
}
