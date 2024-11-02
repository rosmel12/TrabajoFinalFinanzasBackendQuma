package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.model.TasaNominal;
import org.example.trabajofinalfinanzasbackend.repositories.TasaNominalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasaNominalService {
@Autowired
private TasaNominalRepository TasaNominalRepository;

public String insertarTasaNominal(TasaNominal TasaNominal) {
    TasaNominalRepository.save(TasaNominal);
    return "creado correctamente";
}

public List<TasaNominal> listarTasaNominal() {
    List<TasaNominal> tasaNominalList = TasaNominalRepository.findAll();
    List<TasaNominal> tasaNominals = new ArrayList<>();
    for (TasaNominal tasaNominal : tasaNominalList) {
        if(tasaNominal.getFechaInicio().isBefore(LocalDateTime.now()) && tasaNominal.getFechaFin().isAfter(LocalDateTime.now())) {
            tasaNominals.add(tasaNominal);
        }
    }
    return tasaNominals;
}
}
