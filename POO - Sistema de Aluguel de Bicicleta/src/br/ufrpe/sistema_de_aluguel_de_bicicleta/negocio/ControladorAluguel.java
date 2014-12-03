package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAluguelArray;

public class ControladorAluguel {
	private RepositorioAluguelArray repositorio;

	public ControladorAluguel() {
		this.repositorio = RepositorioAluguelArray.getInstance();
	}
}
