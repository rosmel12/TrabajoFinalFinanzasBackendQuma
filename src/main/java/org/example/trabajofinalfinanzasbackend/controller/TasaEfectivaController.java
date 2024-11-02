package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.TasaEfectivaDto;
import org.example.trabajofinalfinanzasbackend.model.TasaEfectiva;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.TasaEfectivaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quma/tasaefectiva")
@CrossOrigin()
public class TasaEfectivaController {
    @Autowired
    private TasaEfectivaService tasaEfectivaService;

    @PostMapping("/insertar")
    public String create(@RequestBody TasaEfectivaDto tasaEfectivaDto) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            TasaEfectiva tasaEfectiva = modelMapper.map(tasaEfectivaDto, TasaEfectiva.class);
            return tasaEfectivaService.insertarTasaEfectiva(tasaEfectiva);
        } catch(Exception e) {
            throw new Exception("Error al insertar tasa efectiva");
        }
    }
    @GetMapping("/usuario/listar")
    public List<TasaEfectivaDto> listartasa() {
        ModelMapper modelMapper = new ModelMapper();
        List<TasaEfectiva> tasaEfectivas=tasaEfectivaService.listarTasaEfectivas();
        return Arrays.asList(modelMapper.map(tasaEfectivas, TasaEfectivaDto[].class));
    }
}
