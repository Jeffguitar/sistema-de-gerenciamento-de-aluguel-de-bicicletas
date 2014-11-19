package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class LoginCliente {

	private Cliente cliente;
	private int senhaLogin; // c�digo de acesso �nico do usu�rio

	public LoginCliente(Cliente cliente, int senhaLogin) {
		this.cliente = cliente;
		this.senhaLogin = senhaLogin;
	}

	public Usuario getcliente() {
		return cliente;
	}

	public void setNomeUsuario(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(int senhaLogin) {
		this.senhaLogin = senhaLogin;
	}
}
