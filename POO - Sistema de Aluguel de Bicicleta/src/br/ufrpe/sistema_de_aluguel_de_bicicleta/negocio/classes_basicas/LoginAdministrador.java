package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class LoginAdministrador {
	private Administrador administrador;
	private String senhaLogin;

	public LoginAdministrador(Administrador administrador, String senhaLogin) {
		this.administrador = administrador;
		this.senhaLogin = senhaLogin;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}
}
