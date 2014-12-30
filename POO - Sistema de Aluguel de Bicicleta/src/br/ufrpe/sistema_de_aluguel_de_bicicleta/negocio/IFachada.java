package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;

public interface IFachada {

	public void alugarBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException, ClienteNaoCadastradoException;

	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException;

	// Início Administrador

	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException;

	public void procurarAdministrador(String cpf);

	public void alterarAdministrador(Administrador adm)
			throws RepositorioException;
	
	public boolean existeAdministrador(String cpf);

	public void excluirAdministrador(String cpf) throws RepositorioException;

	// Fim Administrador

	// Início Aluguel

	public void cadastrarAluguel(String cpf, long idBicicleta) throws RepositorioException;
	
	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException;

	public void procurarAluguel(String cpf, long idBicicleta);
	
	public void procurarAluguel(long id);

	public void alterarAluguel(String cpf, long idBicicleta) throws RepositorioException;
	
	public boolean existeAluguel(String cpf, long idBicicleta);

	public void excluirAluguel(String cpf, long idBicicleta) throws RepositorioException;

	// Fim Aluguel

	// Início Cliente

	public void cadastrarCliente(Cliente cliente) throws RepositorioException, ClienteJaCadastradoException, ClienteNaoCadastradoException;

	public void procurarCliente(String cpf) throws ClienteNaoCadastradoException;

	public void alterarCliente(Cliente cliente) throws RepositorioException, ClienteNaoCadastradoException;

	public void excluirCliente(String cpf) throws RepositorioException, ClienteNaoCadastradoException;

	// Fim Cliente

	// Início Estação

	public void cadastrarEstacao(Estacao estacao) throws RepositorioException;

	public void procurarEstacao(long id);

	public void alterarEstacao(Estacao estacao) throws RepositorioException;

	public void excluirEstacao(long id) throws RepositorioException;

	// Fim Estação
}
