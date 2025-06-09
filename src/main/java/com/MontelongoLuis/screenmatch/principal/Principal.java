package com.MontelongoLuis.screenmatch.principal;

import com.MontelongoLuis.screenmatch.model.*;
import com.MontelongoLuis.screenmatch.repository.SerieRepository;
import com.MontelongoLuis.screenmatch.service.ConsumoAPI;
import com.MontelongoLuis.screenmatch.service.ConvierteDatos;

import java.sql.SQLOutput;
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
    private SerieRepository repositorio;
    private List<Serie> series;

    public Principal(SerieRepository serieRepository) {
        this.repositorio = serieRepository;
    }

    public void mostrarMenu() {

        var opcion = -1;

        while(opcion!= 0){
               var menu = """
                       
                       Eliga una opcion valida para comenzar:
                       
                       1 - Buscar Serie
                       2 - Buscar episodios
                       3 - Lista de Series buscadas recientemente
                       4 - Buscar Serie por titulo
                       5 - Top 5 Mejores series Buscadas
                       6 - Buscar por Genero de Serie
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
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarTop5BestSeeries();
                    break;
                case 6:
                    buscarListSeriePorGenero();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación");
                    break;
                default:
                    System.out.println("Seleccione escribiendo alguna opcion valida");
            }
        }
}

    private void buscarListSeriePorGenero() {
        System.out.println("Escribe el genero de la serie que desea buscar: ");
        var generoBuscar = teclado.nextLine();
        var categoria = Categoria.fromSpanish(generoBuscar);
        List<Serie> seriePorCategoria = repositorio.findByGenero(categoria);
        if(seriePorCategoria.size() != 0)
            System.out.println("Las series de la categoria: " + generoBuscar);
        else
            System.out.println("Series no encontradas con ese genero: "+ generoBuscar);
        seriePorCategoria.forEach(System.out::println);
    }

    private void buscarTop5BestSeeries() {
        List<Serie> serieList = repositorio.findTop5ByOrderByEvaluacionDesc();
        serieList.forEach(s -> System.out.println("Serie: " + s.getTitle()
                + ", Evaluacion: " + s.getEvaluacion()));
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escribe el titulo que quieres de la serie");
        var nombreSerie = teclado.nextLine();
        Optional<Serie> serieBusqueda = repositorio.findByTitleContainsIgnoreCase(nombreSerie);
        if(serieBusqueda.isPresent()){
            System.out.println("La serie buscada es: "+ serieBusqueda.get());
        }else{
            System.out.println("Serie no encontrada");
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
        mostrarSeriesBuscadas();
        System.out.println("Escribe la serie quieres ver los episodios");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s->s.getTitle().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();
        if(serie.isPresent()){
            var serieEncontrada = serie.get();

            //Creamos una lista para los episodios de cada temporada
            List<DatosTemporadas> temporadas = new ArrayList<>();

            //Guardamos los episodios en una lista por cada temporada
            for (int i = 1; i <= serieEncontrada.getTotalSeasons(); i++) {
                var json = consumoAPI.obtenerDatos(URL_BASE
                        + serieEncontrada.getTitle().replace(" ", "+")
                        + "&Season=" + i + API_KEY);
                DatosTemporadas datosTemporadas = convierte.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporadas);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodioList = temporadas.stream()
                    .flatMap(d -> d.listEpisodios().stream()
                            .map(e -> new Episodio(d.numTemporada(),e)))
                            .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodioList);
            repositorio.save(serieEncontrada);
        }


    }

    private void busquedaSerieWeb() {
        DatosSeries datos = getObtenerDatos();
        //datosSeries.add(serie);
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();
        series.stream()
                        .sorted(Comparator.comparing(Serie::getGenero))
                        .forEach(System.out::println);
    }
}







