package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioClienteArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;

public class ControladorCliente {

	private RepositorioClienteArray repositorio;

	public ControladorCliente() throws ClassNotFoundException,
			RepositorioException, ClienteJaCadastradoException {
		this.repositorio = RepositorioClienteArray.getInstance();
	}

	public void cadastrar(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException, ClienteNaoCadastradoException {
		boolean indice = this.existe(cliente.getCpf());

		if (indice == false && cliente != null)
			repositorio.cadastrarCliente(cliente);
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
}
