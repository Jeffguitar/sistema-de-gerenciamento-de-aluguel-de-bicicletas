package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AluguelInativoInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AluguelInativoInexistenteException() {
		super("N�o existem alugu�is finalizados (ou que foram feitas devolu��es).");
	}

}
