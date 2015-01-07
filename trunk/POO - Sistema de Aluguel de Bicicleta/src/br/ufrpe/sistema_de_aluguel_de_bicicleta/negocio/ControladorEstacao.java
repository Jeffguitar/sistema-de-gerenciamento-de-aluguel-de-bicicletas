package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioEstacaoArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Bicicleta;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class ControladorEstacao {
	private RepositorioEstacaoArray repositorio;
	private static long idEstacao = 1;

	public ControladorEstacao() throws ClassNotFoundException,
			RepositorioException {
		this.repositorio = new RepositorioEstacaoArray();
	}

	public void cadastrar(Estacao estacao, int quantidadeBicicletas)
			throws RepositorioException, EstacaoExistenteException,
			EstacaoNaoExisteException {
		estacao.setCodigo(ControladorEstacao.idEstacao);
		boolean resposta = this.existe(estacao.getCodigo());

		if (resposta == false && estacao != null) {
			List<Bicicleta> listaBicicleta = new ArrayList<Bicicleta>();
			for (int i = 0; i < quantidadeBicicletas; i++) {
				listaBicicleta.add(i, new Bicicleta(i + 1, false));
			}
			estacao.setBicicleta(listaBicicleta);
			repositorio.cadastrarEstacao(estacao);
			ControladorEstacao.idEstacao++;
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

	public boolean existe(long id) throws EstacaoNaoExisteException {
		return this.repositorio.existe(id);
	}

	public void excluir(long id) throws RepositorioException,
			EstacaoNaoExisteException {
		this.repositorio.excluirEstacao(id);
	}
}
