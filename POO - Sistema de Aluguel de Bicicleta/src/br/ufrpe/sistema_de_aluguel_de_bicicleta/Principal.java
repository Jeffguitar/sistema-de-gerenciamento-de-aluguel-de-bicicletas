package br.ufrpe.sistema_de_aluguel_de_bicicleta;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.Fachada;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.IFachada;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Contato;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Endereco;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.InicioSistemaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.OpcaoInvalidaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;

public class Principal {

	private static IFachada fachada;
	private static Scanner read = new Scanner(System.in);
	private static int opcao;

	private static void inicializarSistema() throws ClassNotFoundException,
			RepositorioException, ClienteJaCadastradoException,
			InicioSistemaException {
		fachada = new Fachada();
	}

	private static void verificarOpcao(int opcao2, int i, int f)
			throws OpcaoInvalidaException {
		if (opcao < i || opcao > f)
			throw new OpcaoInvalidaException();
	}

	public static void main(String[] args) {

		try {
			inicializarSistema();
		} catch (InicioSistemaException | ClassNotFoundException
				| RepositorioException | ClienteJaCadastradoException e) {
			System.err.println(e);
		}

		do {
			try {
				System.out.println("Menu:");
				System.out.println("(1) Clientes");
				System.out.println("(2) Funcionários");
				System.out.println("(3) Mesas");
				System.out.println("(4) Estoque");
				System.out.println("(0) Sair");
				System.out.print("Opcção: ");
				opcao = read.nextInt();

				verificarOpcao(opcao, 0, 4);

				if (opcao == 0)
					break;

				switch (opcao) {
				case 1:
					menuClientes();
					break;
				case 2:
					menuFuncionarios();
					break;
				case 3:
					menuMesas();
					break;
				case 4:
					menuEstoque();
					break;
				default:
					break;
				}

				System.out.println();

			} catch (OpcaoInvalidaException e) {
				System.err.println(e);
				System.out.println();
			}
		} while (true);
	}

	private static void menuClientes() {

		do {
			try {
				System.out.println("\nClientes:");
				System.out.println("(1) Cadastrar Cliente");
				System.out.println("(2) Atualizar Cadastro");
				System.out.println("(3) Remover Cadastro");
				System.out.println("(4) Consultar Cadastro do Cliente");
				System.out.println("(5) Aluguar Bicicleta");
				System.out.println("(6) Devolver Bicicleta");
				System.out.println("(6) Emitir Recibo do Aluguel");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				opcao = read.nextInt();

				verificarOpcao(opcao, 0, 6);

				switch (opcao) {
				case 1:
					cadastrarClienteMenu();
					break;
				case 2:
					atualizarClienteMenu();
					break;
				case 3:
					removerCLienteMenu();
					break;
				case 4:
					consultarClienteMenu();
					break;
				case 5:
					alugarBicicleta();
					break;
				case 6:
					devolverBicicleta();
					break;
				default:
					break;
				}
			} catch (OpcaoMenuInvalidaException e) {
				System.err.println(e);
			}
		} while (opcao != 0);
	}

