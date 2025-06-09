package com.MontelongoLuis.screenmatch.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name="episodios")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private Integer numTemporada;
    private Integer numEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;

    @ManyToOne
    private Serie serie;

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
        return "{ T:" + numTemporada +
                " E:" + numEpisodio +
                ", Titulo:'" + titulo + '\'' +
                ", Evaluacion: " + evaluacion +
                ", Lanzamiento: " + fechaLanzamiento
                + " }";
    }
}
