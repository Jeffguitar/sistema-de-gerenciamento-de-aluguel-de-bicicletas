package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class ClienteNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClienteNaoCadastradoException(String cpf) {
		super("O cliente com o CPF \"" + cpf + "\" não foi cadastrado.");
	}
}
