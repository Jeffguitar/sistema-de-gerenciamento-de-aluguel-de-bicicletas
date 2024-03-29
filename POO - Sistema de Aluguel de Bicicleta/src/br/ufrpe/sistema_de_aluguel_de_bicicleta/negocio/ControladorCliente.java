package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioClienteArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClientesInexistentesException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class ControladorCliente {

	private RepositorioClienteArray repositorio;
	private static long idCliente = 1;

	public ControladorCliente() throws ClassNotFoundException,
			RepositorioException, ClienteJaCadastradoException {
		this.repositorio = new RepositorioClienteArray();
	}

	public void cadastrar(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException, ClienteNaoCadastradoException {
		boolean indice = this.existe(cliente.getCpf());

		if (indice == false && cliente != null) {
			cliente.setId(ControladorCliente.idCliente);
			repositorio.cadastrarCliente(cliente);
			ControladorCliente.idCliente++;
		} else
			throw new ClienteJaCadastradoException(cliente.getCpf());
	}

	public Cliente procurar(String cpf) throws ClienteNaoCadastradoException {
		return this.repositorio.procurarCliente(cpf);
	}

	public void alterar(Cliente cliente) throws RepositorioException,
			ClienteNaoCadastradoException {
		boolean indice = this.existe(cliente.getCpf());

		if (indice == false && cliente != null)
			this.repositorio.alterarCliente(cliente);
	}

	public boolean existe(String cpf) throws ClienteNaoCadastradoException {

		return this.repositorio.existe(cpf);
	}

	public void excluir(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException {
		this.repositorio.excluirCliente(cpf);
	}

	public List<Cliente> exibirClientes() throws ClientesInexistentesException {
		if (this.repositorio.exibirClientes().size() > 0)
			return this.repositorio.exibirClientes();
		else
			throw new ClientesInexistentesException();
	}
}