	private static void cadastrarClienteMenu() {

		Cliente cliente = new Cliente();
		Contato contato = new Contato();

		System.out.print("Informe o CPF: ");
		String cpf = read.next();

		if (fachada.existeCliente(cpf)){
			throw new ClienteJaCadastradoException(cpf);
			if(!validaCpf(cpf))
				throw new CpfInvalidoException();
		}
		else {

			do {
				System.out.println("\nCadastrar cliente (Sair = 0):");
				try {
					System.out.print("Informe o nome: ");
					String nome = read.nextLine();
					read.nextLine();
					letrasIniciaisMaiusculas(nome);
					if (nome.equalsIgnoreCase("0"))
						return;
					cliente.setNome(nome);
					break;
				} catch (NomePessoaInvalidaException e) {
					System.err.println(e);
				}
			} while (true);

			do {
				try {
					this
					if (cpf.equalsIgnoreCase("0"))
						return;
					cliente.setCpf(cpf);
					break;
				} catch (NumeroCPFException e) {
					System.err.println(e);
				}
			} while (true);

			do {
				try {
					System.out.print("Informe o RG: ");
					String identidade = read.next();

					if (identidade.equalsIgnoreCase("0"))
						return;

					cliente.setIdentidade(identidade);
					break;
				} catch (NumeroRGException | ClienteJaCadastradoException e) {
					System.err.println(e);
				}
			} while (true);

			do {
				try {
					System.out.print("Informe o telefone: ");
					String telefone = read.next();

					if (telefone.equalsIgnoreCase("0"))
						return;

					contato.setTelefone(telefone);
					break;
				} catch (TelefoneException e) {
					System.err.println(e);
					System.out.println();
				}
			} while (true);

			do {
				try {
					System.out.print("Informe o e-mail: ");
					String email = read.next();
		
					if (email.equalsIgnoreCase("0") && !isEmailValido(email))
						return;

					contato.setEmail(email);
					cliente.setContato(contato);
					
					break;
					
				} catch (EmailException e) {
					System.err.println(e);
				}
			} while (true);

			System.out.println("Endereço:");
			Endereco endereco = new Endereco();

			do {
				try {
					System.out.print("Informe a rua: ");
					read.nextLine();
					String rua = read.nextLine();

					if (rua.equalsIgnoreCase("0"))
						return;

					endereco.setEndereco(rua);
					break;
				} catch (RuaException e) {
					System.err.println(e);
				}
			} while (true);

			do {
				try {
					System.out.print("Informe o CEP: ");
					String cep = read.next();

					if (cep.equalsIgnoreCase("0"))
						return;

					endereco.setCep(cep);
					break;
				} catch (CepException e) {
					System.err.println(e);
				}
			} while (true);

			// do {
			// try {
			// System.out.print("Informe o número: ");
			// String numero = read.next();
			//
			// if (numero.equalsIgnoreCase("0"))
			// return;
			//
			// endereco.setNumero(numero);
			// break;
			// } catch (NumeroEnderecoException e) {
			// System.err.println(e);
			// }
			// } while (true);

			// do {
			// try {
			// System.out.print("Informe o complemento: ");
			// read.nextLine();
			// String complemento = read.nextLine();
			//
			// if (complemento.equalsIgnoreCase("0"))
			// return;
			//
			// endereco.setComplemento(complemento);
			// break;
			// } catch (ComplementoEnderecoException e) {
			// System.err.println(e);
			// }
			// } while (true);

			do {
				try {
					System.out.print("Informe o bairro: ");
					String bairro = read.nextLine();
					if (bairro.equalsIgnoreCase("0"))
						return;

					endereco.setBairro(bairro);
					break;
				} catch (BairroException e) {
					System.err.println(e);
				}
			} while (true);

			do {
				try {
					System.out.print("Informe o cidade: ");
					String cidade = read.nextLine();
					if (cidade.equalsIgnoreCase("0"))
						return;

					endereco.setCidade(cidade);
					break;
				} catch (CidadeException e) {
					System.err.println(e);
				}
			} while (true);

			cliente.setEndereco(endereco);

			try {
				fachada.cadastrarCliente(cliente);
				System.out.println("\nCadastro realizado com sucesso!\n");
			} catch (RepositorioException | ClienteJaCadastradoException e) {
				System.err.println(e);
			}
		}
	}

