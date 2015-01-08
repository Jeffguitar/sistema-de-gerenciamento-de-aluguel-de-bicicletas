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

public class RepositorioAdministradorArray implements
		IRepositorioAdministradorArray {
	private List<Administrador> listaAdm;
	private final String ARQUIVO = "adms.dat";
	private File arquivoAdm;

	public RepositorioAdministradorArray() throws RepositorioException,
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

	private void lerArquivo() throws RepositorioException,
			ClassNotFoundException {
		FileInputStream fisAdministrador = null;
		ObjectInputStream oisAdministrador = null;
		try {
			fisAdministrador = new FileInputStream(arquivoAdm);
			oisAdministrador = new ObjectInputStream(fisAdministrador);
			while (true) {
				try {
					@SuppressWarnings("unchecked")
					ArrayList<Administrador> adm = (ArrayList<Administrador>) oisAdministrador
							.readObject();
					for (Administrador a : adm)
						this.cadastrarAdministrador(a);
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
		FileOutputStream fosAdministrador = null;
		ObjectOutputStream oosAdministrador = null;
		try {
			fosAdministrador = new FileOutputStream(arquivoAdm);
			oosAdministrador = new ObjectOutputStream(fosAdministrador);
			oosAdministrador.writeObject(listaAdm);
			oosAdministrador.reset();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RepositorioException("Erro na gravação do arquivo "
					+ this.ARQUIVO + ".");
		} finally {
			try {
				oosAdministrador.close();
				fosAdministrador.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Erro no fechamento do arquivo "
						+ this.ARQUIVO + ".");
			}
		}
	}

	@Override
	public void cadastrarAdministrador(Administrador adm)
			throws RepositorioException {
		this.listaAdm.add(adm);
		this.gravarArquivo();
	}

	@Override
	public Administrador procurarAdministrador(String cpf)
			throws AdministradorInexistenteException {
		int indice = this.obterIndice(cpf);
		if (indice == -1)
			throw new AdministradorInexistenteException(cpf);
		return this.listaAdm.get(indice);
	}

	@Override
	public void alterarAdministrador(Administrador adm)
			throws RepositorioException, AdministradorInexistenteException {
		int indice = this.obterIndice(adm.getCpf());
		if (indice == -1)
			throw new AdministradorInexistenteException(adm.getCpf());
		this.listaAdm.set(indice, adm);
		this.gravarArquivo();
	}

	@Override
	public boolean existe(String cpf) throws AdministradorInexistenteException {
		int indice = this.obterIndice(cpf);

		if (indice != -1)
			return true;
		else
			return false;
	}

	@Override
	public void excluirAdministrador(String cpf) throws RepositorioException,
			AdministradorInexistenteException {
		int indice = this.obterIndice(cpf);
		if (indice != -1) {
			this.listaAdm.remove(indice);
			this.gravarArquivo();
		} else
			throw new AdministradorInexistenteException(cpf);
	}

	private int obterIndice(String cpf) {
		int indice = -1;

		for (int i = 0; i < this.listaAdm.size(); i++) {
			if (this.listaAdm.get(i).getCpf().equals(cpf)) {
				indice = i;
			}
		}
		return indice;
	}
}
