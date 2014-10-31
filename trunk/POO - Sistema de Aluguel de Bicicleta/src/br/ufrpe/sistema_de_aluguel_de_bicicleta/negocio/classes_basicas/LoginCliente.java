package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class LoginCliente {

	private Usuario nomeUsuario;
	private int senhaLogin; // código de acesso único do usuário

	public LoginCliente(Usuario nomeUsuario, int senhaLogin) {
		this.nomeUsuario = nomeUsuario;
		this.senhaLogin = senhaLogin;
	}

	public Usuario getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(Usuario nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(int senhaLogin) {
		this.senhaLogin = senhaLogin;
	}
}
