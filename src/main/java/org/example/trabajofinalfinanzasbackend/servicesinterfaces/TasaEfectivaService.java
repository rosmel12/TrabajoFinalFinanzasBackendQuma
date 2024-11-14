package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.TasaEfectivaDto;
import org.example.trabajofinalfinanzasbackend.model.TasaEfectiva;
import org.example.trabajofinalfinanzasbackend.repositories.TasaEfectivaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasaEfectivaService {
    @Autowired
    private TasaEfectivaRepository tasaEfectivaRepository;

    public String insertarTasaEfectiva(TasaEfectiva tasaEfectiva) {
         tasaEfectivaRepository.save(tasaEfectiva);
         return "creado correctamente";
    }

    public List<TasaEfectivaDto> listarTasaEfectivas() {
        List<TasaEfectiva> tasaEfectivasAux = tasaEfectivaRepository.findAll();
        List<TasaEfectivaDto> tasaEfectivas = new ArrayList<>();
        for (TasaEfectiva tasaEfectiva : tasaEfectivasAux) {
            if(tasaEfectiva.getFechaInicio().isBefore(LocalDateTime.now()) && tasaEfectiva.getFechaFin().isAfter(LocalDateTime.now()) ){
               TasaEfectivaDto tasaEfectivaDto= new TasaEfectivaDto();
               tasaEfectivaDto.setId(tasaEfectiva.getId());
               tasaEfectivaDto.setTasaInteres(tasaEfectiva.getTasaInteres());
               tasaEfectivaDto.setPlazo(tasaEfectiva.getPlazo());
               tasaEfectivaDto.setFechaInicio(tasaEfectiva.getFechaInicio());
               tasaEfectivaDto.setFechaFin(tasaEfectiva.getFechaFin());
               tasaEfectivas.add(tasaEfectivaDto);
            }
        }
        return tasaEfectivas;
    }
}
