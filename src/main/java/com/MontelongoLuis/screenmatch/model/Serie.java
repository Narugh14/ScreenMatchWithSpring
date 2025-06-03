package com.MontelongoLuis.screenmatch.model;

import com.MontelongoLuis.screenmatch.service.ConsultaChatGPT;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.OptionalDouble;
@Getter
@Setter
public class Serie {

    private String title;
    private Integer totalSeasons;
    private Double evaluacion;
    private Categoria genero;
    private String sinopsis;
    private String url_Poster;
    private String actores;

    public Serie(DatosSeries datosSeries){
        this.title = datosSeries.title();
        this.totalSeasons = datosSeries.totalSeasons();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSeries.evaluacion())).orElse(0.0);
        this.url_Poster = datosSeries.url_Poster();
        this.genero = Categoria.fromString(datosSeries.genero().split(",")[0].trim());
        this.actores = datosSeries.actores();
       // this.sinopsis = ConsultaChatGPT.obtenerTraduccion(datosSeries.sinopsis());
        this.sinopsis = datosSeries.sinopsis();
    }

    @Override
    public String toString() {
        return  "Genero=" + genero +
                ", title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", evaluacion=" + evaluacion +
                ", sinopsis='" + sinopsis + '\'' +
                ", url_Poster='" + url_Poster + '\'' +
                ", actores='" + actores + '\'';
    }
}