	private static void consultarClienteMenu() {
		do {
			System.out.println("\nConsultar cadastro de cliente (Sair = 0): ");
			try {
				System.out.print("Informe o CPF: ");
				String cpf = read.next();

				if (!fachada.existeCliente(cpf)) {
					throw new ClienteNaoCadastradoException(cpf);
					if (!validaCpf(cpf))
						throw new CpfInvalidoException();
				} else {
					if (cpf.equalsIgnoreCase("0"))
						return;

					Cliente cliente = fachada.procurarCliente(cpf);

					if (cliente == null)
						throw new ClienteNaoCadastradoException(cpf);

					System.out.println("\nDados cadastrais:");
					System.out.println(cliente);
					System.out.println();
				}
			} catch (ClienteNaoCadastradoException e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void removerCLienteMenu() {
		do {
			System.out.println("\nRemover cadastro de cliente (Sair = 0):");
			try {
				System.out.print("\nInforme o CPF: ");
				String cpf = read.next();

				if (!fachada.existeCliente(cpf)) {
					throw new ClienteNaoCadastradoException(cpf);
					if (!validaCpf(cpf))
						throw new CpfInvalidoException();
				} else {

					if (cpf.equalsIgnoreCase("0"))
						return;

					Cliente cliente = fachada.procurarCliente(cpf);
					if (cliente != null) {
						System.out.println();
						System.out.println(cliente);
						String resposta;
						do {
							try {
								System.out
										.println("\nDeseja realmente remover o cadastro desse cliente? (S/N)");
								System.out.print("Opção: ");
								resposta = read.next();
								if (!resposta.equalsIgnoreCase("s")
										&& !resposta.equalsIgnoreCase("n"))
									throw new OpcaoMenuInvalidaException();
								else if (resposta.equalsIgnoreCase("s")) {
									fachada.excluirCliente(cpf);
									System.out
											.println("\nCadastro de cliente removido com sucesso!");
									break;
								} else {
									System.out
											.println("\nRemoção de cadastro de cliente cancelada!");
									break;
								}
							} catch (OpcaoMenuInvalidaException e) {
								System.err.println(e);
							}
						} while (true);

					} else
						throw new ClienteNaoCadastradoException(cpf);
				}
			} catch (ClienteNaoCadastradoException | RepositorioException e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void alugarBicicleta() {

		do {
			System.out.println("\nAluguel de Bicicleta (Sair = 0):");
			try {
				System.out.print("\nInforme o CPF: ");
				String cpf = read.next();
				System.out.print("\nInforme o código da estação: ");
				Long codEstacao = read.nextLong();
				System.out.print("\nInforme o código da bicicleta: ");
				Long codBicicleta = read.nextLong();

				if (!fachada.existeCliente(cpf)) {
					throw new ClienteNaoCadastradoException(cpf);
					if (!validaCpf(cpf))
						throw new CpfInvalidoException();
				} else {

					if (cpf.equalsIgnoreCase("0"))
						return;

					String resposta;
					do {
						try {
							System.out
									.println("\nDeseja confirmar o aluguel? (S/N)");
							System.out.print("Opção: ");
							resposta = read.next();
							if (!resposta.equalsIgnoreCase("s")
									&& !resposta.equalsIgnoreCase("n"))
								throw new OpcaoMenuInvalidaException();
							else if (resposta.equalsIgnoreCase("s")) {
								fachada.alugarBicicleta(cpf, codEstacao,
										codBicicleta);

								System.out.println("\nAluguel confirmado!");
								break;
							} else {
								System.out.println("\nAluguel não realizado!");
								break;
							}
						} catch (OpcaoMenuInvalidaException e) {
							System.err.println(e);
						}
					} while (true);

				}
			} catch (ClienteNaoCadastradoException | RepositorioException e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void devolverBicicleta() {

		do {
			System.out.println("\nDevoluçao de Bicicleta (Sair = 0):");
			try {
				System.out.print("\nInforme o CPF: ");
				String cpf = read.next();
				System.out.print("\nInforme o código da estação: ");
				Long codEstacao = read.nextLong();
				System.out.print("\nInforme o código da bicicleta: ");
				Long codBicicleta = read.nextLong();

				if (!fachada.existeCliente(cpf)) {
					throw new ClienteNaoCadastradoException(cpf);
					if (!validaCpf(cpf))
						throw new CpfInvalidoException();
				} else {

					if (cpf.equalsIgnoreCase("0"))
						return;

					String resposta;
					do {
						try {
							System.out
									.println("\nDeseja confirmar a devolução? (S/N)");
							System.out.print("Opção: ");
							resposta = read.next();
							if (!resposta.equalsIgnoreCase("s")
									&& !resposta.equalsIgnoreCase("n"))
								throw new OpcaoMenuInvalidaException();
							else if (resposta.equalsIgnoreCase("s")) {
								fachada.devolverBicicleta(cpf, codEstacao,
										codBicicleta);

								System.out.println("\nAluguel confirmado!");
								break;
							} else {
								System.out.println("\nAluguel não realizado!");
								break;
							}
						} catch (OpcaoMenuInvalidaException e) {
							System.err.println(e);
						}
					} while (true);

				}
			} catch (ClienteNaoCadastradoException | RepositorioException e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void menuFuncionarios() {
		do {
			try {
				System.out.println("\nFuncionários:");
				System.out.println("(1) Cadastrar Estação");
				System.out.println("(2) Atualizar dados da Estação");
				System.out.println("(3) Remover Estação");
				System.out.println("(4) Consultar dados da Estação");
				System.out.println("(5) Exibir Aluguéis da Estação");
				System.out.println("(6) Exibir Clientes Cadastrados");
				System.out.println("(7) Exibir Clientes Ativos");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				opcao = read.nextInt();

				verificarOpcao(opcao, 0, 5);

				switch (opcao) {
				case 1:
					cadastrarEstacaoMenu();
					break;
				case 2:
					atualizarEstacaoMenu();
					break;
				case 3:
					removerEstacaoMenu();
					break;
				case 4:
					consultarEstacaoMenu();
					break;
				case 5:
					exibirAluguel();
					break;
				case 6:
					exibirClientesCadastrados();
					break;
				case 7:
					exibirClientesAtivos();
					break;
				default:
					break;
				}
			} catch (OpcaoMenuInvalidaException e) {
				System.err.println(e);
			}
		} while (opcao != 0);
	}

	public static String letrasIniciaisMaiusculas(String oNome) {
		String frase = oNome;
		frase = frase.toLowerCase();
		StringBuffer frase2 = new StringBuffer(frase);
		for (int i = 0; i < frase2.length(); i++) {
			Character letra = frase2.charAt(i);
			if (i == 0) {
				letra = Character.toUpperCase(letra);
				frase2.setCharAt(i, letra);
			} else if ((i > 0) && (frase2.charAt(i - 1) == ' ')) {
				letra = Character.toUpperCase(letra);
				frase2.setCharAt(i, letra);
			}
		}
		frase = frase2.toString();
		return frase;
	}

	private static boolean isEmailValido(String email) {
		if (email != null) {
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = p.matcher(email);
			boolean matchFound = m.matches();
			if (matchFound) {
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("parametro email null");
			return false;
		}
	}

	private static boolean validaCpf(String strCpf) {
		strCpf = strCpf.replace('.', ' ');// onde há ponto coloca espaço
		strCpf = strCpf.replace('/', ' ');// onde há barra coloca espaço
		strCpf = strCpf.replace('-', ' ');// onde há traço coloca espaço
		strCpf = strCpf.replaceAll(" ", "");// retira espaço
		if (strCpf.equals("00000000000") || strCpf.equals("11111111111")
				|| strCpf.equals("22222222222") || strCpf.equals("33333333333")
				|| strCpf.equals("44444444444") || strCpf.equals("55555555555")
				|| strCpf.equals("66666666666") || strCpf.equals("77777777777")
				|| strCpf.equals("88888888888") || strCpf.equals("99999999999")) {
			return false;
		}
		if (strCpf.equals("")) {
			return false;
		}
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;
		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;
		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount))
					.intValue();
			d1 = d1 + (11 - nCount) * digitoCPF;
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		resto = (d1 % 11);
		if (resto < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}
		d2 += 2 * digito1;
		resto = (d2 % 11);
		if (resto < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}
		String nDigVerific = strCpf.substring(strCpf.length() - 2,
				strCpf.length());
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
		return nDigVerific.equals(nDigResult);
	}
}
