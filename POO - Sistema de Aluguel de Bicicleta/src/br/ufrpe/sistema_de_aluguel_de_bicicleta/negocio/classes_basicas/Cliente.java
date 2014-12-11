package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;
import java.util.Calendar;

public class Cliente extends Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String identidade;
	private Calendar dataNascimento;
	private SexoTipo sexo; // Enum
	private Endereco endereco;
	private Contato contato;

	public Cliente(String nome, String cpf, long id, String identidade,
			Calendar dataNascimento, SexoTipo sexo, Endereco endereco,
			Contato contato) {
		this(nome, cpf, identidade, dataNascimento, sexo, endereco, contato);
		this.id = id;
	}

	public Cliente(String nome, String cpf, String identidade,
			Calendar dataNascimento, SexoTipo sexo, Endereco endereco,
			Contato contato) {
		this(nome, cpf, identidade, dataNascimento, sexo);
		this.endereco = endereco;
		this.contato = contato;
	}

	public Cliente(String nome, String cpf, String identidade,
			Calendar dataNascimento, SexoTipo sexo) {
		this(nome, cpf);
		this.identidade = identidade;
		this.sexo = sexo;
	}

	public Cliente(String nome, String cpf) {
		super(nome, cpf);
	}

	public Cliente() {

	}

	@Override
	public String getNome() {

		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;

	}

	@Override
	public String getCpf() {

		return this.cpf;
	}

	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public SexoTipo getSexo() {
		return sexo;
	}

	public void setSexo(SexoTipo sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
