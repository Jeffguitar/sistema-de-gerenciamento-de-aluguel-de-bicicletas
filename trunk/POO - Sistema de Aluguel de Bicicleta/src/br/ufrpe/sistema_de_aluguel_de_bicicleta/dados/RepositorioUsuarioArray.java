package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Usuario;

public class RepositorioUsuarioArray {
	private Usuario usuario[];
	private int proximo;

	public RepositorioUsuarioArray(int tamanho) {
		this.usuario = new Usuario[tamanho];
		this.proximo = 0;
	}

	private void duplicaArrayUsuario() {
		if (this.usuario != null && this.usuario.length > 0) {
			Usuario arrayUsuarioDobrado[] = new Usuario[this.usuario.length * 2];
			for (int i = 0; i < this.usuario.length; i++) {
				arrayUsuarioDobrado[i] = this.usuario[i];
			}
			this.usuario = arrayUsuarioDobrado;
		}
	}

	private int procurarPeloIndice(String cpf) {
		int indice = -1;

		if (cpf != null) {
			for (int i = 0; i < this.usuario.length; i++) {
				if (this.usuario[i].getCpf().equals(cpf)) {
					indice = 1;
				}
			}
		}
		return indice;
	}

	public Usuario procurarUsuario(String cpf) {
		Usuario retornoBusca = null;

		for (int i = 0; i < this.usuario.length; i++) {
			if (this.usuario[i].getCpf().equals(cpf)) {
				retornoBusca = this.usuario[i];

			}
		}
		return retornoBusca;
	}

	public void cadastrarUsuario(Usuario usuario) {

		this.usuario[this.proximo] = usuario;
		this.proximo += 1;
		if (this.proximo == this.usuario.length) {
			this.duplicaArrayUsuario();
		}
	}

	/*
	 * public void alugarBicicleta(long codigoEstacao, int codigoBicicleta) {
	 * boolean retorno; retorno = repositorio.isAlugada(codigoEstacao,
	 * codigoBicicleta);
	 * 
	 * }
	 */

	public Usuario alterarCliente(String cpf) {
		return this.procurarUsuario(cpf);
	}

	public void excluirUsuario(String cpf) {

		if (this.procurarPeloIndice(cpf) != this.proximo) {
			this.usuario[this.procurarPeloIndice(cpf)] = this.usuario[this.proximo - 1];
			this.usuario[this.proximo - 1] = null;
			this.proximo = this.proximo - 1;
		}
	}
}
