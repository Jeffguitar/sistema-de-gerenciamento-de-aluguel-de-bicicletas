package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AluguelInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public AluguelInexistenteException(String mensagem) {
		super(mensagem);
	}

}
