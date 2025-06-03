package com.MontelongoLuis.screenmatch.principal;

import com.MontelongoLuis.screenmatch.model.*;
import com.MontelongoLuis.screenmatch.service.ConsumoAPI;
import com.MontelongoLuis.screenmatch.service.ConvierteDatos;

import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierte = new ConvierteDatos();
    private List<DatosSeries> datosSeries = new ArrayList<>();

    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9d1156f5";

    public void mostrarMenu() {

        var opcion = -1;

        while(opcion!= 0){
               var menu = """
                       
                       Eliga una opcion valida para comenzar:
                       
                       1 - Buscar Serie
                       2 - Buscar episodios
                       3 - Lista de Series buscadas recientemente
                       
                       0 - Salir del Programa
                       """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    busquedaSerieWeb();
                    break;
                case 2:
                    buscarListEpisodio();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación");
                    break;
                default:
                    System.out.println("Seleccione escribiendo alguna opcion valida");
            }
        }
}


    private DatosSeries getObtenerDatos(){
        System.out.println("Escribe que serie deseas buscar mas informacion:");
        var serie = teclado.nextLine();
        //Datos generales de la serie
        var json = consumoAPI
                .obtenerDatos(URL_BASE
                        + serie.replace(" ", "+")
                        + API_KEY);

        return convierte.obtenerDatos(json, DatosSeries.class);
    }

    private void buscarListEpisodio() {
        var datosSerie = getObtenerDatos();
        //Creamos una lista para los episodios de cada temporada
        List<DatosTemporadas> temporadas = new ArrayList<>();
        //Guardamos los episodios en una lista por cada temporada
        for (int i = 1; i <= datosSerie.totalSeasons(); i++) {
            var json = consumoAPI.obtenerDatos(URL_BASE
                    + datosSerie.title().replace(" ", "+") + "&Season=" + i + API_KEY);
           DatosTemporadas datosTemporadas = convierte.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }

    private void busquedaSerieWeb() {
        DatosSeries serie = getObtenerDatos();
        datosSeries.add(serie);
        System.out.println(serie);
    }

    private void mostrarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
                        .map(t -> new Serie(t))
                                .collect(Collectors.toList());

        System.out.println("Titulos buscados recientemente");

        series.stream()
                        .sorted(Comparator.comparing(Serie::getGenero))
                        .forEach(System.out::println);
    }
}







