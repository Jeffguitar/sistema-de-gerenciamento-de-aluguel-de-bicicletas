package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public interface IRepositorioEstacaoArray {

	public void cadastrarEstacao(Estacao estacao) throws RepositorioException;

	public Estacao procurarEstacao(long id) throws EstacaoNaoExisteException;

	public void alterarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoNaoExisteException;

	public boolean existe(long id) throws EstacaoNaoExisteException;

	public boolean excluirEstacao(long id) throws RepositorioException,
			EstacaoNaoExisteException;
}
