package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

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

    public List<TasaEfectiva> listarTasaEfectivas() {
        List<TasaEfectiva> tasaEfectivasAux = tasaEfectivaRepository.findAll();
        List<TasaEfectiva> tasaEfectivas = new ArrayList<>();
        for (TasaEfectiva tasaEfectiva : tasaEfectivasAux) {
            if(tasaEfectiva.getFechaInicio().isBefore(LocalDateTime.now()) && tasaEfectiva.getFechaFin().isAfter(LocalDateTime.now()) ){
                tasaEfectivas.add(tasaEfectiva);
            }
        }
        return tasaEfectivas;
    }
}
