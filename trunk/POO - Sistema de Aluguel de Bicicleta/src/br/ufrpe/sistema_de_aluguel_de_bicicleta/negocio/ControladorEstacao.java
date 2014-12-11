package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioEstacaoArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class ControladorEstacao {
	private RepositorioEstacaoArray repositorio;

	public ControladorEstacao() {
		this.repositorio = RepositorioEstacaoArray.getInstance();
	}

	public void cadastrar(Estacao estacao) {
		boolean resposta = this.existe(estacao.getCodigo());

		if (resposta == false && estacao != null)
			repositorio.cadastrarEstacao(estacao);
	}

	public Estacao procurar(long id) {
		return this.repositorio.procurarEstacao(id);
	}

	public void alterar(Estacao estacao) {
		boolean resposta = this.existe(estacao.getCodigo());

		if (resposta == false && estacao != null)
			this.repositorio.alterarEstacao(estacao);
	}

	public boolean existe(long id) {
		return this.repositorio.existe(id);
	}

	public void excluir(long id) {
		this.repositorio.excluirEstacao(id);
	}
}
