package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class EstacaoNaoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public EstacaoNaoExisteException(Long id) {
		super("A esta�ao com o id \"" + id + "\" n�o existe.");
	}

}
