package com.MontelongoLuis.screenmatch.service;

import com.MontelongoLuis.screenmatch.dto.SerieDTO;
import com.MontelongoLuis.screenmatch.model.Serie;
import com.MontelongoLuis.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .map(s -> new SerieDTO(
                        s.getTitle(),s.getTotalSeasons(),
                        s.getEvaluacion(), s.getGenero(),
                        s.getSinopsis(),s.getUrl_Poster()))
                .collect(Collectors.toList());
    }

}
