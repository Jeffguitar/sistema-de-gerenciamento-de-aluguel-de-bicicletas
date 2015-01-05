package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class BicicletaIndisponivelException extends Exception {

	public BicicletaIndisponivelException(Long id) {
		super("A bicicleta com o id \"" + id + "\" está indisponível.");
	}
}
