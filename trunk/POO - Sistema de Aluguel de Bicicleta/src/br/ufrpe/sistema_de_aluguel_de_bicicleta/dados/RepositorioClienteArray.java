package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class RepositorioClienteArray {

	private List<Cliente> listaCliente;
	private static RepositorioClienteArray repositorio;

	private RepositorioClienteArray() {
		this.listaCliente = new ArrayList<Cliente>();
	}

	public static RepositorioClienteArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioClienteArray();
		}
		return repositorio;
	}

	public void cadastrarCliente(Cliente cliente) {
		this.listaCliente.add(cliente);
	}

	public Cliente procurarCliente(String cpf) {
		int indice = this.obterIndice(cpf);
		return this.listaCliente.get(indice);
	}

	public void alterarCliente(Cliente cliente) {
		int indice = this.obterIndice(cliente.getCpf());
		this.listaCliente.set(indice, cliente);
	}

	public boolean existe(String cpf) {
		boolean existe = false;
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirCliente(String cpf) {
		int indice = this.obterIndice(cpf);
		// preicsa usar um try, catch, informando que o cliente não existe
		if (indice != -1) {
			this.listaCliente.remove(indice);
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
			// tratar um exceção do tipo se o obj não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}
}
