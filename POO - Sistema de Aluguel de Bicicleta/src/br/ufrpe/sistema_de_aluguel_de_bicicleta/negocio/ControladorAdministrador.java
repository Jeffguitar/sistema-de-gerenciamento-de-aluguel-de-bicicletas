package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAdministradorArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class ControladorAdministrador {

	private RepositorioAdministradorArray repositorio;

	public ControladorAdministrador() throws ClassNotFoundException, RepositorioException {
		this.repositorio = RepositorioAdministradorArray.getInstance();
	}

	public void cadastrar(Administrador adm) throws RepositorioException {
		boolean indice = this.existe(adm.getCpf());

		if (indice == false && adm != null)
			repositorio.cadastrarAdministrador(adm);
	}

	public Administrador procurar(String cpf) {
		return this.repositorio.procurarAdministrador(cpf);
	}

	public void alterar(Administrador adm) throws RepositorioException {
		boolean resposta = this.existe(adm.getCpf());

		if (resposta == false && adm != null)
			this.repositorio.alterarAdministrador(adm);
	}

	public boolean existe(String cpf) {
		return this.repositorio.existe(cpf);
	}

	public void excluir(String cpf) throws RepositorioException {
		this.repositorio.excluirAdministrador(cpf);
	}

}
