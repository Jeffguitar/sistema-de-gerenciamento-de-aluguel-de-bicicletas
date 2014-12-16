package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.util.Calendar;
import java.util.Locale;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;

public class Fachada implements IFachada {
	private ControladorAdministrador adm;
	private ControladorCliente cliente;
	private ControladorAluguel aluguel;
	private ControladorEstacao estacao;
	public static long ID_ALUGUEL = 1;
	public static long ID_ADM = 1;
	public static long ID_CLIENTE = 1;
	public static long ID_ESTACAO = 1;

	public Fachada() throws ClassNotFoundException, RepositorioException {
		this.adm = new ControladorAdministrador();
		this.cliente = new ControladorCliente();
		this.aluguel = new ControladorAluguel();
		this.estacao = new ControladorEstacao();
	}

	@Override
	public void alugarBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException {
		if (this.cliente.existe(cpf)) {
			if (this.estacao.existe(codigoEstacao)) {
				int indiceBicicletaNoArray = this.estacao.procurar(
						codigoEstacao).retornaIndiceBicicleta(codigoBicicleta);
				if (this.estacao.procurar(codigoEstacao).getBicicleta()
						.get(indiceBicicletaNoArray).getAlugou() == false) {
					Cliente cliente = this.cliente.procurar(cpf);
					Estacao estacao = this.estacao.procurar(codigoEstacao);
					estacao.getBicicleta()
							.get(estacao
									.retornaIndiceBicicleta(codigoBicicleta))
							.setAlugou(true);
					Aluguel a = new Aluguel(Fachada.ID_ALUGUEL, estacao,
							cliente, Calendar.getInstance(Locale.getDefault()));
					if (!this.aluguel.existe(
							a.getCliente().getCpf(),
							a.getEstacao()
									.getBicicleta()
									.get(a.getEstacao().retornaIndiceBicicleta(
											codigoBicicleta)).getCodigo())) {
						this.aluguel.cadastrar(a);
						Fachada.ID_ALUGUEL++;
					}
				}
			}
		}
	}

	@Override
	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException {
		if (this.aluguel.existe(cpf, codigoBicicleta)) { // tratar exceção do
															// tipo
			// 'aluguelInexistenteException'
			// (sugestão)
			int indiceBicicletaNoArray = this.estacao.procurar(codigoEstacao)
					.retornaIndiceBicicleta(codigoBicicleta);
			Aluguel aluguelRetorno = this.aluguel
					.procurar(cpf, codigoBicicleta);
			aluguelRetorno.getEstacao().getBicicleta()
					.get(indiceBicicletaNoArray).setAlugou(false);
			double preco = this.calcularAluguel(
					aluguelRetorno.getDataAluguel(),
					Calendar.getInstance(Locale.getDefault()));
			Aluguel aluguelPersistente = new Aluguel(aluguelRetorno.getId(),
					aluguelRetorno.getEstacao(), aluguelRetorno.getCliente(),
					aluguelRetorno.getDataAluguel(),
					Calendar.getInstance(Locale.getDefault()), preco);
			this.aluguel.alterar(
					aluguelPersistente.getCliente().getCpf(),
					aluguelPersistente
							.getEstacao()
							.getBicicleta()
							.get(aluguelPersistente.getEstacao()
									.retornaIndiceBicicleta(codigoBicicleta))
							.getCodigo());
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
	public void cadastrarCliente(Cliente cliente) throws RepositorioException {
		this.cliente.cadastrar(cliente);
	}

	@Override
	public void procurarCliente(String cpf) {
		this.cliente.procurar(cpf);
	}

	@Override
	public void alterarCliente(Cliente cliente) throws RepositorioException {
		this.cliente.alterar(cliente);
	}

	@Override
	public void excluirCliente(String cpf) throws RepositorioException {
		this.cliente.excluir(cpf);
	}

	@Override
	public void cadastrarEstacao(Estacao estacao) throws RepositorioException {
		this.estacao.cadastrar(estacao);
	}

	@Override
	public void procurarEstacao(long id) {
		this.estacao.procurar(id);
	}

	@Override
	public void alterarEstacao(Estacao estacao) throws RepositorioException {
		this.estacao.alterar(estacao);
	}

	@Override
	public void excluirEstacao(long id) throws RepositorioException {
		this.estacao.excluir(id);
	}
}