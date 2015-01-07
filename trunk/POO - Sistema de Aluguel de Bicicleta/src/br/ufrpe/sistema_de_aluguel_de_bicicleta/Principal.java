package br.ufrpe.sistema_de_aluguel_de_bicicleta;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.Fachada;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.IFachada;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Administrador;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Aluguel;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Bicicleta;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Cliente;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Contato;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Endereco;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.Estacao;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.classes_basicas.SexoTipo;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorJaExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AdministradorNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelAtivoInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelComMultaInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInativoInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.AluguelInexistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.BairroException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.BicicletaIndisponivelException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.CepException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.CidadeException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaAlugouBicicletaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteJaCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoAlugouBicicletaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClienteNaoCadastradoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.ClientesInexistentesException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.CpfInvalidoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.DescricaoInvalidaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EmailException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoExistenteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.EstacaoNaoExisteException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.IdIncorreto;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.InicioSistemaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.NomePessoaInvalidaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.NumeroBicicletaInvalidoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.NumeroCPFException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.NumeroRGException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.OpcaoInvalidaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RepositorioException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.RuaException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.SexoInvalidoException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.TelefoneException;
import br.ufrpe.sistema_de_aluguel_de_bicicleta.negocio.excecao.senhaInvalidaException;

public class Principal {

	private static IFachada fachada;
	private static Scanner read = new Scanner(System.in);
	private static int opcao;

	public static void main(String[] args) {

		try {
			inicializarSistema();
		} catch (InicioSistemaException | ClassNotFoundException
				| RepositorioException | ClienteJaCadastradoException e) {
			System.err.println(e);
		}
		do {
			do {

				String resp;
				try {
					System.out
							.println("-----BEM VINDO AO GERENCIADOR DO SISTEMA DE ALUGUEL DE BICICLETAS (SAB)-----");
					System.out.println();
					System.out
							.println("Já possui cadastro de administrador? (Sair = 0)");
					resp = read.nextLine();
					if (resp.equalsIgnoreCase("0"))
						return;
					if (!resp.equalsIgnoreCase("s")
							&& !resp.equalsIgnoreCase("n"))
						throw new OpcaoInvalidaException();
					else if (resp.equalsIgnoreCase("n")) {
						cadastrarAdministrador();
					} else if (resp.equalsIgnoreCase("s")) {

						System.out
								.println("Informe seu CPF para acessar o sistema: (Sair = 0)");
						String cpf = read.nextLine();
						if (cpf.equalsIgnoreCase("0"))
							break;
						if (fachada.existeAdministrador(cpf)) {
							do {

								System.out.println("Menu:");
								System.out.println("(1) Clientes");
								System.out.println("(2) Funcionários");
								System.out.println("(0) Sair");
								System.out.print("Opcção: ");
								opcao = read.nextInt();

								verificarOpcao(opcao, 0, 2);

								if (opcao == 0)
									break;

								switch (opcao) {
								case 1:
									menuClientes();
									break;
								case 2:
									menuFuncionarios();
									break;
								default:
									break;
								}

								System.out.println();
							} while (true);

						} else
							throw new AdministradorNaoCadastradoException(
									"Você não cadastrou sua conta de administrador!");

					} else {
						throw new AdministradorNaoCadastradoException(
								"Você precisa cadastrar um administrador para poder acessar o sistema!");
					}

				} catch (OpcaoInvalidaException
						| AdministradorNaoCadastradoException
						| AdministradorInexistenteException e) {
					System.err.println(e);
					System.out.println();
				}
			} while (true);
		} while (true);
	}

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

