package com.MontelongoLuis.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSeries(
        @JsonAlias("Title") String title,
        @JsonAlias("Year") String year,
        @JsonAlias("Released") String released,
        @JsonAlias("Runtime") String runtime,
        @JsonAlias("totalSeasons") Integer totalSeasons
)
{ }
