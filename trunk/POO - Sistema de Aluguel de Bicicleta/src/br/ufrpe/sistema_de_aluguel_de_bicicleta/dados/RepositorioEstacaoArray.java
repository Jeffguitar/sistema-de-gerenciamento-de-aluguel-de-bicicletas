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
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class RepositorioEstacaoArray implements IRepositorioEstacaoArray {

	private List<Estacao> listaEstacao;
	private final String ARQUIVO = "estacao.dat";
	private File arquivoEstacao;

	public RepositorioEstacaoArray() throws ClassNotFoundException,
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

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisEstacao = null;
		ObjectInputStream oisEstacao = null;
		try {
			fisEstacao = new FileInputStream(arquivoEstacao);
			oisEstacao = new ObjectInputStream(fisEstacao);
			while (true) {
				try {
					@SuppressWarnings("unchecked")
					ArrayList<Estacao> estacao = (ArrayList<Estacao>) oisEstacao
							.readObject();
					for (Estacao a : estacao)
						this.cadastrarEstacao(a);
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
		FileOutputStream fosEstacao = null;
		ObjectOutputStream oosEstacao = null;
		try {
			fosEstacao = new FileOutputStream(arquivoEstacao);
			oosEstacao = new ObjectOutputStream(fosEstacao);
			oosEstacao.writeObject(listaEstacao);
			oosEstacao.reset();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na gravação do arquivo "
					+ this.ARQUIVO + ".");
		} finally {
			try {
				fosEstacao.close();
				oosEstacao.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	@Override
	public void cadastrarEstacao(Estacao estacao) throws RepositorioException {
		this.listaEstacao.add(estacao);
		this.gravarArquivo();
	}

	@Override
	public Estacao procurarEstacao(long id) throws EstacaoNaoExisteException {
		int indice = this.obterIndice(id);
		if (indice == -1)
			throw new EstacaoNaoExisteException(id);
		return this.listaEstacao.get(indice);
	}

	@Override
	public void alterarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoNaoExisteException {
		int indice = this.obterIndice(estacao.getCodigo());
		if (indice == -1)
			throw new EstacaoNaoExisteException(estacao.getCodigo());
		this.listaEstacao.set(indice, estacao);
		this.gravarArquivo();
	}

	@Override
	public boolean existe(long id) throws EstacaoNaoExisteException {
		int indice = this.obterIndice(id);

		if (indice != -1)
			return true;
		else
			return false;
	}

	@Override
	public boolean excluirEstacao(long id) throws RepositorioException,
			EstacaoNaoExisteException {
		int indice = this.obterIndice(id);
		if (indice != -1) {
			this.listaEstacao.remove(indice);
			this.gravarArquivo();
			return true;
		} else

			throw new EstacaoNaoExisteException(id);
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