	private static void cadastrarAdministrador() {

		Administrador administrador = new Administrador();

		System.out.print("Informe o CPF: ");
		String cpf = read.nextLine();

		try {
			if (!fachada.existeAdministrador(cpf)) {
				do {
					System.out.println("\nCadastrar Administrador (Sair = 0):");
					System.out.print("Informe o nome: ");
					String nome = read.nextLine();
					if (nome != null) {
						letrasIniciaisMaiusculas(nome);
						if (nome.equalsIgnoreCase("0"))
							return;
						administrador.setNome(nome);
						break;
					} else
						throw new NomePessoaInvalidaException();
				} while (true);

				do {
					if (cpf != null) {
						if (cpf.equalsIgnoreCase("0"))
							return;
						administrador.setCpf(cpf);
						break;
					} else
						throw new NumeroCPFException();
				} while (true);

				do {
					System.out.print("Informe a senha de login: (Sair = 0)");
					String senha = read.nextLine();

					if (senha != null) {
						if (senha.equalsIgnoreCase("0"))
							return;

						System.out.print("Confirme a senha: ");
						String novaSenha = read.nextLine();

						if (novaSenha != null) {
							if (novaSenha.equalsIgnoreCase("0"))
								return;
							if (senha.equals(novaSenha)) {
								administrador.setLogin(senha);
								break;
							} else
								throw new senhaInvalidaException(
										"As senhas não conferem. Por favor, insira-as novamente.");
						} else {
							throw new senhaInvalidaException("Senha inválida!");
						}
					} else {
						throw new senhaInvalidaException("Senha inválida!");
					}
				} while (true);
				try {
					fachada.cadastrarAdministrador(administrador);
					System.out.println("\nCadastro realizado com sucesso!\n");
				} catch (RepositorioException
						| AdministradorInexistenteException e) {
					System.err.println(e);

				}
			} else {
				throw new AdministradorJaExistenteException(
						administrador.getCpf());
			}
		} catch (NomePessoaInvalidaException | NumeroCPFException
				| senhaInvalidaException | AdministradorJaExistenteException
				| AdministradorInexistenteException e) {
			System.err.println(e);
		}

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
				System.out.println("(7) Emitir Recibo do Aluguel");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				opcao = read.nextInt();

				verificarOpcao(opcao, 0, 7);

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
					alugarBicicletaMenu();
					break;
				case 6:
					devolverBicicletaMenu();
					break;
				case 7:
					emitirReciboMenu();
					break;
				default:
					break;
				}
			} catch (OpcaoInvalidaException e) {
				System.err.println(e);
			}
		} while (opcao != 0);
	}

	private static void cadastrarClienteMenu() {

		Cliente cliente = new Cliente();
		Contato contato = new Contato();

		System.out.print("Informe o CPF: ");
		String cpf = read.next();
		try {
			if (!fachada.existeCliente(cpf)) {
				do {
					System.out.println("\nCadastrar cliente (Sair = 0):");
					System.out.print("Informe o nome: ");
					read.nextLine();
					String nome = read.nextLine();
					if (nome != null) {
						letrasIniciaisMaiusculas(nome);
						if (nome.equalsIgnoreCase("0"))
							return;
						cliente.setNome(nome);
						break;
					} else
						throw new NomePessoaInvalidaException();
				} while (true);

				do {
					if (cpf != null) {
						if (cpf.equalsIgnoreCase("0"))
							return;
						cliente.setCpf(cpf);
						break;
					} else
						throw new NumeroCPFException();
				} while (true);

				do {
					System.out.print("Informe o RG: ");
					String identidade = read.nextLine();
					if (identidade != null) {

						if (identidade.equalsIgnoreCase("0"))
							return;

						cliente.setIdentidade(identidade);
						break;
					} else
						throw new NumeroRGException();
				} while (true);

				do {
					System.out.print("Informe o sexo: ");
					String sexo = read.nextLine();
					if (sexo != null) {
						if (sexo.equals(SexoTipo.F.getDescricao()))
							cliente.setSexo(SexoTipo.F);
						else
							cliente.setSexo(SexoTipo.M);

						if (sexo.equalsIgnoreCase("0"))
							return;

						break;
					} else
						throw new SexoInvalidoException();
				} while (true);

				do {
					System.out.print("Informe o telefone: ");
					String telefone = read.nextLine();
					if (telefone != null) {
						if (telefone.equalsIgnoreCase("0"))
							return;

						contato.setTelefone(telefone);
						break;
					} else
						throw new TelefoneException();
				} while (true);

				do {
					System.out.print("Informe o e-mail: ");
					String email = read.nextLine();
					if (email != null) {
						if (email.equalsIgnoreCase("0")
								&& !isEmailValido(email))
							return;

						contato.setEmail(email);
						cliente.setContato(contato);

						break;
					} else
						throw new EmailException();
				} while (true);

				System.out.println("Endereço");
				Endereco endereco = new Endereco();

				do {
					System.out.print("Informe a rua: ");
					String rua = read.nextLine();
					if (rua != null) {
						if (rua.equalsIgnoreCase("0"))
							return;
						endereco.setEndereco(rua);
						break;
					} else
						throw new RuaException();
				} while (true);

				do {
					System.out.print("Informe o CEP: ");
					String cep = read.nextLine();
					if (cep != null) {
						if (cep.equalsIgnoreCase("0"))
							return;
						endereco.setCep(cep);
						break;
					} else
						throw new CepException();
				} while (true);

				do {
					System.out.print("Informe o bairro: ");
					String bairro = read.nextLine();
					if (bairro != null) {
						if (bairro.equalsIgnoreCase("0"))
							return;

						endereco.setBairro(bairro);
						break;
					} else
						throw new BairroException();
				} while (true);

				do {
					System.out.print("Informe o cidade: ");
					String cidade = read.nextLine();
					if (cidade != null) {
						if (cidade.equalsIgnoreCase("0"))
							return;

						endereco.setCidade(cidade);
						break;
					} else
						throw new CidadeException();
				} while (true);
				cliente.setEndereco(endereco);
				try {
					fachada.cadastrarCliente(cliente);
					System.out.println("\nCadastro realizado com sucesso!\n");
				} catch (RepositorioException | ClienteJaCadastradoException e) {
					System.err.println(e);

				}
			} else
				throw new ClienteJaCadastradoException(cpf);
		} catch (ClienteNaoCadastradoException | NomePessoaInvalidaException
				| NumeroCPFException | NumeroRGException
				| SexoInvalidoException | TelefoneException | EmailException
				| RuaException | CepException | BairroException
				| CidadeException | ClienteJaCadastradoException e) {
			System.err.println(e);
		}
	}

	private static void atualizarClienteMenu() {
		do {
			System.out.println("\nAtualizar cadastro de cliente (Sair = 0): ");
			try {
				System.out.print("\nInforme o CPF: ");
				String cpf = read.next();

				if (cpf.equalsIgnoreCase("0"))
					return;

				Cliente cliente = fachada.procurarCliente(cpf);

				do {
					try {
						System.out.println("Escolha o que deseja atualizar:");
						System.out.println("\n(1) Atualizar nome");
						System.out.println("(2) Atualizar telefone");
						System.out.println("(3) Atualizar e-mail");
						System.out.println("(4) Atualizar rua");
						System.out.println("(5) Atualizar cep");
						System.out.println("(6) Atualizar bairro");
						System.out.println("(7) Atualizar cidade");
						System.out.println("(0) Sair");
						System.out.print("Opção: ");
						opcao = read.nextInt();

						verificarOpcao(opcao, 0, 7);

						if (opcao == 0)
							break;

						switch (opcao) {
						case 1:
							do {
								try {
									System.out.println("\nNome atual: "
											+ cliente.getNome());
									System.out
											.print("Informe o novo nome (Cancelar = 0): ");
									read.nextLine();
									String novoNome = read.nextLine();
									if (novoNome != null) {
										if (novoNome.equalsIgnoreCase("0"))
											break;
										cliente.setNome(novoNome);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nNome do cliente atualizado com sucesso!");
										break;
									} else
										throw new NomePessoaInvalidaException();
								} catch (RepositorioException
										| NomePessoaInvalidaException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						case 2:
							do {
								try {
									System.out.println("\nTelefone atual: "
											+ cliente.getContato()
													.getTelefone());
									System.out
											.print("Informe o novo número do telefone (Cancelar = 0): ");
									String novoTel = read.next();
									if (novoTel != null) {
										if (novoTel.equalsIgnoreCase("0"))
											break;
										cliente.getContato().setTelefone(
												novoTel);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nTelefone do cliente atualizado com sucesso!");
										break;
									} else
										throw new TelefoneException();
								} catch (TelefoneException
										| RepositorioException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						case 3:
							do {
								try {
									System.out.println("\nE-mail atual: "
											+ cliente.getContato().getEmail());
									System.out
											.print("Informe o novo e-mail (Cancelar = 0): ");
									String novoEmail = read.next();
									if (novoEmail != null) {
										if (novoEmail.equalsIgnoreCase("0"))
											break;
										cliente.getContato()
												.setEmail(novoEmail);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nE-mail do cliente atualizado com sucesso!");
										break;
									} else
										throw new EmailException();
								} catch (EmailException | RepositorioException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						case 4:
							do {
								try {
									System.out.println("\nRua atual: "
											+ cliente.getEndereco()
													.getEndereco());
									System.out
											.print("Informe o novo endereço de rua (Cancelar = 0): ");
									String novoRua = read.nextLine();
									if (novoRua != null) {
										if (novoRua.equalsIgnoreCase("0"))
											break;
										cliente.getEndereco().setEndereco(
												novoRua);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nRua do endereço do cliente atualizado com sucesso!");
										break;
									} else
										throw new RuaException();
								} catch (RuaException | RepositorioException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						case 5:
							do {
								try {
									System.out.println("\nCEP atual: "
											+ cliente.getEndereco().getCep());
									System.out
											.print("Informe o novo CEP (Cancelar = 0): ");
									String novoCep = read.next();
									if (novoCep != null) {
										if (novoCep.equalsIgnoreCase("0"))
											break;
										cliente.getEndereco().setCep(novoCep);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nCEP de endereço do cliente atualizado com sucesso!");
										break;
									} else
										throw new CepException();
								} catch (CepException | RepositorioException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						case 6:
							do {
								try {
									System.out
											.println("\nBairro atual: "
													+ cliente.getEndereco()
															.getBairro());
									System.out
											.print("Informe o novo bairro (Cancelar = 0): ");
									read.nextLine();
									String novoBairro = read.nextLine();
									if (novoBairro != null) {
										if (novoBairro.equalsIgnoreCase("0"))
											break;
										cliente.getEndereco().setBairro(
												novoBairro);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nBairro do cliente atualizado com sucesso!");
										break;
									} else
										throw new BairroException();
								} catch (BairroException | RepositorioException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						case 7:
							do {
								try {
									System.out
											.println("\nCidade atual: "
													+ cliente.getEndereco()
															.getCidade());
									System.out
											.print("Informe a nova cidade (Cancelar = 0): ");
									read.nextLine();
									String novoCidade = read.nextLine();
									if (novoCidade != null) {
										if (novoCidade.equalsIgnoreCase("0"))
											break;
										cliente.getEndereco().setCidade(
												novoCidade);
										fachada.alterarCliente(cliente);
										System.out
												.println("\nCidade do cliente atualizada com sucesso!");
										break;
									} else
										throw new CidadeException();
								} catch (CidadeException | RepositorioException e) {
									System.err.println(e);
								}
							} while (true);
							break;
						default:
							break;
						}
					} catch (OpcaoInvalidaException e) {
						System.err.println(e);
					}
				} while (true);
			} catch (ClienteNaoCadastradoException e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void consultarClienteMenu() {
		do {
			System.out.println("\nConsultar cadastro de cliente (Sair = 0): ");
			try {
				System.out.print("Informe o CPF: ");
				String cpf = read.next();
				if (cpf != null) {
					if (cpf.equalsIgnoreCase("0"))
						return;
					if (fachada.existeCliente(cpf)) {

						Cliente cliente = fachada.procurarCliente(cpf);

						if (cliente == null)
							throw new ClienteNaoCadastradoException(cpf);

						System.out.println("\n-----Dados cadastrais-----");
						System.out.println();
						System.out.println("Usuário: " + cliente.getNome());
						System.out.println();
						System.out.println("CPF: " + cliente.getCpf());
						System.out.println();
						System.out.println("Sexo: "
								+ cliente.getSexo().getDescricao());
						System.out.println();
						System.out.println("Endereço: "
								+ cliente.getEndereco().getEndereco() + " - "
								+ cliente.getEndereco().getBairro() + " - "
								+ cliente.getEndereco().getCidade());
						System.out.println();
						System.out.println("Contato: "
								+ cliente.getContato().getTelefone() + " - "
								+ "Email: " + cliente.getContato().getEmail());
						System.out.println();
						System.out.println("____________________");
					} else
						throw new ClienteNaoCadastradoException(cpf);
				} else
					throw new CpfInvalidoException();
			} catch (CpfInvalidoException | ClienteNaoCadastradoException e) {
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
				if (cpf.equalsIgnoreCase("0"))
					return;
				if (fachada.existeCliente(cpf)) {
					Cliente cliente = fachada.procurarCliente(cpf);
					if (cliente != null) {
						System.out.println();
						System.out.println(cliente.getNome());
						String resposta;
						do {
							try {
								System.out
										.println("\nDeseja realmente remover o cadastro desse cliente? (S/N)");
								System.out.print("Opção: ");
								resposta = read.next();
								if (!resposta.equalsIgnoreCase("s")
										&& !resposta.equalsIgnoreCase("n"))
									throw new OpcaoInvalidaException();
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
							} catch (OpcaoInvalidaException e) {
								System.err.println(e);
							}
						} while (true);
					}

				} else
					throw new ClienteNaoCadastradoException(cpf);
			} catch (ClienteNaoCadastradoException | RepositorioException e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void alugarBicicletaMenu() {

		do {
			System.out.println("\nAluguel de Bicicleta (Sair = 0):");
			try {
				System.out.print("\nInforme o CPF: ");
				String cpf = read.next();
				if (cpf != null) {
					if (cpf.equalsIgnoreCase("0"))
						return;
					if (fachada.existeCliente(cpf)) {
						System.out.print("\nInforme o código da estação: ");
						Long codEstacao = read.nextLong();
						System.out.print("\nInforme o código da bicicleta: ");
						Long codBicicleta = read.nextLong();
						if (codBicicleta > 0 || codEstacao > 0) {
							String resposta;
							do {
								try {
									System.out
											.println("\nDeseja confirmar o aluguel? (S/N)");
									System.out.print("Opção: ");
									resposta = read.next();
									if (!resposta.equalsIgnoreCase("s")
											&& !resposta.equalsIgnoreCase("n"))
										throw new OpcaoInvalidaException();
									else if (resposta.equalsIgnoreCase("s")) {
										fachada.alugarBicicleta(cpf,
												codEstacao, codBicicleta);

										System.out
												.println("\nAluguel confirmado!");
										break;
									} else {
										System.out
												.println("\nAluguel não realizado!");
										break;
									}
								} catch (OpcaoInvalidaException
										| EstacaoNaoExisteException
										| BicicletaIndisponivelException
										| ClienteJaAlugouBicicletaException
										| AluguelInexistenteException e) {
									System.err.println(e);
								}
							} while (true);
						} else
							throw new IdIncorreto();
					} else
						throw new ClienteNaoCadastradoException(cpf);
				} else
					throw new CpfInvalidoException();

			} catch (ClienteNaoCadastradoException | RepositorioException
					| IdIncorreto | CpfInvalidoException e) {
				System.err.println(e);
			}

		} while (true);
	}

	private static void devolverBicicletaMenu() {

		do {
			System.out.println("\nDevoluçao de Bicicleta (Sair = 0):");
			try {
				System.out.print("\nInforme o CPF: ");
				String cpf = read.next();
				if (cpf != null) {
					if (cpf.equalsIgnoreCase("0"))
						return;
					if (fachada.existeCliente(cpf)) {
						System.out.print("\nInforme o código da estação: ");
						Long codEstacao = read.nextLong();
						System.out.print("\nInforme o código da bicicleta: ");
						Long codBicicleta = read.nextLong();
						if (codBicicleta > 0 || codEstacao > 0) {
							String resposta;
							String resp;
							do {
								try {
									System.out
											.println("\nDeseja confirmar a devolução? (S/N)");
									System.out.print("Opção: ");
									resposta = read.next();
									if (!resposta.equalsIgnoreCase("s")
											&& !resposta.equalsIgnoreCase("n"))
										throw new OpcaoInvalidaException();
									else if (resposta.equalsIgnoreCase("s")) {
										fachada.devolverBicicleta(cpf,
												codEstacao, codBicicleta);
										do {
											System.out
													.println("Deseja imprimir um recibo? (S/N)");
											System.out.print("Opção: ");
											resp = read.next();
											if (!resp.equalsIgnoreCase("s")
													&& !resp.equalsIgnoreCase("n"))
												throw new OpcaoInvalidaException();
											else if (resp.equalsIgnoreCase("s")) {
												emitirReciboMenu();
											} else {
												System.out
														.println("\nRecibo não emitido!");
												break;
											}

										} while (true);

										System.out
												.println("\nDevolução confirmada!");
										break;
									} else {
										System.out
												.println("\nDevolução não realizada!");
										break;
									}
								} catch (OpcaoInvalidaException
										| EstacaoNaoExisteException
										| ClienteNaoAlugouBicicletaException
										| AluguelInexistenteException e) {
									System.err.println(e);
								}
							} while (true);

						} else
							throw new IdIncorreto();
					} else
						throw new ClienteNaoCadastradoException(cpf);
				} else
					throw new CpfInvalidoException();
			} catch (ClienteNaoCadastradoException | RepositorioException
					| CpfInvalidoException | IdIncorreto e) {
				System.err.println(e);
			}

		} while (true);
	}

	private static void emitirReciboMenu() {

		try {
			System.out.print("Informe o CPF: ");
			String cpf = read.next();
			if (cpf != null) {
				if (cpf.equalsIgnoreCase("0"))
					return;
				if (fachada.existeCliente(cpf)) {
					System.out.print("Informe o ID da bicicleta: ");
					Long id = read.nextLong();
					if (id == 0)
						return;
					if (id > 0) {
						Format formato = new SimpleDateFormat(
								"dd/MM/yyyy - HH:mm:ss");
						Aluguel aluguel = fachada.procurarAluguelFinalizado(
								cpf, id);
						System.out
								.println("Recibo - Exibição dos dados do aluguel");
						System.out.println();
						System.out.println("Usuário: "
								+ aluguel.getCliente().getNome());
						System.out.println();
						System.out.println("Estação: "
								+ aluguel.getEstacao().getCodigo() + ". "
								+ "Descrição: "
								+ aluguel.getEstacao().getDescricao());
						System.out.println();
						System.out.println("Data do aluguel: "
								+ formato.format(aluguel.getDataAluguel()
										.getTime()));
						System.out.println();
						System.out.println("Data da devolução: "
								+ formato.format(aluguel.getDataDevolucao()
										.getTime()));
						System.out.println();
						System.out.println("Valor pago: " + aluguel.getValor()
								+ " Reais.");
						System.out.println();
					} else
						throw new IdIncorreto();
				} else
					throw new ClienteNaoCadastradoException(cpf);
			} else
				throw new CpfInvalidoException();
		} catch (CpfInvalidoException | ClienteNaoCadastradoException
				| AluguelInexistenteException | IdIncorreto e) {
			System.err.println(e);
		}
	}

	private static void menuFuncionarios() {
		do {
			try {
				System.out.println("\nFuncionários:");
				System.out.println("(1) Cadastrar Estação");
				System.out.println("(2) Atualizar dados da Estação");
				System.out.println("(3) Consultar dados da Estação");
				System.out.println("(4) Exibir Clientes com aluguéis Ativos");
				System.out.println("(5) Exibir Clientes Cadastrados");
				System.out.println("(6) Exibir Aluguéis finalizados");
				System.out.println("(7) Exibir Aluguéis multados");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				opcao = read.nextInt();

				verificarOpcao(opcao, 0, 7);

				switch (opcao) {
				case 1:
					cadastrarEstacaoMenu();
					break;
				case 2:
					atualizarEstacaoMenu();
					break;
				case 3:
					consultarEstacaoMenu();
					break;
				case 4:
					exibirClientesAtivos();
					break;
				case 5:
					exibirClientesCadastrados();
					break;
				case 6:
					exibirAluguelFinalizado();
					break;
				case 7:
					exibirAluguelMultado();
					break;
				default:
					break;
				}
			} catch (OpcaoInvalidaException e) {
				System.err.println(e);
			}
			if (opcao == 0)
				break;
		} while (true);
	}

	private static void exibirAluguelMultado() {
		try {
			System.out.println("Exibição dos cliente com aluguéis multados");
			System.out.println();
			fachada.exibirALuguelComMulta();
			System.out.println("Quantidade de aluguéis multados: "
					+ fachada.exibirALuguelComMulta().size());
			Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			for (Aluguel aluguel : fachada.exibirALuguelComMulta()) {
				System.out.println("Nome: " + aluguel.getCliente().getNome());
				System.out.println();
				System.out.println("CPF: " + aluguel.getCliente().getCpf());
				System.out.println();
				System.out.println("Data de aluguel: "
						+ formato.format(aluguel.getDataAluguel().getTime()));
				System.out.println();
				System.out.println("Data de devolução: "
						+ formato.format(aluguel.getDataDevolucao().getTime()));
				System.out.println();
				System.out.println("Valor da multa: " + aluguel.getValor()
						+ " Reais.");
				System.out.println();
				System.out.println("____________________________");
			}
		} catch (AluguelComMultaInexistenteException e) {
			System.err.println(e);
		}

	}

	private static void exibirClientesAtivos() {

		try {
			System.out.println("Exibição dos Cliente com aluguéis Ativos");
			System.out.println();
			// Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			fachada.exibirALuguelAtivo();
			System.out.println("Quantidade de aluguéis ativos: "
					+ fachada.exibirALuguelAtivo().size());
			for (Aluguel aluguel : fachada.exibirALuguelAtivo()) {
				System.out.println("Nome: " + aluguel.getCliente().getNome());
				System.out.println();
				System.out.println("CPF: " + aluguel.getCliente().getCpf());
				System.out.println();
				// System.out.println("Data de devolução: "
				// + formato.format(aluguel.getDataDevolucao().getTime()));
				System.out.println();
				System.out.println("____________________________");
			}
		} catch (AluguelAtivoInexistenteException e) {
			System.err.println(e);
		}

	}

	private static void exibirClientesCadastrados() {
		try {
			System.out.println("Exibição dos Cliente Cadastrados");
			System.out.println();
			fachada.exibirClientes();
			System.out.println("Quantidade de aluguéis finalizados: "
					+ fachada.exibirClientes().size());
			for (Cliente cliente : fachada.exibirClientes()) {
				System.out.println("Nome: " + cliente.getNome());
				System.out.println();
				System.out.println("CPF: " + cliente.getCpf());
				System.out.println();
				System.out.println("Sexo: " + cliente.getSexo().getDescricao());
				System.out.println();
				System.out.println("Endereço: "
						+ cliente.getEndereco().getEndereco() + " - "
						+ cliente.getEndereco().getBairro() + " - "
						+ cliente.getEndereco().getCidade());
				System.out.println();
				System.out.println("Contato: "
						+ cliente.getContato().getTelefone() + " - "
						+ "Email: " + cliente.getContato().getEmail());
				System.out.println();
				System.out.println("____________________");
			}
		} catch (ClientesInexistentesException e) {
			System.err.println(e);
		}
	}

	private static void exibirAluguelFinalizado() {
		try {
			System.out.println("Exibição dos aluguéis finalizados");
			System.out.println();
			fachada.exibirALuguelFinalizadoEstacao();
			System.out.println("Quantidade de aluguéis finalizados: "
					+ fachada.exibirALuguelFinalizadoEstacao().size());
			Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			for (Aluguel aluguel : fachada.exibirALuguelFinalizadoEstacao()) {
				System.out.println("Nome: " + aluguel.getCliente().getNome());
				System.out.println();
				System.out.println("CPF: " + aluguel.getCliente().getCpf());
				System.out.println();
				System.out.println("Data de aluguel: "
						+ formato.format(aluguel.getDataAluguel().getTime()));
				System.out.println();
				System.out.println("Data de devolução: "
						+ formato.format(aluguel.getDataDevolucao().getTime()));
				System.out.println();
				System.out.println("Valor da multa: " + aluguel.getValor()
						+ " Reais.");
				System.out.println();
				System.out.println("____________________________");
			}
		} catch (AluguelInativoInexistenteException e) {
			System.err.println(e);
		}
	}

	private static void consultarEstacaoMenu() {

		do {
			System.out.println("\nConsultar dados da Estação: ");
			try {
				System.out.print("\nDeseja continuar? (Sair = 0)");
				String resp = read.next();
				if (resp.equalsIgnoreCase("0"))
					return;

				System.out.println("Informe o ID da estação:");
				Long idEstacao = read.nextLong();
				if (idEstacao == 0)
					return;
				if (idEstacao > 0) {
					Estacao estacao = fachada.procurarEstacao(idEstacao);
					if (estacao == null)
						throw new EstacaoNaoExisteException(idEstacao);
					System.out.println("ID: " + estacao.getCodigo() + ". "
							+ "Descrição: " + estacao.getDescricao());
				} else
					throw new IdIncorreto();
			} catch (EstacaoNaoExisteException | IdIncorreto e) {
				System.err.println(e);
			}
		} while (true);

	}

	private static void atualizarEstacaoMenu() {

		do {
			System.out.println("\nAtualizar dados da Estação: ");
			try {
				System.out.print("\nDeseja continuar? (Sair = 0)");
				String resp = read.next();
				if (resp.equalsIgnoreCase("0"))
					return;

				System.out.println("Informe o ID da estação:");
				Long idEstacao = read.nextLong();
				if (idEstacao == 0)
					return;
				if (idEstacao > 0) {
					Estacao estacao = fachada.procurarEstacao(idEstacao);

					do {
						try {
							System.out
									.println("Escolha o que deseja atualizar:");
							System.out.println("\n(1) Atualizar descrição");
							System.out.println("(0) Sair");
							System.out.print("Opção: ");
							opcao = read.nextInt();

							verificarOpcao(opcao, 0, 1);

							if (opcao == 0)
								break;

							switch (opcao) {
							case 1:
								do {
									try {
										System.out
												.println("\nDescrição atual: "
														+ estacao
																.getDescricao());
										System.out
												.print("Informe o novo nome (Cancelar = 0): ");
										read.nextLine();
										String novaDescricao = read.nextLine();
										if (novaDescricao != null) {
											if (novaDescricao
													.equalsIgnoreCase("0"))
												break;
											estacao.setDescricao(novaDescricao);
											fachada.alterarEstacao(estacao);
											System.out
													.println("\nNome do cliente atualizado com sucesso!");
											break;
										} else
											throw new DescricaoInvalidaException();
									} catch (RepositorioException
											| DescricaoInvalidaException e) {
										System.err.println(e);
									}
								} while (true);
								break;
							default:
								break;
							}
						} catch (OpcaoInvalidaException e) {
							System.err.println(e);
						}
					} while (true);
				} else
					throw new IdIncorreto();
			} catch (EstacaoNaoExisteException | IdIncorreto e) {
				System.err.println(e);
			}
		} while (true);
	}

	private static void cadastrarEstacaoMenu() {

		Estacao estacao = new Estacao();
		List<Bicicleta> listaBicicleta = new ArrayList<Bicicleta>();

		try {
			do {
				System.out.println("\nCadastrar Estação. (Sair = 0): ");
				String resp = read.next();
				if (resp.equalsIgnoreCase("0"))
					return;

				System.out.println();
				System.out
						.println("Informe a quantidade de bicicletas que a estação possuirá:");
				int totalBicicletas = read.nextInt();
				if (totalBicicletas > 0) {
					System.out.println("Informe uma descrição para a estação:");
					read.nextLine();
					String descricaoEstacao = read.nextLine();
					if (descricaoEstacao != null) {
						System.out.println();
						estacao.setDescricao(descricaoEstacao);
						estacao.setBicicleta(listaBicicleta);
						fachada.cadastrarEstacao(estacao, totalBicicletas);
						break;
					} else
						throw new DescricaoInvalidaException();
				} else
					throw new NumeroBicicletaInvalidoException();
			} while (true);
		} catch (DescricaoInvalidaException | NumeroBicicletaInvalidoException
				| RepositorioException | EstacaoExistenteException
				| EstacaoNaoExisteException e) {
			System.err.println(e);
		}
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
}
