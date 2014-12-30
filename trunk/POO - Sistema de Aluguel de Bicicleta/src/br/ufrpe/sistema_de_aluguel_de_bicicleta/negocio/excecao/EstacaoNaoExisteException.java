package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class EstacaoNaoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public EstacaoNaoExisteException(Long id) {
		super("A estaçao com o id \"" + id + "\" não existe.");
	}

}
