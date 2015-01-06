package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AluguelAtivoInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AluguelAtivoInexistenteException() {
		super("N�o existem alugu�is (ou aluguel) ativo no momento.");
	}

}
