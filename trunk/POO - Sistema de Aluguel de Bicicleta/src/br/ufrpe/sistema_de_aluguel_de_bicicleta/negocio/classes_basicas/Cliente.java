package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Cliente extends Usuario {
	private int horaInicio;
	private int minutoInicio;
	private int horaFim;
	private int minutoFim;

	public Cliente(String nome, String cpf, int identidade, String endereco,
			int idade, int horaInicio, int minutoInicio, int horaFim,
			int minutoFim) {
		super(endereco, endereco, minutoInicio, endereco, minutoInicio);
		this.setHoraInicio(horaInicio);
		this.setMinutoInicio(minutoInicio);
		this.setHoraInicio(horaInicio);
		this.setHoraFim(horaFim);
	}

	public int getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}

	public int getMinutoFim() {
		return minutoFim;
	}

	public void setMinutoFim(int minutoFim) {
		this.minutoFim = minutoFim;
	}

	public int getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(int minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
}
