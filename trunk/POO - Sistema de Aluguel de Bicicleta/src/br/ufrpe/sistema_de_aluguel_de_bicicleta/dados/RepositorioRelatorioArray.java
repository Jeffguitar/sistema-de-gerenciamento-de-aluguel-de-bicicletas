package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.RelatorioCliente;

public class RepositorioRelatorioArray {
	private RelatorioCliente relatorio[];
	private int proximo;
	private static RepositorioRelatorioArray repositorio;

	private RepositorioRelatorioArray(int tamanho) {
		this.relatorio = new RelatorioCliente[tamanho];
		this.proximo = 0;
	}

	public static RepositorioRelatorioArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioRelatorioArray(100);
		}
		return repositorio;
	}

	private int procurarPeloIndice(String nome) {
		int indice = -1;

		for (int i = 0; i < this.relatorio.length; i++) {
			if (this.relatorio[i].getAluguel().getCliente().getNome() == (nome)) {
				indice = i;
			}
		}
		return indice; // Retorna -1 se não encontrou
	}

	private int procurarIndiceRelatorioCpf(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.relatorio.length; i++) {
			if (this.relatorio[i].getAluguel().getCliente().getCpf() == (cpf)) {
				indice = i;
			}
		}
		return indice; // Retorna -1 se não encontrou
	}

	private void duplicaArrayRelatorio() {
		if (this.relatorio != null && this.relatorio.length > 0) {
			RelatorioCliente arrayUsuarioDobrado[] = new RelatorioCliente[this.relatorio.length * 2];
			for (int i = 0; i < this.relatorio.length; i++) {
				arrayUsuarioDobrado[i] = this.relatorio[i];
			}
			this.relatorio = arrayUsuarioDobrado;
		}
	}

	public void cadastrarRelatorio(RelatorioCliente relatorio) {

		if ((this.procurarRelatorio(relatorio.getAluguel().getCliente()
				.getNome()) == null)
				&& this.procurarPeloIndice(relatorio.getAluguel().getCliente()
						.getNome()) != this.proximo) {
			this.relatorio[this.proximo] = relatorio;
		}
		if (this.proximo == this.relatorio.length) {
			this.duplicaArrayRelatorio();
		}
	}

	public RelatorioCliente procurarRelatorio(String nome) {
		RelatorioCliente retorno = null;

		for (int i = 0; i < this.relatorio.length; i++) {
			if (this.relatorio[i].getAluguel().getCliente().getNome()
					.equals(nome)) {
				retorno = this.relatorio[i];
			}
		}
		return retorno;
	}
	
	

	public void excluirRelatorio(String cpf) {
		if (this.procurarIndiceRelatorioCpf(cpf) != this.proximo) {
			this.relatorio[this.procurarPeloIndice(cpf)] = this.relatorio[this.proximo - 1];
			this.relatorio[this.proximo - 1] = null;
			this.proximo = this.proximo - 1;
		}
	}
}
