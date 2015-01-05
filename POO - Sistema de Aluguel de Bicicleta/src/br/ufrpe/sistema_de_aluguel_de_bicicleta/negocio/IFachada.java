package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorJaExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.BicicletaIndisponivelException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaAlugouBicicletaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoAlugouBicicletaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public interface IFachada {

	public void alugarBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			ClienteNaoCadastradoException, EstacaoNaoExisteException,
			BicicletaIndisponivelException, ClienteJaAlugouBicicletaException;

	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			EstacaoNaoExisteException, ClienteNaoAlugouBicicletaException;

	// Início Administrador

	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorJaExistenteException;

	public Administrador procurarAdministrador(String cpf)
			throws AdministradorInexistenteException;

	public void alterarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorInexistenteException;

	public boolean existeAdministrador(String cpf);

	public void excluirAdministrador(String cpf) throws RepositorioException;

	// Fim Administrador

	// Início Aluguel

	public void cadastrarAluguel(String cpf, long idBicicleta)
			throws RepositorioException;

	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException;

	public void procurarAluguel(String cpf, long idBicicleta);

	public Aluguel procurarAluguel(long id);

	public void alterarAluguel(String cpf, long idBicicleta)
			throws RepositorioException;

	public boolean existeAluguel(String cpf, long idBicicleta);

	public void excluirAluguel(String cpf, long idBicicleta)
			throws RepositorioException;

	// Fim Aluguel

	// Início Cliente

	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException, ClienteNaoCadastradoException;

	public Cliente procurarCliente(String cpf)
			throws ClienteNaoCadastradoException;

	public void alterarCliente(Cliente cliente) throws RepositorioException,
			ClienteNaoCadastradoException;

	public boolean existeCliente(String cpf)
			throws ClienteNaoCadastradoException;

	public void excluirCliente(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException;

	// Fim Cliente

	// Início Estação

	public void cadastrarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoExistenteException;

	public Estacao procurarEstacao(long id) throws EstacaoNaoExisteException;

	public void alterarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoNaoExisteException;

	public void excluirEstacao(long id) throws RepositorioException,
			EstacaoNaoExisteException;

	// Fim Estação
}
