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
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class RepositorioClienteArray implements IRepositorioClienteArray {

	private List<Cliente> listaCliente;
	private final String ARQUIVO = "clientes.dat";
	private File arquivoCliente;

	public RepositorioClienteArray() throws ClassNotFoundException,
			RepositorioException, ClienteJaCadastradoException {
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

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException, ClienteJaCadastradoException {
		FileInputStream fisCliente = null;
		ObjectInputStream oisCliente = null;
		try {
			fisCliente = new FileInputStream(arquivoCliente);
			oisCliente = new ObjectInputStream(fisCliente);
			while (true) {
				try {
					@SuppressWarnings("unchecked")
					ArrayList<Cliente> cliente = (ArrayList<Cliente>) oisCliente
							.readObject();
					for (Cliente a : cliente)
						this.cadastrarCliente(a);
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
					+ " não encontrado.");
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
		FileOutputStream fosCliente = null;
		ObjectOutputStream oosCliente = null;
		try {
			fosCliente = new FileOutputStream(arquivoCliente);
			oosCliente = new ObjectOutputStream(fosCliente);
			oosCliente.writeObject(listaCliente);
			oosCliente.reset();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na gravação do arquivo "
					+ this.ARQUIVO + ".");
		} finally {
			try {
				fosCliente.close();
				oosCliente.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException {
		this.listaCliente.add(cliente);
		this.gravarArquivo();
	}

	public Cliente procurarCliente(String cpf)
			throws ClienteNaoCadastradoException {
		int indice = this.obterIndice(cpf);
		if (indice == -1)
			throw new ClienteNaoCadastradoException(cpf);
		return this.listaCliente.get(indice);
	}

	public void alterarCliente(Cliente cliente) throws RepositorioException,
			ClienteNaoCadastradoException {
		int indice = this.obterIndice(cliente.getCpf());
		if (indice == -1)
			throw new ClienteNaoCadastradoException(cliente.getCpf());
		this.listaCliente.set(indice, cliente);
		this.gravarArquivo();
	}

	public boolean existe(String cpf) throws ClienteNaoCadastradoException {
		boolean existe = false;
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public void excluirCliente(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException {
		int indice = this.obterIndice(cpf);

		if (indice != -1) {
			this.listaCliente.remove(indice);
			this.gravarArquivo();
		} else
			throw new ClienteNaoCadastradoException(cpf);
	}

	public List<Cliente> exibirClientes() {
		return this.listaCliente;
	}

	private int obterIndice(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.listaCliente.size(); i++) {
			if (this.listaCliente.get(i).getCpf().equals(cpf)) {
				indice = i;
			}
			// tratar um exceção do tipo se o obj não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}
}
