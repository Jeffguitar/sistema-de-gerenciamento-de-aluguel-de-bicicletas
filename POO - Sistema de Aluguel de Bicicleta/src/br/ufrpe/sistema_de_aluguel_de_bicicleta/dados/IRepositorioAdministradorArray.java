package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorJaExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public interface IRepositorioAdministradorArray {

	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorJaExistenteException,
			AdministradorInexistenteException;

	public Administrador procurarAdministrador(String cpf)
			throws AdministradorInexistenteException;

	public void alterarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorInexistenteException;

	public boolean existe(String cpf) throws AdministradorInexistenteException;

	public void excluirAdministrador(String cpf) throws RepositorioException,
			AdministradorInexistenteException;
}
