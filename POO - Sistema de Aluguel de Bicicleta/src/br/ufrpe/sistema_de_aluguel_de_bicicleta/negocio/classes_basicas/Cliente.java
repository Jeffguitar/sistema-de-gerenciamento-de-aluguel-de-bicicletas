package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

public class Cliente extends Usuario {
	private int identidade;
	private String endereco;
	private int horaInicio; // hora do aluguel
	private int minutoInicio; // minuto do aluguel
	private int horaFim; // hora da devolucao
	private int minutoFim; // minuto da devolucao

	public Cliente(String nome, String cpf, int identidade, String endereco,
			int horaInicio, int minutoInicio, int horaFim, int minutoFim) {
		super(nome, cpf);
		this.identidade = identidade;
		this.endereco = endereco;
		this.horaInicio = 0;
		this.minutoInicio = 0;
		this.horaFim = 0;
		this.minutoFim = 0;
	}

	public int getIdentidade() {
		return identidade;
	}

	public void setIdentidade(int identidade) {
		this.identidade = identidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(int minutoInicio) {
		this.minutoInicio = minutoInicio;
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

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;

	}

	@Override
	public String getCpf() {
		// TODO Auto-generated method stub
		return this.cpf;
	}

	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;

	}

}
