package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class RepositorioEstacaoArray {

	private Estacao estacao[];
	private int proxima;

	public RepositorioEstacaoArray(int tamanho) {
		this.estacao = new Estacao[tamanho];
		this.proxima = 0;
	}

	private int procurarPeloIndice(long codigo) {
		int indice = -1;

		for (int i = 0; i < this.estacao.length; i++) {
			if (this.estacao[i].getCodigo() == (codigo)) {
				indice = i;
			}
		}
		return indice; // Retorna -1 se não encontrou
	}

	public Estacao procurarEstacao(long codigo) {
		Estacao retornoBusca = null;

		for (int i = 0; i < this.estacao.length; i++) {
			if (this.estacao[i].getCodigo() == (codigo)) {
				retornoBusca = this.estacao[i];
			}
		}
		return retornoBusca; // retorna NULL se não encontrar a estação
	}

	private void duplicaArrayEstacao() {

		if (this.estacao != null && this.estacao.length > 0) {
			Estacao arrayEstacaoDobrado[] = new Estacao[this.estacao.length * 2];
			for (int i = 0; i < this.estacao.length; i++) {
				arrayEstacaoDobrado[i] = this.estacao[i];
			}
			this.estacao = arrayEstacaoDobrado;
		}
	}

	private int procurarIndiceBicicletaEstacao(long codigoEstacao,
			int codigoBicicleta) {
		int indiceBicicleta = -1;

		for (int i = 0; i < this.estacao[i].getBicicleta().length; i++) {
			if (this.estacao[this.procurarPeloIndice(codigoEstacao)]
					.getBicicleta()[i].getCodigo() == codigoBicicleta) {
				indiceBicicleta = i;
			}
		}
		return indiceBicicleta;
	}

	/*
	 * private boolean isAlugada(long codigoEstacao, int codigoBicicleta) {
	 * 
	 * if
	 * (this.estacao[this.procurarPeloIndice(codigoEstacao)].getBicicleta()[this
	 * .procurarIndiceBicicletaEstacao(codigoEstacao, codigoBicicleta)]
	 * .getAlugou() == false) { return false; } return true; }
	 */

	/*
	 * public void alugarBicicleta(long codigoEstacao, int codigoBicicleta,
	 * Cliente cliente) { boolean retorno = this.isAlugada(codigoEstacao,
	 * codigoBicicleta); ; int indiceEstacao =
	 * this.procurarPeloIndice(codigoEstacao); int indiceBicicletaEstacao =
	 * this.procurarIndiceBicicletaEstacao( codigoEstacao, codigoBicicleta);
	 * 
	 * if (retorno == false && indiceEstacao != -1 && indiceBicicletaEstacao !=
	 * -1) { this.estacao[indiceEstacao].getBicicleta()[indiceBicicletaEstacao]
	 * .setCliente(cliente); // Insere um cliente em uma // determinada
	 * bicicleta
	 * this.estacao[indiceEstacao].getBicicleta()[indiceBicicletaEstacao]
	 * .setAlugou(true); // Informa que a bicicleta encontra-se // alugada //
	 * Falta implementar o armazenamento da hora } }
	 */

	private void cadastrarBicicletaEstacao() {

		for (int j = 0; j < this.estacao.length; j++) {
			for (int i = 0; i < this.estacao[i].getBicicleta().length; i++) {
				this.estacao[j].getBicicleta()[i].setCodigo(i + 1);
				this.estacao[j].getBicicleta()[i].setAlugou(false);
			}
		}
	}

	public void cadastrarEstação(Estacao estacao) {

		if ((this.procurarEstacao(estacao.getCodigo()) == null)
				&& this.procurarPeloIndice(estacao.getCodigo()) < this.proxima) {
			this.estacao[this.proxima] = estacao;
			this.proxima += 1;
		}
		if (this.proxima == this.estacao.length) {
			this.duplicaArrayEstacao();
		}
		this.cadastrarBicicletaEstacao();
	}

	public void excluirEstacao(long codigo) {

		if (this.procurarPeloIndice(codigo) != -1
				&& this.procurarPeloIndice(codigo) != this.proxima) {
			this.estacao[this.procurarPeloIndice(codigo)] = this.estacao[this.proxima - 1];
			this.estacao[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}
	}
}