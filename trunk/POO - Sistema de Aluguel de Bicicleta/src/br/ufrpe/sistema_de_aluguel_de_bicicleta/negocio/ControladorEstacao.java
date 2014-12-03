package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioEstacaoArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class ControladorEstacao {
	private RepositorioEstacaoArray repositorio;

	public ControladorEstacao() {
		this.repositorio = RepositorioEstacaoArray.getInstance();
	}

	public void cadastrar(Estacao estacao) {
		if (estacao != null
				&& this.procurarEstacao(estacao.getCodigo()) == null) {
			this.repositorio.cadastrarEstação(estacao);
		}
	}

	/*
	 * public void alugarBicicleta(long codigoEstacao, int codigoBicicleta,
	 * Cliente cliente) { if (codigoEstacao != 0 && codigoBicicleta != 0 &&
	 * cliente != null) { this.repositorio.alugarBicicleta(codigoEstacao,
	 * codigoBicicleta, cliente); } }
	 */

	public Estacao procurarEstacao(long codigo) {
		Estacao retorno = null;
		if (codigo != 0) {
			retorno = this.repositorio.procurarEstacao(codigo);
		}
		return retorno;
	}

	public void remover(long codigo) {
		if (codigo != 0) {
			this.repositorio.excluirEstacao(codigo);
		}
	}

	public boolean isAlugada(long codigoEstacao, int codigoBicicleta) {
		return this.repositorio.isAlugada(codigoEstacao, codigoBicicleta);
	}
}
