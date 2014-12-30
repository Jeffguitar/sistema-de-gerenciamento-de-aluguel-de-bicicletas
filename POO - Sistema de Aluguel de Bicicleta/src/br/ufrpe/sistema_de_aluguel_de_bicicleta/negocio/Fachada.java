package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class Fachada implements IFachada {
	private ControladorAdministrador adm;
	private ControladorCliente cliente;
	private ControladorAluguel aluguel;
	private ControladorEstacao estacao;
	public static long ID_ALUGUEL = 1;
	public static long ID_ADM = 1;
	public static long ID_CLIENTE = 1;
	public static long ID_ESTACAO = 1;

	public Fachada() throws ClassNotFoundException, RepositorioException,
			ClienteJaCadastradoException {
		this.adm = new ControladorAdministrador();
		this.cliente = new ControladorCliente();
		this.aluguel = new ControladorAluguel();
		this.estacao = new ControladorEstacao();
	}

	@Override
	public void alugarBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			ClienteNaoCadastradoException, EstacaoNaoExisteException {
		this.aluguel.alugarBicicleta(cpf, codigoEstacao, codigoBicicleta);
	}

	@Override
	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			EstacaoNaoExisteException {
		this.aluguel.devolverBicicleta(cpf, codigoEstacao, codigoBicicleta);
	}

	@Override
	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException {
		this.adm.cadastrar(adm);

	}

	@Override
	public void procurarAdministrador(String cpf) {
		this.adm.procurar(cpf);

	}

	@Override
	public void alterarAdministrador(Administrador adm)
			throws RepositorioException {
		this.adm.alterar(adm);

	}

	@Override
	public boolean existeAdministrador(String cpf) {
		return this.adm.existe(cpf);

	}

	@Override
	public void excluirAdministrador(String cpf) throws RepositorioException {
		this.adm.excluir(cpf);

	}

	@Override
	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException {
		this.aluguel.cadastrar(aluguel);
	}

	@Override
	public void cadastrarAluguel(String cpf, long idBicicleta)
			throws RepositorioException {
		this.aluguel.cadastrar(cpf, idBicicleta);

	}

	@Override
	public void procurarAluguel(String cpf, long idBicicleta) {
		this.aluguel.procurar(cpf, idBicicleta);

	}

	public void procurarAluguel(long id) {
		this.aluguel.procurar(id);
	}

	@Override
	public void alterarAluguel(String cpf, long idBicicleta)
			throws RepositorioException {
		this.aluguel.alterar(cpf, idBicicleta);

	}

	@Override
	public boolean existeAluguel(String cpf, long idBicicleta) {
		return this.aluguel.existe(cpf, idBicicleta);

	}

	@Override
	public void excluirAluguel(String cpf, long idBicicleta)
			throws RepositorioException {
		this.aluguel.excluir(cpf, idBicicleta);

	}

	@Override
	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException, ClienteNaoCadastradoException {
		this.cliente.cadastrar(cliente);
	}

	@Override
	public void procurarCliente(String cpf)
			throws ClienteNaoCadastradoException {
		this.cliente.procurar(cpf);
	}

	@Override
	public void alterarCliente(Cliente cliente) throws RepositorioException,
			ClienteNaoCadastradoException {
		this.cliente.alterar(cliente);
	}

	@Override
	public void excluirCliente(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException {
		this.cliente.excluir(cpf);
	}

	@Override
	public void cadastrarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoExistenteException {
		this.estacao.cadastrar(estacao);
	}

	@Override
	public void procurarEstacao(long id) throws EstacaoNaoExisteException {
		this.estacao.procurar(id);
	}

	@Override
	public void alterarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoNaoExisteException {
		this.estacao.alterar(estacao);
	}

	@Override
	public void excluirEstacao(long id) throws RepositorioException,
			EstacaoNaoExisteException {
		this.estacao.excluir(id);
	}
}