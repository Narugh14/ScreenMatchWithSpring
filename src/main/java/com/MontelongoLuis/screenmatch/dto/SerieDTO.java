package com.MontelongoLuis.screenmatch.dto;

import com.MontelongoLuis.screenmatch.model.Categoria;

public record SerieDTO(
         Long id,
         String titulo,
         Integer totalSeasons,
         Double evaluacion,
         Categoria genero,
         String sinopsis,
         String url_Poster,
         String actores
) {
}
