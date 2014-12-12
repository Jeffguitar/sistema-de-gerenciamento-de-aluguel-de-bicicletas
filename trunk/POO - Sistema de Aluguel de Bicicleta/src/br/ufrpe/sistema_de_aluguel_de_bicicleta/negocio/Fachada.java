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

	public Fachada() throws ClassNotFoundException, RepositorioException {
		this.adm = new ControladorAdministrador();
		this.cliente = new ControladorCliente();
		this.aluguel = new ControladorAluguel();
		this.estacao = new ControladorEstacao();
	}
		
	@Override
	public void alugarBicicleta(String cpf, long codigoEstacao, long codigoBicicleta) {
		if (this.cliente.existe(cpf)) {
			if (this.estacao.existe(codigoEstacao)) {
				int indiceBicicletaNoArray = this.estacao.procurar(codigoEstacao).retornaIndiceBicicleta(codigoBicicleta);
				if (this.estacao.procurar(codigoEstacao).getBicicleta().get(indiceBicicletaNoArray).getAlugou() == false) {
					try {
						Cliente c = this.cliente.procurar(cpf);
						Estacao e = this.estacao.procurar(codigoEstacao);
						Aluguel a = new Aluguel(e, c, Calendar.getInstance(Locale.getDefault()));
						this.aluguel.cadastrar(a);
					} catch (RepositorioException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void devolverBicicleta(String cpf, long codigoEstacao,
			long codigoBicicleta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void procurarAdministrador(String cpf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarAdministrador(Administrador adm)
			throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluirAdministrador(String cpf) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarAluguel(Aluguel adm) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void procurarAluguel(String cpf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarAluguel(Aluguel adm) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluirAluguel(String cpf) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarCliente(Cliente cliente) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void procurarCliente(String cpf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarCliente(Cliente cliente) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluirCliente(String cpf) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cadastrarEstacao(Estacao estacao) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void procurarEstacao(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterarEstacao(Estacao estacao) throws RepositorioException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluirEstacao(long id) throws RepositorioException {
		// TODO Auto-generated method stub

	}

}