package br.ufrpe.sistema_de_aluguel_de_bicicleta.dados;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.RelatorioCliente;

public class RepositorioRelatorioArray {
	private RelatorioCliente relatorio[];
	private int proximo;
	private static RepositorioRelatorioArray repositorio;

	private RepositorioRelatorioArray(int tamanho) {
		this.relatorio = new RelatorioCliente[tamanho];
		this.proximo = 0;
	}
	
	public static RepositorioRelatorioArray  getInstance(){
		if(repositorio == null){
			repositorio = new RepositorioRelatorioArray(100);
		}
		return repositorio;
	}

	private void duplicaArrayUsuario() {
		if (this.relatorio != null && this.relatorio.length > 0) {
			RelatorioCliente arrayUsuarioDobrado[] = new RelatorioCliente[this.relatorio.length * 2];
			for (int i = 0; i < this.relatorio.length; i++) {
				arrayUsuarioDobrado[i] = this.relatorio[i];
			}
			this.relatorio = arrayUsuarioDobrado;
		}
	}
}
