package com.MontelongoLuis.screenmatch.controller;

import com.MontelongoLuis.screenmatch.dto.EpisodiosDTO;
import com.MontelongoLuis.screenmatch.dto.SerieDTO;
import com.MontelongoLuis.screenmatch.model.Serie;
import com.MontelongoLuis.screenmatch.repository.SerieRepository;
import com.MontelongoLuis.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSeries() {
        return service.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5Series() {
        return service.obtenerTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosRecientes() {
        return service.obtenerLanzamientoRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodiosDTO> obtenerTodasTemporadas(@PathVariable Long id) {
        return service.obtenerTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodiosDTO> obtenerTemporadasPorNumero(@PathVariable Long id,
                                                         @PathVariable Long numeroTemporada)
    {
        return service.obtenerTemporadasPorNumero(id, numeroTemporada);
    }

    @GetMapping("/categoria/{nombreGenero}")
    public  List<SerieDTO> obtenerSeriePorCategoria(@PathVariable String nombreGenero){
        return service.obtenerSeriePorCategoria(nombreGenero);
    }
}
