package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AluguelExistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public AluguelExistenteException(String mensagem) {
		super(mensagem);
	}

}
