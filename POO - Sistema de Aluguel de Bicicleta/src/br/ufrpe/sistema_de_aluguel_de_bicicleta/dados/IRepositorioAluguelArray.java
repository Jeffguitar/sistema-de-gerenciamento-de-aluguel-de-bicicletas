package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelAtivoInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelComMultaInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInativoInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public interface IRepositorioAluguelArray {

	public void cadastrarAluguel(Aluguel aluguel) throws RepositorioException,
			AluguelInexistenteException;

	public Aluguel procurarAluguel(String cpf, long idBicicleta)
			throws AluguelInexistenteException;

	public Aluguel procurarAluguelFinalizado(String cpf, long idBicicleta)
			throws AluguelInexistenteException;

	public Aluguel procurarAluguel(long id) throws AluguelInexistenteException;

	public void alterarAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException;

	public boolean existeAluguel(String cpf, long idBicicleta);

	public void excluirAluguel(String cpf, long idBicicleta)
			throws RepositorioException, AluguelInexistenteException;

	public List<Aluguel> exibirALuguelComMulta()
			throws AluguelComMultaInexistenteException;

	public List<Aluguel> exibirALuguelFinalizadoEstacao()
			throws AluguelInativoInexistenteException;

	public List<Aluguel> exibirALuguelAtivo()
			throws AluguelAtivoInexistenteException;
}
