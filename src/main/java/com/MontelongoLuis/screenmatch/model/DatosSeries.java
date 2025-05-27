package com.MontelongoLuis.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosSeries(
        @JsonAlias("Title") String title,
        @JsonAlias("Year") Integer year,
        @JsonAlias("Released") String released,
        @JsonAlias("Runtime") String runtime
) {
}
