package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AdministradorInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdministradorInexistenteException(String cpf) {
		super("O Administrador com o CPF \"" + cpf + "\" não foi cadastrado.");
	}
}
