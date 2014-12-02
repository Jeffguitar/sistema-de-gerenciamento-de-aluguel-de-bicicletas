package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAluguelArray;

public class CadastroAluguel {
	private RepositorioAluguelArray repositorio;

	public CadastroAluguel() {
		this.repositorio = RepositorioAluguelArray.getInstance();
	}
}
