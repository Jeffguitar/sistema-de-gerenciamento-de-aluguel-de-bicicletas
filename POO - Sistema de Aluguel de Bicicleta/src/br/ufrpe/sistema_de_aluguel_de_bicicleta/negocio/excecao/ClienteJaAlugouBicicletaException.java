package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class ClienteJaAlugouBicicletaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ClienteJaAlugouBicicletaException(String cpf, long idEstacao,
			long idBicicleta) {
		super("O cliente com CPF \"" + cpf + "já alugou a bicicleta"
				+ idBicicleta + "da estação" + idEstacao + ".");
	}
}
