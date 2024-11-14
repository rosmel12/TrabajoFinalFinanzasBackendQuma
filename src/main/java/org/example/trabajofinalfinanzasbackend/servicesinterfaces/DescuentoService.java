package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.DescuentoDto;
import org.example.trabajofinalfinanzasbackend.model.Comision;
import org.example.trabajofinalfinanzasbackend.model.Descuento;
import org.example.trabajofinalfinanzasbackend.model.TasaEfectiva;
import org.example.trabajofinalfinanzasbackend.model.TasaNominal;
import org.example.trabajofinalfinanzasbackend.repositories.ComisionRepository;
import org.example.trabajofinalfinanzasbackend.repositories.DescuentoRepository;
import org.example.trabajofinalfinanzasbackend.repositories.TasaEfectivaRepository;
import org.example.trabajofinalfinanzasbackend.repositories.TasaNominalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DescuentoService {

    private final DescuentoRepository descuentoRepository;
    private final ComisionRepository comisionRepository;
    private final TasaNominalRepository tasaNominalRepository;
    private final TasaEfectivaRepository tasaEfectivaRepository;

    private DescuentoService(DescuentoRepository descuentoRepository, ComisionRepository comisionRepository, TasaNominalRepository tasaNominalRepository, TasaEfectivaRepository tasaEfectivaRepository) {
        this.descuentoRepository = descuentoRepository;
        this.comisionRepository = comisionRepository;
        this.tasaNominalRepository = tasaNominalRepository;
        this.tasaEfectivaRepository = tasaEfectivaRepository;
    }

    public Integer insertDescuento(DescuentoDto descuentoDto, Descuento descuento) {
        Comision comision =comisionRepository.findById(descuentoDto.getIdComision()).orElse(null) ;
        TasaNominal tasaNominal=tasaNominalRepository.findById(descuentoDto.getIdTasaNominal()).orElse(null) ;
        TasaEfectiva tasaEfectiva=tasaEfectivaRepository.findById(descuentoDto.getIdTasaEfectiva()).orElse(null);

        //verificar comision
        if(comision !=null){
           descuento.setComisionDescuento(comision);
       }else{
           return null;
       }
       //verificar una tasa
       if(tasaNominal!=null && tasaEfectiva==null) {
            descuento.setTasaNominalDescuento(tasaNominal);
            descuento.setFecha(LocalDateTime.now());
            descuento.setTasaEfectivaDescuento(null);
           descuento= descuentoRepository.save(descuento);
           return descuento.getId();
       }else if(  tasaEfectiva !=null && tasaNominal==null) {
            descuento.setTasaEfectivaDescuento(tasaEfectiva);
            descuento.setTasaNominalDescuento(null);
           descuento.setFecha(LocalDateTime.now());
           descuento= descuentoRepository.save(descuento);
           return descuento.getId();
       }
        return null;
    }

    public List<Descuento> listarDescuentos(String ruc) {
        return descuentoRepository.findByDescuento(ruc);
    }

}
