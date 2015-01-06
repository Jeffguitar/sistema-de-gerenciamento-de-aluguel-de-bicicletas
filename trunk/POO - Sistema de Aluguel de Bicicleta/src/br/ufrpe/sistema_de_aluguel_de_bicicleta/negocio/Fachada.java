package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorJaExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelAtivoInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelComMultaInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInativoInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.BicicletaIndisponivelException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaAlugouBicicletaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoAlugouBicicletaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClientesInexistentesException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class Fachada implements IFachada {
	private ControladorAdministrador adm;
	private ControladorCliente cliente;
	private ControladorAluguel aluguel;
	private ControladorEstacao estacao;

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
			ClienteNaoCadastradoException, EstacaoNaoExisteException,
			BicicletaIndisponivelException, ClienteJaAlugouBicicletaException {
		this.aluguel.alugarBicicleta(cpf, codigoEstacao, codigoBicicleta);
	}

	@Override
	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			EstacaoNaoExisteException, ClienteNaoAlugouBicicletaException,
			AluguelInexistenteException {
		this.aluguel.devolverBicicleta(cpf, codigoEstacao, codigoBicicleta);
	}

	@Override
	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorJaExistenteException,
			AdministradorInexistenteException {
		this.adm.cadastrar(adm);

	}

	@Override
	public Administrador procurarAdministrador(String cpf)
			throws AdministradorInexistenteException {
		return this.adm.procurar(cpf);

	}

	@Override
	public void alterarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorInexistenteException {
		this.adm.alterar(adm);

	}

	@Override
	public boolean existeAdministrador(String cpf)
			throws AdministradorInexistenteException {
		return this.adm.existe(cpf);

	}

	@Override
	public void excluirAdministrador(String cpf) throws RepositorioException,
			AdministradorInexistenteException {
		this.adm.excluir(cpf);

	}

	@Override
	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException {
		this.aluguel.cadastrar(aluguel);
	}

	@Override
	public void cadastrarAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException,
			AluguelExistenteException {
		this.aluguel.cadastrar(cpf, idBicicleta);

	}

	@Override
	public Aluguel procurarAluguel(String cpf, long idBicicleta)
			throws AluguelInexistenteException {
		return this.aluguel.procurar(cpf, idBicicleta);
	}

	public Aluguel procurarAluguel(long id) throws AluguelInexistenteException {
		return this.aluguel.procurar(id);
	}

	@Override
	public void alterarAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException {
		this.aluguel.alterar(cpf, idBicicleta);

	}

	@Override
	public boolean existeAluguel(String cpf, long idBicicleta) {
		return this.aluguel.existe(cpf, idBicicleta);

	}

	@Override
	public void excluirAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException {
		this.aluguel.excluir(cpf, idBicicleta);

	}

	@Override
	public List<Aluguel> exibirALuguelComMulta()
			throws AluguelComMultaInexistenteException {
		return this.aluguel.exibirALuguelComMulta();
	}

	@Override
	public List<Aluguel> exibirALuguelFinalizadoEstacao()
			throws AluguelInativoInexistenteException {
		return this.aluguel.exibirALuguelFinalizadoEstacao();
	}

	@Override
	public List<Aluguel> exibirALuguelAtivo()
			throws AluguelAtivoInexistenteException {
		return this.aluguel.exibirALuguelAtivo();
	}

	@Override
	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException, ClienteNaoCadastradoException {
		this.cliente.cadastrar(cliente);
	}

	@Override
	public Cliente procurarCliente(String cpf)
			throws ClienteNaoCadastradoException {
		return this.cliente.procurar(cpf);
	}

	@Override
	public void alterarCliente(Cliente cliente) throws RepositorioException,
			ClienteNaoCadastradoException {
		this.cliente.alterar(cliente);
	}

	@Override
	public boolean existeCliente(String cpf)
			throws ClienteNaoCadastradoException {
		return this.cliente.existe(cpf);
	}

	@Override
	public void excluirCliente(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException {
		this.cliente.excluir(cpf);
	}

	@Override
	public List<Cliente> exibirClientes() throws ClientesInexistentesException {
		return this.cliente.exibirClientes();
	}

	@Override
	public void cadastrarEstacao(Estacao estacao) throws RepositorioException,
			EstacaoExistenteException, EstacaoNaoExisteException {
		this.estacao.cadastrar(estacao);
	}

	@Override
	public Estacao procurarEstacao(long id) throws EstacaoNaoExisteException {
		return this.estacao.procurar(id);
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