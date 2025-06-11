package com.MontelongoLuis.screenmatch.repository;

import com.MontelongoLuis.screenmatch.model.Categoria;
import com.MontelongoLuis.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTitleContainsIgnoreCase(String nombreSerie);
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria categoria);

    @Query("SELECT s FROM Serie s " + "JOIN s.episodios e " + "GROUP BY s " + "ORDER BY MAX(e.fechaLanzamiento) DESC LIMIT 5")
    List<Serie> lanzamientoMasRecientes();
}
