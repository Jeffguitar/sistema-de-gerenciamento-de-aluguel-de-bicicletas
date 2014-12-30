package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.util.Calendar;
import java.util.Locale;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAluguelArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
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
			long codigoBicicleta) throws RepositorioException, ClienteNaoCadastradoException, EstacaoNaoExisteException {
		if (this.cliente.existe(cpf)) { // 1 - Se cliente existe
			if (this.estacao.existe(codigoEstacao)) { // 2 - Se a estação existe
				int indiceBicicletaNoArray = this.estacao.procurar(
						codigoEstacao).retornaIndiceBicicleta(codigoBicicleta); // 3 - indíce da bicicleta informada correspodente à lista de bicicletas da uma estação
				if (this.estacao.procurar(codigoEstacao).getBicicleta()
						.get(indiceBicicletaNoArray).getAlugou() == false) { //4 - Se a bicicleta está disponível
					Cliente cliente = this.cliente.procurar(cpf); // 5 - Retorna o objeto do tipo cliente
					Estacao estacao = this.estacao.procurar(codigoEstacao); // 6 - retora o objeto do tipo estaçao
					estacao.getBicicleta()
							.get(estacao
									.retornaIndiceBicicleta(codigoBicicleta))
							.setAlugou(true); // 7 - Torna a bicicleta indisponível
					Aluguel aluguel = new Aluguel(this.idAluguel, estacao,
							cliente, Calendar.getInstance(Locale.getDefault())); // 8 - Monta o objeto persistente Aluguel
					if (this.existe(
							aluguel.getCliente().getCpf(),
							aluguel.getEstacao()
									.getBicicleta()
									.get(aluguel.getEstacao()
											.retornaIndiceBicicleta(
													codigoBicicleta))
									.getCodigo()) == false) { // Aluga se o cliente não 'possui' uma bicicleta
						this.cadastrar(aluguel);
						this.idAluguel++;
					}
				}
			}
		}
	}

	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) throws RepositorioException, EstacaoNaoExisteException {
		if (this.existe(cpf, codigoBicicleta)) { // tratar exceção do
															// tipo
			// 'aluguelInexistenteException'
			// (sugestão)
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
			this.alterar(
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

	public void cadastrar(Aluguel aluguel) throws RepositorioException {
		if (aluguel == null)
			repositorio.cadastrarAluguel(aluguel);
	}

	public void cadastrar(String cpf, long idBicicleta)
			throws RepositorioException {
		Aluguel aluguel = this.procurar(cpf, idBicicleta);

		if (aluguel == null)
			repositorio.cadastrarAluguel(aluguel);
	}

	public Aluguel procurar(String cpf, long idBicicleta) {
		return this.repositorio.procurarAluguel(cpf, idBicicleta);
	}

	public Aluguel procurar(long id) {
		return this.repositorio.procurarAluguel(id);
	}

	public void alterar(String cpf, long idBicicleta)
			throws RepositorioException {
		Aluguel aluguel = this.procurar(cpf, idBicicleta);

		if (aluguel != null)
			this.repositorio.alterarAluguel(aluguel);
	}

	public boolean existe(String cpf, long idBicicleta) {
		return this.repositorio.existe(cpf, idBicicleta);
	}

	public void excluir(String cpf, long idBicicleta)
			throws RepositorioException {
		this.repositorio.excluirAluguel(cpf, idBicicleta);
	}
}
