package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class LoginAdministrador {
	private long id;
	private Administrador administrador;
	private String senhaLogin;

	public LoginAdministrador(long id, Administrador administrador,
			String senhaLogin) {

		this.id = id;
		this.administrador = administrador;
		this.senhaLogin = senhaLogin;
	}

	public LoginAdministrador(Administrador administrador, String senhaLogin) {

		this.administrador = administrador;
		this.senhaLogin = senhaLogin;
	}

	public LoginAdministrador() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
