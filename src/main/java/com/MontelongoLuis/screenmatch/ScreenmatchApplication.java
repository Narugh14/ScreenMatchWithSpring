package com.MontelongoLuis.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.MontelongoLuis.screenmatch.service.ConsumoAPI;

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
	}
}
