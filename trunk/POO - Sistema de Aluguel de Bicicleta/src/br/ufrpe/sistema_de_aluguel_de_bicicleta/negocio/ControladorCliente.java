package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioClienteArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class ControladorCliente {

	private RepositorioClienteArray repositorio;

	public ControladorCliente() throws ClassNotFoundException,
			RepositorioException {
		this.repositorio = RepositorioClienteArray.getInstance();
	}

	public void cadastrar(Cliente cliente) throws RepositorioException {
		boolean indice = this.existe(cliente.getCpf());

		if (indice == false && cliente != null)
			repositorio.cadastrarCliente(cliente);
	}

	public Cliente procurar(String cpf) {
		return this.repositorio.procurarCliente(cpf);
	}

	public void alterar(Cliente cliente) throws RepositorioException {
		boolean indice = this.existe(cliente.getCpf());

		if (indice == false && cliente != null)
			this.repositorio.alterarCliente(cliente);
	}

	public boolean existe(String cpf) {

		return this.repositorio.existe(cpf);
	}

	public void excluir(String cpf) throws RepositorioException {
		this.repositorio.excluirCliente(cpf);
	}
}
