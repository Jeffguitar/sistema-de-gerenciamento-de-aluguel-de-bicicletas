package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class AdministradorNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdministradorNaoCadastradoException(String mensagem) {
		super(mensagem);
	}
}
