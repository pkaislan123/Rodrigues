package view;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import controller.InsertionSort;
import controller.ManipularTxt;
import controller.ManipularVetor;
import model.App;

public class Main {

	private static Scanner sc;
	
	private static App[]  iniciarOperacoes(String url) {
		
		ManipularTxt manipulador = new ManipularTxt();

		File arquivo_base = new File(url);
		String[] conteudo_linha_a_linha = manipulador.lerArquivo(arquivo_base);
		SimpleDateFormat formato_transformado = new SimpleDateFormat("dd/MM/yyyy");

		App[] base_dados = new App[20000];

		int contador = 0;
		for (String linha : conteudo_linha_a_linha) {

			if (linha == null) {
				break;
			} else {
				try {
					// String [] atributos = linha.split(",");
					String[] atributos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					App app = new App();
					app.setName(atributos[0]);
					app.setCategory(atributos[1]);
					app.setRating(atributos[2]);
					app.setReviews(atributos[3]);
					app.setSize(atributos[4]);
					app.setInstalls(atributos[5]);
					app.setType(atributos[6]);
					app.setPrice(atributos[7]);
					app.setContent_rating(atributos[8]);
					app.setGenres(atributos[9]);

					String data = atributos[10].replaceAll("\"", "");

					try {
						DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
						Date date = format.parse(data);

						String nova_data = formato_transformado.format(date);

						app.setLast_update(nova_data);

					} catch (Exception e) {
						e.printStackTrace();
						DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
						Date date = format.parse(data);
						String nova_data = formato_transformado.format(date);

						app.setLast_update(nova_data);

					}

					app.setCurrent_ver(atributos[11]);
					app.setAndroid_ver(atributos[12]);

					base_dados[contador] = app;

					// System.out.println(app.toString());
					contador++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return base_dados;
	}
	

	public static  void iniciarGui() throws ParseException {
		sc = new Scanner(System.in);
		int op = 0, op2;
		
		
		
		App[] base_dados = iniciarOperacoes("E:\\Users\\aisla\\Downloads\\googleplaystore.csv");

	
		ManipularVetor gerenteVetor = new ManipularVetor(base_dados);
		
		System.out.println("\t| Vetor Incial: |");
		gerenteVetor.imprimirVetor(base_dados);
		System.out.println("\n\n");

		
		
		do {
			System.out.println("\n\t --==[ Rodrigues - Ordenação ]==--");
			System.out.println("\t+===================================+");
			System.out.println("\t| 1 ---------------- Ordenar pelo nome (Campo 'App')                              |");
			System.out.println("\t| 2 ---------------- Ordernar pelas Notas de Avaliação (Campo 'Ratins' )          |");
			System.out.println("\t| 3 ---------------- Ordernar pelo número de instalações (Campo 'installs')       |");
			System.out.println("\t| 4 ---------------- Ordernar pela data da última atualização (Campo last_update) |");
			System.out.println("\t| 0 ---------------- SAIR           |");
			System.out.println("\t+===================================+");
			System.out.println(" Opção: ");
			op = sc.nextInt();

			switch (op) {
			case 1: {
				imprimeSubMenu();
				do {
					System.out.println("\t --==[  Ordenar pelo nome (Campo 'App') ]==--");
					op2 = sc.nextInt();

					switch (op2) {
					case 1:{
						//ordernar appname com algoritmo SelectionSort
						System.out.println("\n\n*****Ordenação Usando Algoritmo SelectionSort*****\n\n");
						System.out.println("-->Atributo: 'AppName'\n\n");
						System.out.println("-->Iniciando Médio Caso...");
						App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(1, 1);
						System.out.println("-->Médio Caso Finalizado, salvando arquivo");
						gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
						boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "selectionSort_ordena_appname_medio_caso");
						if(salvo_medio_caso) {
							System.out.println("Vetor Médio Caso Salvo!");
							
							
							System.out.println("-->Iniciando Melhor Caso...");
							gerenteVetor.setVetor(vetor_ordenado_medio_caso);
							App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(1, 1);
							System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
							boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "selectionSort_ordena_appname_melhor_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Melhor Caso Salvo!");
								
								
								
								System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
								App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
								gerenteVetor.setVetor(vetor_pior_caso_base);
								App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(1, 1);
								System.out.println("-->Pior Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
								boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "selectionSort_ordena_appname_pior_caso");
								if(salvo_pior_caso) {
									System.out.println("Ordenações Concluídas!");

								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
							
						}else {
							System.out.println("Erro ao gerar o arquivo .csv!");

						}
							
					}break;
					case 2:{
						//ordernar app com algoritmo insertionSort
						System.out.println("\n\n*****Ordenação Usando Algoritmo InsertionSort*****\n\n");
						System.out.println("-->Atributo: 'AppName'\n\n");
						System.out.println("-->Iniciando Médio Caso...");
						App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(1, 2);
						System.out.println("-->Médio Caso Finalizado, salvando arquivo");
						gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
						boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "insertionSort_ordena_appname_medio_caso");
						if(salvo_medio_caso) {
							System.out.println("Vetor Médio Caso Salvo!");
							
							
							System.out.println("-->Iniciando Melhor Caso...");
							gerenteVetor.setVetor(vetor_ordenado_medio_caso);
							App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(1, 2);
							System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
							boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "insertionSort_ordena_appname_melhor_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Melhor Caso Salvo!");
								
								
								
								System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
								App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
								gerenteVetor.setVetor(vetor_pior_caso_base);
								App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(1, 2);
								System.out.println("-->Pior Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
								boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "insertionSort_ordena_appname_pior_caso");
								if(salvo_pior_caso) {
									System.out.println("Ordenações Concluídas!");

								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
							
						}else {
							System.out.println("Erro ao gerar o arquivo .csv!");

						}
						
					}break;
					
					case 0:
						System.out.println("\n retornar...\n");
						break;
					default:
						System.out.println("\n Opção inválida!\n Por gentileza, tente novamente!\n");
					}

				} while (op2 != 0);
			}
				break;
			case 2: {
				imprimeSubMenu();
					do {

						System.out.println("\t --==[  Ordernar pelas Notas de Avaliação (Campo 'Ratins' )  ]==--");
						op2 = sc.nextInt();

						switch (op2) {
						case 1:{
							//ordernar ratins com algoritmo SelectionSort
							System.out.println("\n\n*****Ordenação Usando Algoritmo SelectionSort*****\n\n");
							System.out.println("-->Atributo: 'Rating'\n\n");
							System.out.println("-->Iniciando Médio Caso...");
							App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(2, 1);
							System.out.println("-->Médio Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
							boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "SelectionSort_ordena_rating_medio_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Médio Caso Salvo!");
								
								
								System.out.println("-->Iniciando Melhor Caso...");
								gerenteVetor.setVetor(vetor_ordenado_medio_caso);
								App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(2, 1);
								System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
								boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "SelectionSort_ordena_rating_melhor_caso");
								if(salvo_medio_caso) {
									System.out.println("Vetor Melhor Caso Salvo!");
									
									
									
									System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
									App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
									gerenteVetor.setVetor(vetor_pior_caso_base);
									App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(2, 1);
									System.out.println("-->Pior Caso Finalizado, salvando arquivo");
									gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
									boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "SelectionSort_ordena_rating_pior_caso");
									if(salvo_pior_caso) {
										System.out.println("Ordenações Concluídas!");

									}else {
										System.out.println("Erro ao gerar o arquivo .csv!");

									}
									
								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
						}break;
							
						case 2:{
							//ordernar app com algoritmo insertionSort
							System.out.println("\n\n*****Ordenação Usando Algoritmo InsertionSort*****\n\n");
							System.out.println("-->Atributo: 'Rating'\n\n");
							System.out.println("-->Iniciando Médio Caso...");
							App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(2, 2);
							System.out.println("-->Médio Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
							boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "insertionSort_ordena_rating_medio_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Médio Caso Salvo!");
								
								
								System.out.println("-->Iniciando Melhor Caso...");
								gerenteVetor.setVetor(vetor_ordenado_medio_caso);
								App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(2, 2);
								System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
								boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "insertionSort_ordena_rating_melhor_caso");
								if(salvo_medio_caso) {
									System.out.println("Vetor Melhor Caso Salvo!");
									
									
									
									System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
									App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
									gerenteVetor.setVetor(vetor_pior_caso_base);
									App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(2, 2);
									System.out.println("-->Pior Caso Finalizado, salvando arquivo");
									gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
									boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "insertionSort_ordena_rating_pior_caso");
									if(salvo_pior_caso) {
										System.out.println("Ordenações Concluídas!");

									}else {
										System.out.println("Erro ao gerar o arquivo .csv!");

									}
									
								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
						}break;
						
						default:
							System.out.println("\n Opção inválida!\n Por gentileza, tente novamente!\n");
						}

					} while (op2 != 0);
				
			}
				break;
			case 3: {
				imprimeSubMenu();
				do {

					System.out.println("\t --==[  Ordernar pelo número de instalações (Campo 'installs')   ]==--");
					op2 = sc.nextInt();

					switch (op2) {
					case 1:{
						//ordernar app com algoritmo insertionSort
						System.out.println("\n\n*****Ordenação Usando Algoritmo SelectionSort*****\n\n");
						System.out.println("-->Atributo: 'Installs'\n\n");
						System.out.println("-->Iniciando Médio Caso...");
						App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(3, 1);
						System.out.println("-->Médio Caso Finalizado, salvando arquivo");
						gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
						boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "SelectionSort_ordena_installs_medio_caso");
						if(salvo_medio_caso) {
							System.out.println("Vetor Médio Caso Salvo!");
							
							
							System.out.println("-->Iniciando Melhor Caso...");
							gerenteVetor.setVetor(vetor_ordenado_medio_caso);
							App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(3, 1);
							System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
							boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "SelectionSort_ordena_installs_melhor_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Melhor Caso Salvo!");
								
								
								
								System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
								App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
								gerenteVetor.setVetor(vetor_pior_caso_base);
								App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(3, 1);
								System.out.println("-->Pior Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
								boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "SelectionSort_ordena_installs_pior_caso");
								if(salvo_pior_caso) {
									System.out.println("Ordenações Concluídas!");

								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
							
						}else {
							System.out.println("Erro ao gerar o arquivo .csv!");

						}
						
					}break;
					case 2:{
						//ordernar app com algoritmo insertionSort
						System.out.println("\n\n*****Ordenação Usando Algoritmo InsertionSort*****\n\n");
						System.out.println("-->Atributo: 'Installs'\n\n");
						System.out.println("-->Iniciando Médio Caso...");
						App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(3, 2);
						System.out.println("-->Médio Caso Finalizado, salvando arquivo");
						gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
						boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "insertionSort_ordena_installs_medio_caso");
						if(salvo_medio_caso) {
							System.out.println("Vetor Médio Caso Salvo!");
							
							
							System.out.println("-->Iniciando Melhor Caso...");
							gerenteVetor.setVetor(vetor_ordenado_medio_caso);
							App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(3, 2);
							System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
							boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "insertionSort_ordena_installs_melhor_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Melhor Caso Salvo!");
								
								
								
								System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
								App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
								gerenteVetor.setVetor(vetor_pior_caso_base);
								App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(3, 2);
								System.out.println("-->Pior Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
								boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "insertionSort_ordena_installs_pior_caso");
								if(salvo_pior_caso) {
									System.out.println("Ordenações Concluídas!");

								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
							
						}else {
							System.out.println("Erro ao gerar o arquivo .csv!");

						}
						
					}break;
					case 0:
						System.out.println("\n retornar...\n");
						break;
					default:
						System.out.println("\n Opção inválida!\n Por gentileza, tente novamente!\n");
					}

				} while (op2 != 0);

			}
				break;
			case 4: {
				imprimeSubMenu();
				do {

					System.out.println("\t --==[  Ordernar pela data da última atualização (Campo last_update)  ]==--");
					op2 = sc.nextInt();

					switch (op2) {
					case 1:{
						//ordernar app com algoritmo insertionSort
						System.out.println("\n\n*****Ordenação Usando Algoritmo SelectionSort*****\n\n");
						System.out.println("-->Atributo: 'Last_update'\n\n");
						System.out.println("-->Iniciando Médio Caso...");
						App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(4, 1);
						System.out.println("-->Médio Caso Finalizado, salvando arquivo");
						gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
						boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "SelectionSort_ordena_installs_medio_caso");
						if(salvo_medio_caso) {
							System.out.println("Vetor Médio Caso Salvo!");
							
							
							System.out.println("-->Iniciando Melhor Caso...");
							gerenteVetor.setVetor(vetor_ordenado_medio_caso);
							App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(4, 1);
							System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
							boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "SelectionSort_ordena_installs_melhor_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Melhor Caso Salvo!");
								
								
								
								System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
								App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
								gerenteVetor.setVetor(vetor_pior_caso_base);
								App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(4, 1);
								System.out.println("-->Pior Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
								boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "SelectionSort_ordena_installs_pior_caso");
								if(salvo_pior_caso) {
									System.out.println("Ordenações Concluídas!");

								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
							
						}else {
							System.out.println("Erro ao gerar o arquivo .csv!");

						}
					}break;
					case 2:{
						//ordernar app com algoritmo insertionSort
						System.out.println("\n\n*****Ordenação Usando Algoritmo InsertionSort*****\n\n");
						System.out.println("-->Atributo: 'Last_update'\n\n");
						System.out.println("-->Iniciando Médio Caso...");
						App [] vetor_ordenado_medio_caso = gerenteVetor.ordernarVetor(4, 2);
						System.out.println("-->Médio Caso Finalizado, salvando arquivo");
						gerenteVetor.imprimirVetor(vetor_ordenado_medio_caso);
						boolean salvo_medio_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_medio_caso), "insertionSort_ordena_installs_medio_caso");
						if(salvo_medio_caso) {
							System.out.println("Vetor Médio Caso Salvo!");
							
							
							System.out.println("-->Iniciando Melhor Caso...");
							gerenteVetor.setVetor(vetor_ordenado_medio_caso);
							App [] vetor_ordenado_melhor_caso = gerenteVetor.ordernarVetor(4, 2);
							System.out.println("-->Melhor Caso Finalizado, salvando arquivo");
							gerenteVetor.imprimirVetor(vetor_ordenado_melhor_caso);
							boolean salvo_melhor_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_melhor_caso), "insertionSort_ordena_installs_melhor_caso");
							if(salvo_medio_caso) {
								System.out.println("Vetor Melhor Caso Salvo!");
								
								
								
								System.out.println("-->Iniciando Pior Caso, vetor que já está ordenado será invertido...");
								App [] vetor_pior_caso_base = gerenteVetor.inverterVetor(vetor_ordenado_melhor_caso);
								gerenteVetor.setVetor(vetor_pior_caso_base);
								App [] vetor_ordenado_pior_caso = gerenteVetor.ordernarVetor(4, 2);
								System.out.println("-->Pior Caso Finalizado, salvando arquivo");
								gerenteVetor.imprimirVetor(vetor_ordenado_pior_caso);
								boolean salvo_pior_caso = gerenteVetor.salvarCsv(gerenteVetor.gerarCsv(vetor_ordenado_pior_caso), "insertionSort_ordena_installs_pior_caso");
								if(salvo_pior_caso) {
									System.out.println("Ordenações Concluídas!");

								}else {
									System.out.println("Erro ao gerar o arquivo .csv!");

								}
								
							}else {
								System.out.println("Erro ao gerar o arquivo .csv!");

							}
							
							
						}else {
							System.out.println("Erro ao gerar o arquivo .csv!");

						}
						
					}break;
					case 0:
						System.out.println("\n retornar...\n");
						break;
					default:
						System.out.println("\n Opção inválida!\n Por gentileza, tente novamente!\n");
					}

				} while (op2 != 0);
			}
				break;
			case 0:
				System.out.println("\n Até mais!\n");
				break;
			default:
				System.out.println("\n Opção inválida!\n Tente novamente.\n");
			}

		} while (op != 0);

		
	}
	
	
	

	public static void main(String... args) throws ParseException {
		
		
		iniciarGui();
		
	
		

		

	}
	
	
	public static void imprimeSubMenu() {
		System.out.println("\n Escolha uma das seguintes opções disponíveis");
		System.out.println("\t+===================================+");
		System.out.println("\t| 1 ---------------- Algoritmo Selection Sort           |");
		System.out.println("\t| 2 ---------------- Algoritmo Insertion Sort           |");
		System.out.println("\t| 3 ---------------- Algoritmo Merge Sort               |");
		System.out.println("\t| 4 ---------------- Algoritmo Quick Sort               |");
		System.out.println("\t| 5 ---------------- Algoritmo Quick Sort(mediana de 3) |");
		System.out.println("\t| 6 ---------------- Algoritmo Heap Sort                |");
		System.out.println("\t| 7 ---------------- Algoritmo Couting Sort             |");
		System.out.println("\t| 0 ---------------- Sair                               |");
		System.out.println("\t+===================================+");
		System.out.print("\t Opção: ");

	}


}
