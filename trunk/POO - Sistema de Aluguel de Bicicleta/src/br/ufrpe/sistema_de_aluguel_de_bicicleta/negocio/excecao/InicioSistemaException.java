package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao;

public class InicioSistemaException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public InicioSistemaException(){
		super("Erro ao iniciar o sistema!");
	}

}
