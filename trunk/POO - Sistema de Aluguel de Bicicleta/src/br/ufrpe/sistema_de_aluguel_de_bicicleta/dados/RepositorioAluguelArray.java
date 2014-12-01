package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;

public class RepositorioAluguelArray {
	private Aluguel aluguel[];
	private int proxima;
	private static RepositorioAluguelArray repositorio;

	private RepositorioAluguelArray(int tamanho) {
		this.aluguel = new Aluguel[tamanho];
		this.proxima = 0;
	}

	public static RepositorioAluguelArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioAluguelArray(100);
		}
		return repositorio;
	}

	private int procurarIndicePeloCpf(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.aluguel.length; i++) {
			if (this.aluguel[i].getCliente().getCpf().equals(cpf)) {
				indice = i;
			}
		}
		return indice; // Retorna -1 se não encontrou
	}

	public Aluguel procurarAluguelPorNome(String nome) {
		Aluguel retornoBusca = null;

		for (int i = 0; i < this.aluguel.length; i++) {
			if (this.aluguel[i].getCliente().getNome().equals(nome)) {
				retornoBusca = this.aluguel[i];
			}
		}
		return retornoBusca; // retorna NULL se não encontrar o aluguel
	}

	public Aluguel procurarAluguelPorCpf(String cpf) {
		Aluguel retornoBusca = null;

		for (int i = 0; i < this.aluguel.length; i++) {
			if (this.aluguel[i].getCliente().getCpf().equals(cpf)) {
				retornoBusca = this.aluguel[i];
			}
		}
		return retornoBusca; // retorna NULL se não encontrar o aluguel
	}

	private void duplicaArrayAluguel() {

		if (this.aluguel != null && this.aluguel.length > 0) {
			Aluguel arrayEstacaoDobrado[] = new Aluguel[this.aluguel.length * 2];
			for (int i = 0; i < this.aluguel.length; i++) {
				arrayEstacaoDobrado[i] = this.aluguel[i];
			}
			this.aluguel = arrayEstacaoDobrado;
		}
	}

	public void cadastrarEstação(Aluguel aluguel) {

		if ((this.procurarAluguelPorCpf(aluguel.getCliente().getCpf()) == null)
				&& this.procurarIndicePeloCpf(aluguel.getCliente().getCpf()) != this.proxima) {
			this.aluguel[this.proxima] = aluguel;

		}
		if (this.proxima == this.aluguel.length) {
			this.duplicaArrayAluguel();
		}
	}

	public void excluirEstacao(String cpf) {

		if (this.procurarIndicePeloCpf(cpf) != this.proxima) {
			this.aluguel[this.procurarIndicePeloCpf(cpf)] = this.aluguel[this.proxima - 1];
			this.aluguel[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}
	}
}
