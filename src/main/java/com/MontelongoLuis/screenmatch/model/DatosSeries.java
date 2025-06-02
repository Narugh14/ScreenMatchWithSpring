package com.MontelongoLuis.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSeries(
        @JsonAlias("Title") String title,
        @JsonAlias("Year") String year,
        @JsonAlias("Released") String released,
        @JsonAlias("Runtime") String runtime,
        @JsonAlias("totalSeasons") Integer totalSeasons,

        @JsonAlias("Genre")String genero,
        @JsonAlias("Plot")String sinopsis,
        @JsonAlias("Poster")String url_Poster,
        @JsonAlias("Actors")String actores

)
{
    @Override
    public String toString() {
        return "Series{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", released='" + released + '\'' +
                ", genero='" + genero + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", url_Poster='" + url_Poster + '\'' +
                ", actores='" + actores + '\'' +
                ", totalSeasons=" + totalSeasons +
                '}';
    }
}
