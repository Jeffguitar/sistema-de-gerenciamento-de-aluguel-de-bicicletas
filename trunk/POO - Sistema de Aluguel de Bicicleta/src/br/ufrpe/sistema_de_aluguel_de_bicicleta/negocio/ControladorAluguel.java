package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioAluguelArray;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;

public class ControladorAluguel {
	private RepositorioAluguelArray repositorio;

	public ControladorAluguel() throws ClassNotFoundException,
			RepositorioException {
		this.repositorio = RepositorioAluguelArray.getInstance();
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
