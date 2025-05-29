package com.MontelongoLuis.screenmatch;

import com.MontelongoLuis.screenmatch.model.DatosEpisodios;
import com.MontelongoLuis.screenmatch.model.DatosSeries;
import com.MontelongoLuis.screenmatch.model.DatosTemporadas;
import com.MontelongoLuis.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.MontelongoLuis.screenmatch.service.ConsumoAPI;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();

		var json = consumoAPI
				.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=9d1156f5");
		System.out.println(json);
		ConvierteDatos convierte = new ConvierteDatos();
		var datos = convierte.obtenerDatos(json, DatosSeries.class);
		System.out.println(datos);

		json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=9d1156f5");
		DatosEpisodios episodio = convierte.obtenerDatos(json, DatosEpisodios.class);
		System.out.println(episodio);

		List<DatosTemporadas> temporadas = new ArrayList<>();

		for (int i = 1; i <= datos.totalSeasons() ; i++) {
			json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season="+i+"&apikey=9d1156f5");
			var datosTemporadas = convierte.obtenerDatos(json,DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}

		temporadas.forEach(System.out::println);
	}
}
