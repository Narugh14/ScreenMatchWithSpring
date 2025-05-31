package com.MontelongoLuis.screenmatch.principal;

import com.MontelongoLuis.screenmatch.model.DatosEpisodios;
import com.MontelongoLuis.screenmatch.model.DatosSeries;
import com.MontelongoLuis.screenmatch.model.DatosTemporadas;
import com.MontelongoLuis.screenmatch.model.Episodio;
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

    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9d1156f5";

    public void mostrarMenu() {
        System.out.println("Escribe que serie deseas buscar mas informacion:");
        var serie = teclado.nextLine();
        //Datos generales de la serie
        var json = consumoAPI
                .obtenerDatos(URL_BASE
                        + serie.replace(" ", "+")
                        + API_KEY);

        var datos = convierte.obtenerDatos(json, DatosSeries.class);

        System.out.println(datos);

        //Creamos una lista para los episodios de cada temporada
        List<DatosTemporadas> temporadas = new ArrayList<>();
        //Guardamos los episodios en una lista por cada temporada
        for (int i = 1; i <= datos.totalSeasons(); i++) {
            json = consumoAPI.obtenerDatos(URL_BASE
                    + serie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var datosTemporadas = convierte.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);

        // temporadas.forEach(t-> t.listEpisodios().forEach(e -> System.out.println(e.title()) ) );

//        System.out.println("Los mejores episodios son: ");
        List<DatosEpisodios> best10Episodios = temporadas.stream()
                .flatMap(t -> t.listEpisodios().stream())
                .collect(Collectors.toList());

//        best10Episodios.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DatosEpisodios::rating).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        //Convertir los datos a una lista de tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.listEpisodios().stream()
                        .map(d -> new Episodio(t.numTemporada(), d)))
                .collect(Collectors.toList());

//        episodios.forEach(System.out::println);

        //Busqueda de episodios a partir de X año
//        System.out.println("Escribe el año para apartir de eso, hacer la busqueda de los episodios:");
//        var fecha = teclado.nextInt();
//        teclado.nextLine();

//        LocalDate fechaBusqueda = LocalDate.of(fecha,1, 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//        episodios.stream()
//                .filter(e -> e.getFechaLanzamiento() != null && e.getFechaLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e ->System.out.println(
//                        "T:" + e.getNumTemporada() +
//                                " E:" + e.getNumEpisodio()+
//                                ", T:" + e.getTitulo().substring(0,Math.min(e.getTitulo().length(),15))+
//                                ", Fecha estreno " +  e.getFechaLanzamiento().format(dtf)
//
//                ));
//        System.out.println("Escribre algun texto de un episodio para la busqueda: ");
//        var textoTituloEpisodo = teclado.nextLine();
//        Optional<Episodio> BusquedaResult = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(textoTituloEpisodo.toUpperCase()))
//                .findFirst();
//        if (BusquedaResult.isPresent()){
//            System.out.println("El capitulo encontrado es: " + BusquedaResult.get().getTitulo());
//        }else{
//            System.out.println("Episodio no encontrado");
//        }


        Map<Integer, Double> evaluacionForTemporadas = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getNumTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionForTemporadas);


        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Episodios contados: "+ est.getCount());
        System.out.println("Calificacion promedio de la serie: " + est.getAverage());
        System.out.println("Calificacion mas alta de un episodio: " + est.getMax());
    }
}







