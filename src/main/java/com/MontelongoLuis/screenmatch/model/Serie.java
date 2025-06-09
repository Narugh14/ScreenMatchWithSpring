package com.MontelongoLuis.screenmatch.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private Integer totalSeasons;
    private Double evaluacion;

    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String sinopsis;
    private String url_Poster;
    private String actores;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios;

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
                ", episodios='" + episodios + '\'';
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach( e->e.setSerie(this));
        this.episodios = episodios;
    }
}
