package com.MontelongoLuis.screenmatch.model;


import lombok.Getter;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter @Setter
public class Episodio {


    private Integer numTemporada;
    private String titulo;
    private Integer numEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;

    public Episodio(Integer temporada, DatosEpisodios d) {

        this.numTemporada = temporada;
        this.titulo = d.title();
        this.numEpisodio = d.numEpisode();
        try{
            this.evaluacion = Double.valueOf(d.rating());
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0;
        }

        try{
            this.fechaLanzamiento = LocalDate.parse(d.released());
        } catch (DateTimeParseException e) {
            this.fechaLanzamiento = null;
        }

    }

    @Override
    public String toString() {
        return "numTemporada=" + numTemporada +
                ", titulo='" + titulo + '\'' +
                ", numEpisodio=" + numEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaLanzamiento=" + fechaLanzamiento;
    }
}
