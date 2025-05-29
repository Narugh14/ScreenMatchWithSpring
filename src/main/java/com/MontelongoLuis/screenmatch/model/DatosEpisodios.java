package com.MontelongoLuis.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodios (
        @JsonAlias("Title") String title,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String released
){}
