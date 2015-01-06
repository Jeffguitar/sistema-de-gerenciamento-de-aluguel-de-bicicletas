package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AluguelInativoInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AluguelInativoInexistenteException() {
		super("Não existem aluguéis finalizados (ou que foram feitas devoluções).");
	}

}
