package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class ClienteNaoAlugouBicicletaException extends Exception {
	public ClienteNaoAlugouBicicletaException(String cpf, long idEstacao,
			long idBicicleta) {
		super("O cliente com CPF \"" + cpf + "não alugou a bicicleta"
				+ idBicicleta + "da estação" + idEstacao + ".");
	}
}
