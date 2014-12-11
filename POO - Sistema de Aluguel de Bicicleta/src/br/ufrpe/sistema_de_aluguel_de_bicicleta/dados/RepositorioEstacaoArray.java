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

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class RepositorioEstacaoArray {

	private List<Estacao> listaEstacao;
	private static RepositorioEstacaoArray repositorio;
	private final String ARQUIVO = "estacao.dat";
	private File arquivoEstacao;

	private RepositorioEstacaoArray() throws ClassNotFoundException,
			RepositorioException {
		try {
			this.listaEstacao = new ArrayList<Estacao>();
			this.arquivoEstacao = new File(this.ARQUIVO);
			this.arquivoEstacao.createNewFile();
			if (this.arquivoEstacao.length() != 0)
				this.lerArquivo();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na abertura do arquivo "
					+ this.ARQUIVO + ".");
		}
	}

	public static RepositorioEstacaoArray getInstance()
			throws ClassNotFoundException, RepositorioException {
		if (repositorio == null) {
			repositorio = new RepositorioEstacaoArray();
		}
		return repositorio;
	}

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisEstacao = null;
		ObjectInputStream oisEstacao = null;
		try {
			fisEstacao = new FileInputStream(arquivoEstacao);
			oisEstacao = new ObjectInputStream(fisEstacao);
			while (true) {
				try {
					Estacao adm = (Estacao) oisEstacao.readObject();
					this.cadastrarEstacao(adm);
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
					+ (new RepositorioEstacaoArray()).getClass()
					+ " não encontrado.");
		} finally {
			try {
				fisEstacao.close();
				oisEstacao.close();
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
			fosRestaurante = new FileOutputStream(arquivoEstacao);
			oosRestaurante = new ObjectOutputStream(fosRestaurante);
			for (Estacao adm : this.listaEstacao)
				oosRestaurante.writeObject(adm);
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

	public void cadastrarEstacao(Estacao estacao) throws RepositorioException {
		this.listaEstacao.add(estacao);
		this.gravarArquivo();
	}

	public Estacao procurarEstacao(long id) {
		int indice = this.obterIndice(id);
		return this.listaEstacao.get(indice);
	}

	public void alterarEstacao(Estacao estacao) throws RepositorioException {
		int indice = this.obterIndice(estacao.getCodigo());
		this.listaEstacao.set(indice, estacao);
		this.gravarArquivo();
	}

	public boolean existe(long id) {
		boolean existe = false;
		int indice = this.obterIndice(id);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirEstacao(long id) throws RepositorioException {
		int indice = this.obterIndice(id);
		// preicsa usar um try, catch, informando que a estação não existe
		if (indice != -1) {
			this.listaEstacao.remove(indice);
			this.gravarArquivo();
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