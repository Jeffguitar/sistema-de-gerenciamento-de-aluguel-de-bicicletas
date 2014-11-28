package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;

public class RepositorioClienteArray {
	private Cliente usuario[];
	private int proximo;
	private static RepositorioClienteArray repositorio;

	private RepositorioClienteArray(int tamanho) {
		this.usuario = new Cliente[tamanho];
		this.proximo = 0;
	}
	
	public static RepositorioClienteArray  getInstance(){
		if(repositorio == null){
			repositorio = new RepositorioClienteArray(100);
		}
		return repositorio;
	}

	private void duplicaArrayUsuario() {
		if (this.usuario != null && this.usuario.length > 0) {
			Cliente arrayUsuarioDobrado[] = new Cliente[this.usuario.length * 2];
			for (int i = 0; i < this.usuario.length; i++) {
				arrayUsuarioDobrado[i] = this.usuario[i];
			}
			this.usuario = arrayUsuarioDobrado;
		}
	}

	private int procurarPeloIndice(String cpf) {
		int indice = -1;

		if (cpf != null) {
			for (int i = 0; i < this.usuario.length; i++) {
				if (this.usuario[i].getCpf().equals(cpf)) {
					indice = 1;
				}
			}
		}
		return indice;
	}

	public Cliente procurarCliente(String cpf) {
		Cliente retornoBusca = null;

		for (int i = 0; i < this.usuario.length; i++) {
			if (this.usuario[i].getCpf().equals(cpf)) {
				retornoBusca = this.usuario[i];

			}
		}
		return retornoBusca;
	}

	public void cadastrarUsuario(Cliente usuario) {

		this.usuario[this.proximo] = usuario;
		this.proximo += 1;
		if (this.proximo == this.usuario.length) {
			this.duplicaArrayUsuario();
		}
	}

	/*
	 * public void alugarBicicleta(long codigoEstacao, int codigoBicicleta) {
	 * boolean retorno; retorno = repositorio.isAlugada(codigoEstacao,
	 * codigoBicicleta);
	 * 
	 * }
	 */

	public Cliente alterarCliente(String cpf) {
		return this.procurarCliente(cpf);
	}

	public void excluirUsuario(String cpf) {

		if (this.procurarPeloIndice(cpf) != this.proximo) {
			this.usuario[this.procurarPeloIndice(cpf)] = this.usuario[this.proximo - 1];
			this.usuario[this.proximo - 1] = null;
			this.proximo = this.proximo - 1;
		}
	}
}
