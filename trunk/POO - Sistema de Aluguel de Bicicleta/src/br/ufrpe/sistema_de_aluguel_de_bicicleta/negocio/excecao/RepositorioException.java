package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class RepositorioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RepositorioException(String message){
		super(message);
	}

}
