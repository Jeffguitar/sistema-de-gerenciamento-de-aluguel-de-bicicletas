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

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class RepositorioClienteArray {

	private List<Cliente> listaCliente;
	private static RepositorioClienteArray repositorio;
	private final String ARQUIVO = "clientes.dat";
	private File arquivoCliente;

	private RepositorioClienteArray() throws ClassNotFoundException,
			RepositorioException {
		try {
			this.listaCliente = new ArrayList<Cliente>();
			this.arquivoCliente = new File(this.ARQUIVO);
			this.arquivoCliente.createNewFile();
			if (this.arquivoCliente.length() != 0)
				this.lerArquivo();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na abertura do arquivo "
					+ this.ARQUIVO + ".");
		}
	}

	public static RepositorioClienteArray getInstance()
			throws ClassNotFoundException, RepositorioException {
		if (repositorio == null) {
			repositorio = new RepositorioClienteArray();
		}
		return repositorio;
	}

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisCliente = null;
		ObjectInputStream oisCliente = null;
		try {
			fisCliente = new FileInputStream(arquivoCliente);
			oisCliente = new ObjectInputStream(fisCliente);
			while (true) {
				try {
					Cliente cliente = (Cliente) oisCliente.readObject();
					this.cadastrarCliente(cliente);
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
					+ (new RepositorioClienteArray()).getClass()
					+ " n�o encontrado.");
		} finally {
			try {
				fisCliente.close();
				oisCliente.close();
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
			fosRestaurante = new FileOutputStream(arquivoCliente);
			oosRestaurante = new ObjectOutputStream(fosRestaurante);
			for (Cliente cliente : this.listaCliente)
				oosRestaurante.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na grava��o do arquivo "
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

	public void cadastrarCliente(Cliente cliente) throws RepositorioException {
		this.listaCliente.add(cliente);
		this.gravarArquivo();
	}

	public Cliente procurarCliente(String cpf) {
		int indice = this.obterIndice(cpf);
		return this.listaCliente.get(indice);
	}

	public void alterarCliente(Cliente cliente) throws RepositorioException {
		int indice = this.obterIndice(cliente.getCpf());
		this.listaCliente.set(indice, cliente);
		this.gravarArquivo();
	}

	public boolean existe(String cpf) {
		boolean existe = false;
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirCliente(String cpf) throws RepositorioException {
		int indice = this.obterIndice(cpf);
		// preicsa usar um try, catch, informando que o cliente n�o existe
		if (indice != -1) {
			this.listaCliente.remove(indice);
			this.gravarArquivo();
			return true;
		}
		return false;
	}

	private int obterIndice(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.listaCliente.size(); i++) {
			if (this.listaCliente.get(i).getCpf().equals(cpf)) {
				indice = i;
			}
			// tratar um exce��o do tipo se o obj n�o foi encontrada
		}
		return indice; // Retorna -1 se n�o encontrou
	}
}
