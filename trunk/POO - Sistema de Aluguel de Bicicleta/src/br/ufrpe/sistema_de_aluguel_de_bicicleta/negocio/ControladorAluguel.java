package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAluguelArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
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
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.IdIncorreto;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class ControladorAluguel {
	private RepositorioAluguelArray repositorio;
	private ControladorCliente cliente;
	private ControladorEstacao estacao;
	private long idAluguel = 1;

	public ControladorAluguel() throws ClassNotFoundException,
			RepositorioException, ClienteJaCadastradoException {
		this.repositorio = RepositorioAluguelArray.getInstance();
		this.cliente = new ControladorCliente();
		this.estacao = new ControladorEstacao();
	}

	public void alugarBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			ClienteNaoCadastradoException, EstacaoNaoExisteException,
			BicicletaIndisponivelException, ClienteJaAlugouBicicletaException,
			IdIncorreto, AluguelInexistenteException {
		if (this.cliente.existe(cpf)) {
			if (this.estacao.existe(codigoEstacao)) {
				int indiceBicicletaNoArray = this.estacao.procurar(
						codigoEstacao).retornaIndiceBicicleta(codigoBicicleta);
				if (indiceBicicletaNoArray >= 0) {
					if (this.estacao.procurar(codigoEstacao).getBicicleta()
							.get(indiceBicicletaNoArray).getAlugou() == false) {
						Cliente cliente = this.cliente.procurar(cpf);
						Estacao estacao = this.estacao.procurar(codigoEstacao);
						estacao.getBicicleta()
								.get(estacao
										.retornaIndiceBicicleta(codigoBicicleta))
								.setAlugou(true);
						Aluguel aluguel = new Aluguel(this.idAluguel, estacao,
								cliente, Calendar.getInstance(Locale
										.getDefault()));
						if (this.existe(
								aluguel.getCliente().getCpf(),
								aluguel.getEstacao()
										.getBicicleta()
										.get(aluguel.getEstacao()
												.retornaIndiceBicicleta(
														codigoBicicleta))
										.getCodigo()) == false) {
							aluguel.setId(idAluguel);
							this.cadastrar(aluguel);
							this.idAluguel++;

						} else {
							throw new ClienteJaAlugouBicicletaException(cpf,
									codigoEstacao, codigoBicicleta);
						}
					} else {
						throw new BicicletaIndisponivelException(
								codigoBicicleta);
					}
				} else
					throw new IdIncorreto();
			} else {
				throw new EstacaoNaoExisteException(codigoEstacao);
			}
		} else {
			throw new ClienteNaoCadastradoException(cpf);
		}
	}

	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException,
			EstacaoNaoExisteException, ClienteNaoAlugouBicicletaException,
			AluguelInexistenteException {
		if (this.existe(cpf, codigoBicicleta)) {
			int indiceBicicletaNoArray = this.estacao.procurar(codigoEstacao)
					.retornaIndiceBicicleta(codigoBicicleta);
			Aluguel aluguelRetorno = this.procurar(cpf, codigoBicicleta);
			aluguelRetorno.getEstacao().getBicicleta()
					.get(indiceBicicletaNoArray).setAlugou(false);
			double preco = this.calcularAluguel(
					aluguelRetorno.getDataAluguel(),
					Calendar.getInstance(Locale.getDefault()));
			Aluguel aluguelPersistente = new Aluguel(aluguelRetorno.getId(),
					aluguelRetorno.getEstacao(), aluguelRetorno.getCliente(),
					aluguelRetorno.getDataAluguel(),
					Calendar.getInstance(Locale.getDefault()), preco);
			this.repositorio.alterarAluguel(aluguelPersistente);
		} else {
			throw new ClienteNaoAlugouBicicletaException(cpf, codigoEstacao,
					codigoBicicleta);
		}
	}

	private double calcularAluguel(Calendar aluguel, Calendar devolucao) {
		double preco = 0.0;

		int mes = devolucao.get(Calendar.MONTH) - aluguel.get(Calendar.MONTH);
		int dia = devolucao.get(Calendar.DAY_OF_MONTH)
				- aluguel.get(Calendar.DAY_OF_MONTH);
		int hora = devolucao.get(Calendar.HOUR_OF_DAY)
				- aluguel.get(Calendar.HOUR_OF_DAY);
		int minuto = devolucao.get(Calendar.MINUTE)
				- aluguel.get(Calendar.MINUTE);
		int segundo = devolucao.get(Calendar.SECOND)
				- aluguel.get(Calendar.SECOND);

		if (mes == 0 && dia == 0) {
			if ((hora == 0 && (minuto >= 0 && minuto < 60) && (segundo >= 0 && segundo < 60))
					|| (hora == 1 && minuto == 0 && segundo == 0)) { // De 0:0:0
																		// até
																		// 0:59:59
																		// OU de
																		// 1:0:0
				preco = 0.0;
			} else if ((hora > 1 && (minuto >= 0 && minuto < 60) && (segundo > 0 && segundo < 60))
					|| (hora == 2 && minuto == 0 && segundo == 0)) { // De 1:0:1
																		// até
																		// 1:59:59
																		// OU de
																		// 2:0:0
				preco = 3;
			} else
				preco = hora * 7;
		}
		return preco;
	}

	public void cadastrar(Aluguel aluguel) throws RepositorioException,
			AluguelInexistenteException {
		if (aluguel != null)
			repositorio.cadastrarAluguel(aluguel);
		else
			throw new AluguelInexistenteException(
					"Impossível salvar o aluguel.");
	}

	public void cadastrar(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException,
			AluguelExistenteException {
		Aluguel aluguel = this.procurar(cpf, idBicicleta);

		if (aluguel == null)
			repositorio.cadastrarAluguel(aluguel);
		else
			throw new AluguelExistenteException("O aluguel ja existe!");
	}

	public Aluguel procurar(String cpf, long idBicicleta)
			throws AluguelInexistenteException {
		return this.repositorio.procurarAluguel(cpf, idBicicleta);
	}

	public Aluguel procurarAluguelFinalizado(String cpf, long idBicicleta)
			throws AluguelInexistenteException {
		return this.repositorio.procurarAluguelFinalizado(cpf, idBicicleta);
	}

	public Aluguel procurar(long id) throws AluguelInexistenteException {
		return this.repositorio.procurarAluguel(id);
	}

	public void alterar(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException {
		Aluguel aluguel = this.procurar(cpf, idBicicleta);

		if (aluguel != null)
			this.repositorio.alterarAluguel(aluguel);
		else
			throw new AluguelInexistenteException("Aluguel não existe!");
	}

	public boolean existe(String cpf, long idBicicleta) {
		return this.repositorio.existe(cpf, idBicicleta);
	}

	public void excluir(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException {
		this.repositorio.excluirAluguel(cpf, idBicicleta);
	}

	public List<Aluguel> exibirALuguelComMulta()
			throws AluguelComMultaInexistenteException {
		if (this.repositorio.exibirALuguelComMulta().size() > 0)
			return repositorio.exibirALuguelComMulta();
		else
			throw new AluguelComMultaInexistenteException();
	}

	public List<Aluguel> exibirALuguelFinalizadoEstacao()
			throws AluguelInativoInexistenteException {
		if (this.repositorio.exibirALuguelFinalizadoEstacao().size() > 0) {
			return repositorio.exibirALuguelFinalizadoEstacao();
		} else
			throw new AluguelInativoInexistenteException();

	}

	public List<Aluguel> exibirALuguelAtivo()
			throws AluguelAtivoInexistenteException {
		if (this.repositorio.exibirALuguelAtivo().size() > 0) {
			return repositorio.exibirALuguelAtivo();
		} else
			throw new AluguelAtivoInexistenteException();
	}
}
