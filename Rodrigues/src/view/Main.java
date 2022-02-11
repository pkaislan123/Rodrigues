package view;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import controller.ManipularTxt;
import model.App;

public class Main {

	public static void main(String... args) {
		ManipularTxt manipulador = new ManipularTxt();

		File arquivo_base = new File("E:\\Users\\aisla\\Downloads\\googleplaystore.csv");
		String[] conteudo_linha_a_linha = manipulador.lerArquivo(arquivo_base);
		SimpleDateFormat formato_transformado = new SimpleDateFormat("dd/MM/yyyy");

		App[] base_dados = new App[20000];

		int contador = 0;
		for (String linha : conteudo_linha_a_linha) {

			// private String name, category, rating, reviews, size, installs, type, price,
			// content_rating, genres, last_update, current_ver, android_ver;

			if (linha != null) {
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

					System.out.println(app.toString());
					contador++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

}
