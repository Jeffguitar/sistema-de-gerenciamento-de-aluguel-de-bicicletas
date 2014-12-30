package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class RepositorioAdministradorArray {
	private List<Administrador> listaAdm;
	private static RepositorioAdministradorArray repositorio;
	private final String ARQUIVO = "adms.dat";
	private File arquivoAdm;

	private RepositorioAdministradorArray() throws RepositorioException,
			ClassNotFoundException {
		try {
			this.listaAdm = new ArrayList<Administrador>();
			this.arquivoAdm = new File(this.ARQUIVO);
			this.arquivoAdm.createNewFile();
			if (this.arquivoAdm.length() != 0)
				this.lerArquivo();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na abertura do arquivo "
					+ this.ARQUIVO + ".");
		}
	}

	public static RepositorioAdministradorArray getInstance()
			throws ClassNotFoundException, RepositorioException {
		if (repositorio == null) {
			repositorio = new RepositorioAdministradorArray();
		}
		return repositorio;
	}

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisAdministrador = null;
		ObjectInputStream oisAdministrador = null;
		try {
			fisAdministrador = new FileInputStream(arquivoAdm);
			oisAdministrador = new ObjectInputStream(fisAdministrador);
			while (true) {
				try {
					Administrador adm = (Administrador) oisAdministrador
							.readObject();
					this.cadastrarAdministrador(adm);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na leitura do arquivo "
					+ this.ARQUIVO + ".");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na leitura do objeto. Objeto "
					+ (new RepositorioAdministradorArray()).getClass()
					+ " não encontrado.");
		} finally {
			try {
				fisAdministrador.close();
				oisAdministrador.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	private void gravarArquivo() throws RepositorioException {
		FileOutputStream fosRestaurante = null;
		ObjectOutputStream oosRestaurante = null;
		try {
			fosRestaurante = new FileOutputStream(arquivoAdm);
			oosRestaurante = new ObjectOutputStream(fosRestaurante);
			for (Administrador adm : this.listaAdm)
				oosRestaurante.writeObject(adm);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na gravação do arquivo "
					+ this.ARQUIVO + ".");
		} finally {
			try {
				fosRestaurante.close();
				oosRestaurante.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException {
		this.listaAdm.add(adm);
		this.gravarArquivo();
	}

	public Administrador procurarAdministrador(String cpf)
			throws AdministradorInexistenteException {
		int indice = this.obterIndice(cpf);
		if (indice == -1)
			throw new AdministradorInexistenteException(cpf);
		return this.listaAdm.get(indice);
	}

	public void alterarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorInexistenteException {
		int indice = this.obterIndice(adm.getCpf());
		if (indice == -1)
			throw new AdministradorInexistenteException(adm.getCpf());
		this.listaAdm.set(indice, adm);
		this.gravarArquivo();
	}

	public boolean existe(String cpf) {
		boolean existe = false;
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return existe = true;
		return existe;
	}

	public boolean excluirAdministrador(String cpf) throws RepositorioException {
		int indice = this.obterIndice(cpf);
		// preicsa usar um try, catch, informando que o adm não existe
		if (indice != -1) {
			this.listaAdm.remove(indice);
			this.gravarArquivo();
			return true;
		}
		return false;
	}

	private int obterIndice(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.listaAdm.size(); i++) {
			if (this.listaAdm.get(i).getCpf().equals(cpf)) {
				indice = i;
			}
			// tratar um exceção do tipo se a conta não foi encontrada
		}
		return indice; // Retorna -1 se não encontrou
	}
}
