package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAdministradorArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorJaExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class ControladorAdministrador {

	private RepositorioAdministradorArray repositorio;
	private long idAdministrador = 1;

	public ControladorAdministrador() throws ClassNotFoundException,
			RepositorioException {
		this.repositorio = RepositorioAdministradorArray.getInstance();
	}

	public void cadastrar(Administrador adm) throws RepositorioException,
			AdministradorJaExistenteException,
			AdministradorInexistenteException {
		boolean indice = this.existe(adm.getCpf());

		if (indice == false && adm != null) {
			adm.setId(this.idAdministrador);
			repositorio.cadastrarAdministrador(adm);
			this.idAdministrador++;
		} else
			throw new AdministradorJaExistenteException(adm.getCpf());
	}

	public Administrador procurar(String cpf)
			throws AdministradorInexistenteException {
		return this.repositorio.procurarAdministrador(cpf);
	}

	public void alterar(Administrador adm) throws RepositorioException,
			AdministradorInexistenteException {
		boolean resposta = this.existe(adm.getCpf());

		if (resposta == false && adm != null)
			this.repositorio.alterarAdministrador(adm);
	}

	public boolean existe(String cpf) throws AdministradorInexistenteException {
		return this.repositorio.existe(cpf);
	}

	public void excluir(String cpf) throws RepositorioException,
			AdministradorInexistenteException {
		this.repositorio.excluirAdministrador(cpf);
	}

}
