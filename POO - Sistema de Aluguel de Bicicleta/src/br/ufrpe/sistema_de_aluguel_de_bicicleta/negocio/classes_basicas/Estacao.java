package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long codigo;
	private String descricao;
	private List<Bicicleta> bicicleta;

	public Estacao(long codigo, String descricao, List<Bicicleta> bicicleta) {
		this.bicicleta = new ArrayList<Bicicleta>();
		this.codigo = codigo;
		this.descricao = descricao;
		this.bicicleta = bicicleta;
	}

	public Estacao(String descricao, List<Bicicleta> bicicleta) {
		this.descricao = descricao;
		this.bicicleta = bicicleta;
	}

	public Estacao() {

	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Bicicleta> getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(List<Bicicleta> bicicleta) {
		this.bicicleta = bicicleta;
	}

	public int retornaIndiceBicicleta(long codigoBicicleta) {
		return this.bicicleta.indexOf(codigoBicicleta);
	}

}
