package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;

public class RepositorioAluguelArray {
	private List<Aluguel> listaAluguel;
	private static RepositorioAluguelArray repositorio;

	private RepositorioAluguelArray() {
		this.listaAluguel = new ArrayList<Aluguel>();
	}

	public static RepositorioAluguelArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioAluguelArray();
		}
		return repositorio;
	}

	public void cadastrarAluguel(Aluguel aluguel) {
		this.listaAluguel.add(aluguel);
	}

	public Aluguel procurarAluguel(String cpf) {
		int indice = this.obterIndice(cpf);
		return this.listaAluguel.get(indice);

	}

	public void alterarAluguel(Aluguel aluguel) {
		int indice = this.obterIndice(aluguel.getCliente().getCpf());
		// precisa do índice pra setar na posição correta
		this.listaAluguel.set(indice, aluguel);

	}

	public boolean existe(String cpf) {
		boolean existe = false;
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirAluguel(String cpf) {
		int indice = this.obterIndice(cpf);
		// preicsa usar um try, catch, informando que a conta não existe
		if (indice != -1) {
			this.listaAluguel.remove(indice);
			return true;
		}
		return false;
	}

	private int obterIndice(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.listaAluguel.size(); i++) {
			if (this.listaAluguel.get(i).getCliente().getCpf().equals(cpf)) {
				indice = i;
			}
			// tratar um exceção do tipo se a conta não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}

	// public boolean isAlugada(long codigoEstacao, int codigoBicicleta) {
	// Estacao estacaoEncontrada = this.procurarEstacao(codigoEstacao);
	// int indiceBicicleta = this.procurarIndiceBicicletaEstacao(
	// codigoEstacao, codigoBicicleta);
	//
	// if (indiceBicicleta > 0 && indiceBicicleta <
	// this.estacao[this.proxima].getBicicleta().length ) {
	// if (estacaoEncontrada.getBicicleta()[indiceBicicleta].getAlugou() ==
	// false) {
	// return false; // retornará false se a bicicletar estiver disponivel.
	// }
	// }
	// return true; // retornará true se a bicicletar estiver indisponivel.
	// }

	// public Aluguel procurarAluguelPorNome(String nome) {
	// Aluguel retornoBusca = null;
	//
	// for (int i = 0; i < this.listaAluguel.size(); i++) {
	// if (this.listaAluguel.get(i).getCliente().getNome().equals(nome)) {
	// retornoBusca = this.listaAluguel.get(i);
	// }
	// }
	// return retornoBusca; // retorna NULL se não encontrar o listaAluguel
	// }

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

	// private void cadastrarBicicletaEstacao() { // Posso implementar na
	// fachada,
	// // antes de montar o obj
	// // 'estacao'
	//
	// for (int i = 0; i < this.estacao[i].getBicicleta().length; i++) {
	// this.estacao[this.proxima].getBicicleta()[i].setCodigo(i + 1);
	// this.estacao[this.proxima].getBicicleta()[i].setAlugou(false);
	// }
	// }
	//
	// public void cadastrarEstação(Estacao estacao) {
	//
	// if ((this.procurarEstacao(estacao.getCodigo()) == null)
	// && this.procurarPeloIndice(estacao.getCodigo()) != this.proxima) {
	// this.estacao[this.proxima] = estacao;
	//
	// }
	// if (this.proxima == this.estacao.length) {
	// this.duplicaArrayEstacao();
	// }
	// this.cadastrarBicicletaEstacao();
	// this.proxima += 1;
	// }

}
