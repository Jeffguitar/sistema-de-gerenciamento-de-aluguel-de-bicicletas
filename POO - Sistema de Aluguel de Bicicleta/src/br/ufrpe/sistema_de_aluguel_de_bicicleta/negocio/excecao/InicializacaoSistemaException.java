package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class InicializacaoSistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	public InicializacaoSistemaException() {
		super("Erro no repositório. O sistema não pode ser iniciado.");
	}
}