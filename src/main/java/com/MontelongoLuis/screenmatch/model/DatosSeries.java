package com.MontelongoLuis.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSeries(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalSeasons,
        @JsonAlias("imdbRating") String evaluacion,

        @JsonAlias("Genre")String genero,
        @JsonAlias("Plot")String sinopsis,
        @JsonAlias("Poster")String url_Poster,
        @JsonAlias("Actors")String actores

)
{

}
