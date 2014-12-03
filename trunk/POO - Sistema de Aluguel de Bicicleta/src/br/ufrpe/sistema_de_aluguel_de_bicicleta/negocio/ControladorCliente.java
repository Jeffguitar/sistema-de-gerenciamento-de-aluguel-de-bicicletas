package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioClienteArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class ControladorCliente {

	private RepositorioClienteArray repositorio;

	public ControladorCliente() {
		this.repositorio = RepositorioClienteArray.getInstance();
	}

	public void cadastrar(Cliente usuario) {
		if (this.procurar(usuario.getCpf()) == null) {
			this.repositorio.cadastrarUsuario(usuario);
		}
	}

	public Cliente procurar(String cpf) {
		Cliente retorno = null;
		if (cpf != null) {
			retorno = this.repositorio.procurarCliente(cpf);
		}
		return retorno;
	}

	public Cliente atualizarDados(String cpf) {
		if (cpf != null) {
			return this.repositorio.alterarCliente(cpf);
		} else
			return null;
	}

	public void remover(String cpf) {
		Cliente auxiliar = this.repositorio.procurarCliente(cpf);
		if (cpf != null && auxiliar != null) {
			this.repositorio.excluirUsuario(cpf);
		}
	}
}
