package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AluguelComMultaInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public AluguelComMultaInexistenteException() {
		super("N�o existem alugu�is que resultaram em multa!");
	}
}
