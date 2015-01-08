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
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class RepositorioAluguelArray implements IRepositorioAluguelArray {
	private List<Aluguel> listaAluguel;
	private final String ARQUIVO = "aluguel.dat";
	private File arquivoAluguel;

	public RepositorioAluguelArray() throws ClassNotFoundException,
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

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisAluguel = null;
		ObjectInputStream oisAluguel = null;
		try {
			fisAluguel = new FileInputStream(arquivoAluguel);
			oisAluguel = new ObjectInputStream(fisAluguel);
			while (true) {
				try {
					@SuppressWarnings("unchecked")
					ArrayList<Aluguel> aluguel = (ArrayList<Aluguel>) oisAluguel
							.readObject();
					for (Aluguel a : aluguel)
						this.cadastrarAluguel(a);
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
		FileOutputStream fosAluguel = null;
		ObjectOutputStream oosAluguel = null;
		try {
			fosAluguel = new FileOutputStream(arquivoAluguel);
			oosAluguel = new ObjectOutputStream(fosAluguel);
			oosAluguel.writeObject(listaAluguel);
			oosAluguel.reset();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na gravação do arquivo "
					+ this.ARQUIVO + ".");
		} finally {
			try {
				fosAluguel.close();
				oosAluguel.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	@Override
	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException {
		this.listaAluguel.add(aluguel);
		this.gravarArquivo();
	}

	@Override
	public Aluguel procurarAluguel(String cpf, long idBicicleta)
			throws AluguelInexistenteException {
		int indice = this.obterIndice(cpf, idBicicleta);
		if (indice != -1)
			return this.listaAluguel.get(indice);
		else
			return null;

	}

	@Override
	public Aluguel procurarAluguelFinalizado(String cpf, long idBicicleta)
			throws AluguelInexistenteException {
		int indice = this.obterIndiceAluguelFinalizado(cpf, idBicicleta);
		if (indice != -1)
			return this.listaAluguel.get(indice);
		else
			throw new AluguelInexistenteException("Aluguel Inexistente!");

	}

	@Override
	public Aluguel procurarAluguel(long id) throws AluguelInexistenteException {
		int indice = this.obterIndice(id);
		if (indice != -1) {
			return this.listaAluguel.get(indice);
		} else
			throw new AluguelInexistenteException("Aluguel Inexistente!");

	}

	@Override
	public boolean existeAluguel(String cpf, long idBicicleta) {
		boolean existe = false;
		int indice = this.obterIndice(cpf, idBicicleta);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	@Override
	public void excluirAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException {
		int indice = this.obterIndice(cpf, idBicicleta);
		if (indice != -1) {
			this.listaAluguel.remove(indice);
			this.gravarArquivo();
		} else
			throw new AluguelInexistenteException("Aluguel não existe!");
	}

	@Override
	public List<Aluguel> exibirALuguelAtivo() {
		List<Aluguel> aluguelAtivo = new ArrayList<Aluguel>();
		for (int i = 0; i < listaAluguel.size(); i++) {
			if (listaAluguel.get(i).getDataDevolucao() == null) {
				aluguelAtivo.add(listaAluguel.get(i));
			}
		}
		return aluguelAtivo;
	}

	@Override
	public List<Aluguel> exibirALuguelFinalizadoEstacao() {
		List<Aluguel> aluguelFinalizado = new ArrayList<Aluguel>();
		for (int i = 0; i < listaAluguel.size(); i++) {
			if (listaAluguel.get(i).getDataDevolucao() != null) {
				aluguelFinalizado.add(listaAluguel.get(i));
			}
		}
		return aluguelFinalizado;
	}

	@Override
	public List<Aluguel> exibirALuguelComMulta() {
		List<Aluguel> aluguelFinalizado = new ArrayList<Aluguel>();
		for (int i = 0; i < listaAluguel.size(); i++) {
			if (listaAluguel.get(i).getValor() > 0.0) {
				aluguelFinalizado.add(listaAluguel.get(i));
			}
		}
		return aluguelFinalizado;
	}

	private int obterIndice(long id) {
		int indice = -1;

		for (int i = 0; i < this.listaAluguel.size(); i++) {
			if (this.listaAluguel.get(i).getId() == id) {
				indice = i;
			}
		}
		return indice;
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
		}
		return indice;
	}

	private int obterIndiceAluguelFinalizado(String cpf, long idBicicleta) {
		int indice = -1;

		for (int i = 0; i < this.listaAluguel.size(); i++) {
			if (this.listaAluguel.get(i).getCliente().getCpf().equals(cpf)
					&& this.listaAluguel
							.get(i)
							.getEstacao()
							.getBicicleta()
							.get(this.listaAluguel.get(i).getEstacao()
									.retornaIndiceBicicleta(idBicicleta))
							.getAlugou() == false
					&& listaAluguel.get(i).getDataDevolucao() != null) {
				indice = i;
			}
		}
		return indice;
	}

	@Override
	public void alterarAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException {
		Aluguel aluguel = this.procurarAluguel(cpf, idBicicleta);
		if (aluguel != null) {
			int indice = this.obterIndice(aluguel.getCliente().getId());
			this.listaAluguel.set(indice, aluguel);
			this.gravarArquivo();
		} else
			throw new AluguelInexistenteException("Aluguel não existe!");
	}
}
