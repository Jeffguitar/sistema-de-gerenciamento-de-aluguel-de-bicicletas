package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioUsuarioArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class CadastroUsuario {

	private RepositorioUsuarioArray repositorio;

	public CadastroUsuario() {
		this.repositorio = new RepositorioUsuarioArray(100);
	}

	public RepositorioUsuarioArray getRepositorio() {
		return repositorio;
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
