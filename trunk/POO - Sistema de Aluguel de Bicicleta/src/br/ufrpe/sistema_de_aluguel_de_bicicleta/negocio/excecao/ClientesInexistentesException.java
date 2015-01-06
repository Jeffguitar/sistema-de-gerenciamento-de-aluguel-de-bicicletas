package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class ClientesInexistentesException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClientesInexistentesException() {
		super("Não existem clientes cadastrados no sistema!");
	}
}
