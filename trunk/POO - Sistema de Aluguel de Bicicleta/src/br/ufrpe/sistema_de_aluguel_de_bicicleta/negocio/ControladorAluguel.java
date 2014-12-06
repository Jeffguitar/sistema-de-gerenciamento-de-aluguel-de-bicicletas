package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAluguelArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;

public class ControladorAluguel {
	private RepositorioAluguelArray repositorio;

	public ControladorAluguel() {
		this.repositorio = RepositorioAluguelArray.getInstance();
	}

	public void cadastrar(Aluguel aluguel) {
		boolean resposta = this.existe(aluguel.getCliente().getCpf());

		if (resposta == false && aluguel != null)
			repositorio.cadastrarAluguel(aluguel);
	}

	public void procurar(String cpf) {
		this.repositorio.procurarAluguel(cpf);
	}

	public void alterar(Aluguel aluguel) {
		boolean resposta = this.existe(aluguel.getCliente().getCpf());

		if (resposta == false && aluguel != null)
			this.repositorio.alterarAluguel(aluguel);
	}

	public boolean existe(String cpf) {

		return this.repositorio.existe(cpf);
	}

	public void excluir(String cpf) {
		this.repositorio.excluirAluguel(cpf);
	}
}
