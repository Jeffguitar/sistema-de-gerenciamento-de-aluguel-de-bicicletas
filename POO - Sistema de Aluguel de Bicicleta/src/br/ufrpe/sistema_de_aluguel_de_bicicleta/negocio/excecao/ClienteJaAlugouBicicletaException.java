package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class ClienteJaAlugouBicicletaException extends Exception {
	public ClienteJaAlugouBicicletaException(String cpf, long idEstacao,
			long idBicicleta) {
		super("O cliente com CPF \"" + cpf + "j� alugou a bicicleta"
				+ idBicicleta + "da esta��o" + idEstacao + ".");
	}
}
