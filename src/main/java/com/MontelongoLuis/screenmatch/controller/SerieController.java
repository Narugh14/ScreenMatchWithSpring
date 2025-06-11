package com.MontelongoLuis.screenmatch.controller;

import com.MontelongoLuis.screenmatch.dto.SerieDTO;
import com.MontelongoLuis.screenmatch.model.Serie;
import com.MontelongoLuis.screenmatch.repository.SerieRepository;
import com.MontelongoLuis.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<SerieDTO> obtenerTodasLasSeries(){
        return service.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5Series(){
        return service.obtenerTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosRecientes(){
        return  service.obtenerLanzamientoRecientes();
    }
}
