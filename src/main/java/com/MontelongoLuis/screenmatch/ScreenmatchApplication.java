package com.MontelongoLuis.screenmatch;

import com.MontelongoLuis.screenmatch.model.DatosEpisodios;
import com.MontelongoLuis.screenmatch.model.DatosSeries;
import com.MontelongoLuis.screenmatch.model.DatosTemporadas;
import com.MontelongoLuis.screenmatch.principal.Principal;
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
		Principal principal = new Principal();
		principal.mostrarMenu();

	}
}
