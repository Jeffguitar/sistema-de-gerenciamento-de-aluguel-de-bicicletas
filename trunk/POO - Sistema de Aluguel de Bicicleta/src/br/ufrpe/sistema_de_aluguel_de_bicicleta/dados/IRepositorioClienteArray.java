package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public interface IRepositorioClienteArray {

	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException;

	public Cliente procurarCliente(String cpf)
			throws ClienteNaoCadastradoException;

	public void alterarCliente(Cliente cliente) throws RepositorioException,
			ClienteNaoCadastradoException;

	public boolean existe(String cpf) throws ClienteNaoCadastradoException;

	public void excluirCliente(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException;

	public List<Cliente> exibirClientes();

}
