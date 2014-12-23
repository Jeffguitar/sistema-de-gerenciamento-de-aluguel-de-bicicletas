package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;

public class RepositorioAluguelArray {
	private List<Aluguel> listaAluguel;
	private static RepositorioAluguelArray repositorio;
	private final String ARQUIVO = "aluguel.dat";
	private File arquivoAluguel;

	private RepositorioAluguelArray() throws ClassNotFoundException,
			RepositorioException {
		try {
			this.listaAluguel = new ArrayList<Aluguel>();
			this.arquivoAluguel = new File(this.ARQUIVO);
			this.arquivoAluguel.createNewFile();
			if (this.arquivoAluguel.length() != 0)
				this.lerArquivo();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na abertura do arquivo "
					+ this.ARQUIVO + ".");
		}
	}

	public static RepositorioAluguelArray getInstance()
			throws ClassNotFoundException, RepositorioException {
		if (repositorio == null) {
			repositorio = new RepositorioAluguelArray();
		}
		return repositorio;
	}

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisAluguel = null;
		ObjectInputStream oisAluguel = null;
		try {
			fisAluguel = new FileInputStream(arquivoAluguel);
			oisAluguel = new ObjectInputStream(fisAluguel);
			while (true) {
				try {
					Aluguel aluguel = (Aluguel) oisAluguel.readObject();
					this.cadastrarAluguel(aluguel);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na leitura do arquivo "
					+ this.ARQUIVO + ".");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na leitura do objeto. Objeto "
					+ (new RepositorioAluguelArray()).getClass()
					+ " não encontrado.");
		} finally {
			try {
				fisAluguel.close();
				oisAluguel.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	private void gravarArquivo() throws RepositorioException {
		FileOutputStream fosRestaurante = null;
		ObjectOutputStream oosRestaurante = null;
		try {
			fosRestaurante = new FileOutputStream(arquivoAluguel);
			oosRestaurante = new ObjectOutputStream(fosRestaurante);
			for (Aluguel aluguel : this.listaAluguel)
				oosRestaurante.writeObject(aluguel);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na gravação do arquivo "
					+ this.ARQUIVO + ".");
		} finally {
			try {
				fosRestaurante.close();
				oosRestaurante.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException {
		this.listaAluguel.add(aluguel);
		this.gravarArquivo();
	}

	public Aluguel procurarAluguel(String cpf, long idBicicleta) {
		int indice = this.obterIndice(cpf, idBicicleta);
		return this.listaAluguel.get(indice);

	}

	public Aluguel procurarAluguel(long id) {
		int indice = this.obterIndice(id);
		if (indice != -1) {
			return this.listaAluguel.get(indice);
		}
		return null;

	}

	public void alterarAluguel(Aluguel aluguel) throws RepositorioException {
		int indice = this.obterIndice(aluguel.getCliente().getId());
		// precisa do índice pra setar na posição correta
		this.listaAluguel.set(indice, aluguel);
		this.gravarArquivo();
	}

	public boolean existe(String cpf, long idBicicleta) {
		boolean existe = false;
		int indice = this.obterIndice(cpf, idBicicleta);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirAluguel(String cpf, long idBicicleta)
			throws RepositorioException {
		int indice = this.obterIndice(cpf, idBicicleta);
		// preicsa usar um try, catch, informando que a conta não existe
		if (indice != -1) {
			this.listaAluguel.remove(indice);
			this.gravarArquivo();
			return true;
		}
		return false;
	}

	private int obterIndice(long id) {
		int indice = -1;

		for (int i = 0; i < this.listaAluguel.size(); i++) {
			if (this.listaAluguel.get(i).getId() == id) {
				indice = i;
			}
			// tratar um exceção do tipo se a conta não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}



	private int obterIndice(String cpf, long idBicicleta) {
		int indice = -1;

		for (int i = 0; i < this.listaAluguel.size(); i++) {
			if (this.listaAluguel.get(i).getCliente().getCpf().equals(cpf)
					&& this.listaAluguel
							.get(i)
							.getEstacao()
							.getBicicleta()
							.get(this.listaAluguel.get(i).getEstacao()
									.retornaIndiceBicicleta(idBicicleta))
							.getAlugou() == true) {
				indice = i;
			}
			// tratar um exceção do tipo se a conta não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}
	
	// private int obterIndice(String cpf) {
	// int indice = -1;
	//
	// for (int i = 0; i < this.listaAluguel.size(); i++) {
	// if (this.listaAluguel.get(i).getCliente().getCpf().equals(cpf)) {
	// indice = i;
	// }
	// // tratar um exceção do tipo se a conta não foi encontrada
	// }
	// return indice; // Retorna -1 se não encontrou
	// }

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

	//
	// public void alugarBicicleta(long codigoEstacao, int codigoBicicleta,
	// Cliente cliente) { boolean retorno = this.isAlugada(codigoEstacao,
	// codigoBicicleta); ; int indiceEstacao =
	// this.procurarPeloIndice(codigoEstacao); int indiceBicicletaEstacao =
	// this.procurarIndiceBicicletaEstacao( codigoEstacao, codigoBicicleta);
	//
	// if (retorno == false && indiceEstacao != -1 && indiceBicicletaEstacao !=
	// -1) { this.estacao[indiceEstacao].getBicicleta()[indiceBicicletaEstacao]
	// .setCliente(cliente); // Insere um cliente em uma // determinada
	// bicicleta
	// this.estacao[indiceEstacao].getBicicleta()[indiceBicicletaEstacao]
	// .setAlugou(true); // Informa que a bicicleta encontra-se // alugada //
	// Falta implementar o armazenamento da hora } }
	//

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
