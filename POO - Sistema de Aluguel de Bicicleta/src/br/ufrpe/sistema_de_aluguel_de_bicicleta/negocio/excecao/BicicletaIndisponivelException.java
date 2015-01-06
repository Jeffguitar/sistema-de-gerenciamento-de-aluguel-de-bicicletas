package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class BicicletaIndisponivelException extends Exception {
	private static final long serialVersionUID = 1L;

	public BicicletaIndisponivelException(Long id) {
		super("A bicicleta com o id \"" + id + "\" está indisponível.");
	}
}
