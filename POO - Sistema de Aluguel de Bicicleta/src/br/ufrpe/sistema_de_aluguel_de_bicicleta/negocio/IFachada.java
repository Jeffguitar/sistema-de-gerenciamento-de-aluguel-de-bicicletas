package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public interface IFachada {

	public void alugarBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException;

	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException;

	// Início Administrador

	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException;

	public void procurarAdministrador(String cpf);

	public void alterarAdministrador(Administrador adm)
			throws RepositorioException;

	public void excluirAdministrador(String cpf) throws RepositorioException;

	// Fim Administrador

	// Início Aluguel

	public void cadastrarAluguel(Aluguel adm) throws RepositorioException;

	public void procurarAluguel(String cpf);
	
	public void procurarBicicletaNoAluguel(String cpf, int idBicicleta);

	public void alterarAluguel(Aluguel adm) throws RepositorioException;

	public void excluirAluguel(long id) throws RepositorioException;

	// Fim Aluguel

	// Início Cliente

	public void cadastrarCliente(Cliente cliente) throws RepositorioException;

	public void procurarCliente(String cpf);

	public void alterarCliente(Cliente cliente) throws RepositorioException;

	public void excluirCliente(String cpf) throws RepositorioException;

	// Fim Cliente

	// Início Estação

	public void cadastrarEstacao(Estacao estacao) throws RepositorioException;

	public void procurarEstacao(long id);

	public void alterarEstacao(Estacao estacao) throws RepositorioException;

	public void excluirEstacao(long id) throws RepositorioException;

	// Fim Estação
}
