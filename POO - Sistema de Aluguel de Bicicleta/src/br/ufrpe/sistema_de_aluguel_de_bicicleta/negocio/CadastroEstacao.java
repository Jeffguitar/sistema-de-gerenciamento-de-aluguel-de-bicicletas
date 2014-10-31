package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioEstacaoArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class CadastroEstacao {
	private RepositorioEstacaoArray repositorioA;

	public CadastroEstacao() {
		this.repositorioA = new RepositorioEstacaoArray(100);
	}

	public RepositorioEstacaoArray getRepositorioA() {
		return repositorioA;
	}

	public void cadastrar(Estacao estacao) {
		if (estacao != null
				&& this.procurarEstacao(estacao.getCodigo()) == null) {
			this.repositorioA.cadastrarEstação(estacao);
		}
	}

	public void alugarBicicleta(long codigoEstacao, int codigoBicicleta,
			Cliente cliente) {
		if (codigoEstacao != 0 && codigoBicicleta != 0 && cliente != null) {
			this.repositorioA.alugarBicicleta(codigoEstacao, codigoBicicleta,
					cliente);
		}
	}

	public Estacao procurarEstacao(long codigo) {
		Estacao retorno = null;
		if (codigo != 0) {
			retorno = this.repositorioA.procurarEstacao(codigo);
		}
		return retorno;
	}

	public void remover(long codigo) {
		if (codigo != 0) {
			this.repositorioA.excluirEstacao(codigo);
		}
	}
}
