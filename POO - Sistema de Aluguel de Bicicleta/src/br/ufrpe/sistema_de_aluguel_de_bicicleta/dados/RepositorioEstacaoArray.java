package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class RepositorioEstacaoArray {

	private List<Estacao> listaEstacao;
	private static RepositorioEstacaoArray repositorio;

	private RepositorioEstacaoArray() {
		this.listaEstacao = new ArrayList<Estacao>();
	}

	public static RepositorioEstacaoArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioEstacaoArray();
		}
		return repositorio;
	}

	public void cadastrarEstacao(Estacao estacao) {
		this.listaEstacao.add(estacao);
	}

	public Estacao procurarEstacao(long id) {
		int indice = this.obterIndice(id);
		return this.listaEstacao.get(indice);
	}

	public void alterarEstacao(Estacao estacao) {
		int indice = this.obterIndice(estacao.getCodigo());
		this.listaEstacao.set(indice, estacao);
	}

	public boolean existe(long id) {
		boolean existe = false;
		int indice = this.obterIndice(id);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirEstacao(long id) {
		int indice = this.obterIndice(id);
		// preicsa usar um try, catch, informando que a estação não existe
		if (indice != -1) {
			this.listaEstacao.remove(indice);
			return true;
		}
		return false;
	}

	private int obterIndice(long id) {
		int indice = -1;

		for (int i = 0; i < this.listaEstacao.size(); i++) {
			if (this.listaEstacao.get(i).getCodigo() == id) {
				indice = i;
			}
			// tratar um exceção do tipo se o obj não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}

}