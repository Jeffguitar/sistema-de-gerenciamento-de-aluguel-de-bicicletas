package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioClienteArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class ControladorCliente {

	private static RepositorioClienteArray repositorio;

	public ControladorCliente() {
		ControladorCliente.repositorio = RepositorioClienteArray.getInstance();
	}

	public void cadastrar(Cliente usuario) {
		if (this.procurar(usuario.getCpf()) == null) {
			ControladorCliente.repositorio.cadastrarUsuario(usuario);
		}
	}

	public Cliente procurar(String cpf) {
		Cliente retorno = null;
		if (cpf != null) {
			retorno = ControladorCliente.repositorio.procurarCliente(cpf);
		}
		return retorno;
	}

	public Cliente atualizarDados(String cpf) {
		if (cpf != null) {
			return ControladorCliente.repositorio.alterarCliente(cpf);
		} else
			return null;
	}

	public void remover(String cpf) {
		Cliente auxiliar = ControladorCliente.repositorio.procurarCliente(cpf);
		if (cpf != null && auxiliar != null) {
			ControladorCliente.repositorio.excluirUsuario(cpf);
		}
	}
}
