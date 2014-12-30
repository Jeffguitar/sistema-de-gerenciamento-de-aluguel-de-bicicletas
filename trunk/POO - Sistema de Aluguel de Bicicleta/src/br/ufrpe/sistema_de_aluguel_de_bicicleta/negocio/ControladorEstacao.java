package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioEstacaoArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class ControladorEstacao {
	private RepositorioEstacaoArray repositorio;
	private long idEstacao = 1;

	public ControladorEstacao() throws ClassNotFoundException,
			RepositorioException {
		this.repositorio = RepositorioEstacaoArray.getInstance();
	}

	public void cadastrar(Estacao estacao) throws RepositorioException,
			EstacaoExistenteException {
		boolean resposta = this.existe(estacao.getCodigo());

		if (resposta == false && estacao != null) {
			estacao.setCodigo(this.idEstacao);
			repositorio.cadastrarEstacao(estacao);
			this.idEstacao++;
		} else
			throw new EstacaoExistenteException("A Estação já existe!");
	}

	public Estacao procurar(long id) throws EstacaoNaoExisteException {
		return this.repositorio.procurarEstacao(id);
	}

	public void alterar(Estacao estacao) throws RepositorioException,
			EstacaoNaoExisteException {
		boolean resposta = this.existe(estacao.getCodigo());

		if (resposta == false && estacao != null)
			this.repositorio.alterarEstacao(estacao);
	}

	public boolean existe(long id) {
		return this.repositorio.existe(id);
	}

	public void excluir(long id) throws RepositorioException,
			EstacaoNaoExisteException {
		this.repositorio.excluirEstacao(id);
	}
}
