package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;

public class RepositorioAdministradorArray {
	private List<Administrador> listaLogin;
	private static RepositorioAdministradorArray repositorio;

	private RepositorioAdministradorArray() {
		this.listaLogin = new ArrayList<Administrador>();
	}

	public static RepositorioAdministradorArray getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioAdministradorArray();
		}
		return repositorio;
	}

	public void cadastrarAdministrador(Administrador adm) {
		this.listaLogin.add(adm);
	}

	public Administrador procurarAdministrador(String cpf) {
		int indice = this.obterIndice(cpf);
		return this.listaLogin.get(indice);
	}

	public void alterarAdministrador(Administrador adm) {
		int indice = this.obterIndice(adm.getCpf());
		// precisa do índice pra setar na posição correta
		this.listaLogin.set(indice, adm);
	}

	public boolean existe(String cpf) {
		boolean existe = false;
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirAdministrador(String cpf) {
		int indice = this.obterIndice(cpf);
		// preicsa usar um try, catch, informando que o adm não existe
		if (indice != -1) {
			this.listaLogin.remove(indice);
			return true;
		}
		return false;
	}

	private int obterIndice(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.listaLogin.size(); i++) {
			if (this.listaLogin.get(i).getCpf().equals(cpf)) {
				indice = i;
			}
			// tratar um exceção do tipo se a conta não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}
}
