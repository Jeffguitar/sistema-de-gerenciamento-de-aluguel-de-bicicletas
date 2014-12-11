package br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.dados.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Bicicleta;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Contato;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Endereco;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.SexoTipo;

public class TesteControlador {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, RepositorioException {
	
		ControladorAdministrador cAdmin = new ControladorAdministrador();
		ControladorAluguel cAluguel = new ControladorAluguel();
		ControladorCliente cCliente = new ControladorCliente();
		ControladorEstacao cEstacao = new ControladorEstacao();

		Endereco endereco = new Endereco("Rua Corrente, 262", "Paulista", "Janga", "53437390");
		Contato contato = new Contato("jeff-pe@live.com", "8184386001", "8188351076");
		
		String dataNasc = "27/07/1992";
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataNasc);
         Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(date);
		
		Cliente cliente = new Cliente("Jefferson", "10541030469", "7644484", dataNascimento, SexoTipo.M, endereco, contato);
		
		List<Bicicleta> bike = new ArrayList<Bicicleta>();
		Bicicleta bicicleta = new Bicicleta();
		for (int i = 0; i < 10; i++) {
			bicicleta.setCodigo(i + 1);
			bicicleta.setAlugou(false);
			bike.add(bicicleta);
		}
		// Implementando a lógica da data
		Calendar dataAluguel = Calendar.getInstance();
		Calendar dataDevolucao =  Calendar.getInstance();
		Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		dataDevolucao.set(2014, 11, 8, 23, 0); // Definindo uma data de devolucao
		String dataDevol = formato.format(dataDevolucao.getTime()); // Formatando a data de devolucao para impressão
		String dataAlug = formato.format(dataAluguel.getTime()); // Formatando a data de aluguel para impressão
		
//		int dia = d.get(Calendar.DAY_OF_MONTH) - p.getData().get(Calendar.DAY_OF_MONTH);
//		int hora = d.get(Calendar.HOUR_OF_DAY) - p.getData().get(Calendar.HOUR_OF_DAY);
//		int minuto = d.get(Calendar.MINUTE) - p.getData().get(Calendar.MINUTE);
//		
//		System.out.println(dia);
//		System.out.println(hora);
//		System.out.println(minuto);
//
//		if (dia == 0 && hora >= 2 && minuto > 0) {
//			System.out.println("Ultrapassou o limite de tempo e pagará multa de XX%!");
//		}
		
		// Montando a Estação e o Aluguel
		Estacao estacao = new Estacao(1, "Estacão Marco Zero", bike);
		cEstacao.cadastrar(estacao);
		cCliente.cadastrar(cliente);
		Aluguel aluguel = new Aluguel(1, estacao, cliente, dataAluguel, null, 0);
		cAluguel.procurar("10541030469").getEstacao().getBicicleta().get(2).setAlugou(true);
		cAluguel.procurar("10541030469").setDataAluguel(dataAluguel);
		cAluguel.cadastrar(aluguel);
		
		System.out.println(formato.format(cAluguel.procurar("10541030469").getDataAluguel().getTime()));
		System.out.println(cAluguel.procurar("10541030469").getCliente().getNome());
		//System.out.println(cAluguel.procurar("10541030469").getEstacao().gw);
	}
}