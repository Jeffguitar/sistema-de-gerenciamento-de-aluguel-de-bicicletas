package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.LoginAdministrador;

public class RepositorioLoginArray {
	private LoginAdministrador login[];
	private int proxima;
	private static RepositorioLoginArray repositorio;

	private RepositorioLoginArray(int tamanho) {
		this.login = new LoginAdministrador[tamanho];
		this.proxima = 0;
	}

	public static RepositorioLoginArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioLoginArray(100);
		}
		return repositorio;
	}

	public LoginAdministrador procurarLoginPorCpf(String cpf) {
		LoginAdministrador retornoBusca = null;

		for (int i = 0; i < this.login.length; i++) {
			if (this.login[i].getAdministrador().getCpf().equals(cpf)) {
				retornoBusca = this.login[i];
			}
		}
		return retornoBusca;
	}

	private int procurarIndicePeloCpf(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.login.length; i++) {
			if (this.login[i].getAdministrador().getCpf().equals(cpf)) {
				indice = i;
			}
		}
		return indice; // Retorna -1 se não encontrou
	}

	private void duplicaArrayLogin() {

		if (this.login != null && this.login.length > 0) {
			LoginAdministrador arrayEstacaoDobrado[] = new LoginAdministrador[this.login.length * 2];
			for (int i = 0; i < this.login.length; i++) {
				arrayEstacaoDobrado[i] = this.login[i];
			}
			this.login = arrayEstacaoDobrado;
		}
	}

	public void cadastrarEstação(LoginAdministrador login) {

		if ((this.procurarLoginPorCpf(login.getAdministrador().getCpf()) == null)
				&& this.procurarIndicePeloCpf(login.getAdministrador().getCpf()) != this.proxima) {
			this.login[this.proxima] = login;

		}
		if (this.proxima == this.login.length) {
			this.duplicaArrayLogin();
		}
	}

	public void excluirEstacao(String cpf) {

		if (this.procurarIndicePeloCpf(cpf) != this.proxima) {
			this.login[this.procurarIndicePeloCpf(cpf)] = this.login[this.proxima - 1];
			this.login[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}
	}
}
