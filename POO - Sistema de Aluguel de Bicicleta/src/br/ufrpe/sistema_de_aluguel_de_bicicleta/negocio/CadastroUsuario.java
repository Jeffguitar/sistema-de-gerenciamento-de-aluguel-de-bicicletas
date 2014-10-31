package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioUsuarioArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Usuario;

public class CadastroUsuario {

	private RepositorioUsuarioArray repositorio;

	public CadastroUsuario() {
		this.repositorio = new RepositorioUsuarioArray(100);
	}

	public RepositorioUsuarioArray getRepositorio() {
		return repositorio;
	}

	public void cadastrar(Usuario usuario) {
		if (this.procurar(usuario.getCpf()) == null) {
			this.repositorio.cadastrarUsuario(usuario);
		}
	}

	public Usuario procurar(String cpf) {
		Usuario retorno = null;
		if (cpf != null) {
			retorno = this.repositorio.procurarUsuario(cpf);
		}
		return retorno;
	}

	public Usuario atualizarDados(String cpf) {
		if (cpf != null) {
			return this.repositorio.alterarCliente(cpf);
		} else
			return null;
	}

	public void remover(String cpf) {
		Usuario auxiliar = this.repositorio.procurarUsuario(cpf);
		if (cpf != null && auxiliar != null) {
			this.repositorio.excluirUsuario(cpf);
		}
	}
}
