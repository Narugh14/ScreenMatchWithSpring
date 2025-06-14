package com.MontelongoLuis.screenmatch.service;

import com.MontelongoLuis.screenmatch.dto.EpisodiosDTO;
import com.MontelongoLuis.screenmatch.dto.SerieDTO;
import com.MontelongoLuis.screenmatch.model.Categoria;
import com.MontelongoLuis.screenmatch.model.Episodio;
import com.MontelongoLuis.screenmatch.model.Serie;
import com.MontelongoLuis.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerTodasLasSeries(){
        return convertirDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5Series() {
        return convertirDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerLanzamientoRecientes(){
        return convertirDatos(repository.lanzamientoMasRecientes());
    }

    public List<SerieDTO> convertirDatos(List<Serie> series){
        return series
                .stream()
                .map(s -> new SerieDTO(s.getId(),
                        s.getTitle(),s.getTotalSeasons(),
                        s.getEvaluacion(), s.getGenero(),
                        s.getSinopsis(),s.getUrl_Poster(),
                        s.getActores()))
                .collect(Collectors.toList());
    }

    public SerieDTO obtenerPorId(Long id){
        Optional<Serie> serie = repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(),
                    s.getTitle(),s.getTotalSeasons(),
                    s.getEvaluacion(), s.getGenero(),
                    s.getSinopsis(),s.getUrl_Poster(),
                    s.getActores());
        }
        return  null;
    }

    public List<EpisodiosDTO> obtenerTodasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodiosDTO(
                            e.getTitulo(), e.getNumTemporada(), e.getNumEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodiosDTO> obtenerTemporadasPorNumero(Long id, Long numeroTemporada) {
            List<Episodio> episodios = repository.obtenerTemporadasPorNumero(id, numeroTemporada);
            List<EpisodiosDTO> episodiosDTOS = episodios.stream().map(e -> new EpisodiosDTO(
                 e.getTitulo(), e.getNumTemporada(), e.getNumEpisodio()))
                    .collect(Collectors.toList());
            return episodiosDTOS;
    }

    public List<SerieDTO> obtenerSeriePorCategoria(String nombreGenero) {
        Categoria categoria = Categoria.fromSpanish(nombreGenero);
        return convertirDatos(repository.findByGenero(categoria));
    }
}
