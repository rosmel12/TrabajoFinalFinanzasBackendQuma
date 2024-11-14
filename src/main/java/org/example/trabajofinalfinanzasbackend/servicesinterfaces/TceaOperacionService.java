package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.TceaOperacionDto;
import org.example.trabajofinalfinanzasbackend.model.OperacionFactoring;
import org.example.trabajofinalfinanzasbackend.model.TceaOperacion;
import org.example.trabajofinalfinanzasbackend.repositories.TceaOperacionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TceaOperacionService {

    private final TceaOperacionRepository tceaOperacionRepository;

    private TceaOperacionService(TceaOperacionRepository tceaOperacionRepository) {
        this.tceaOperacionRepository = tceaOperacionRepository;
    }

    public void IngresarTceaOperacion(OperacionFactoring operacionFactoring) {
        TceaOperacion tceaOperacion = new TceaOperacion();
        tceaOperacion.setTcea(calcularTceaOperacion(operacionFactoring.getNumeroDias(),operacionFactoring.getValorRecibido(),operacionFactoring.getValorEntregado()));
        tceaOperacion.setFecha(LocalDateTime.now(ZoneId.of("America/Lima")));
        tceaOperacion.setTceaOperacionFactoring(operacionFactoring);
        tceaOperacionRepository.save(tceaOperacion);
    }

    private double calcularTceaOperacion(int diasFactura, double montoRecibido,double montoEntregado) {
        double tceaOperacion = (Math.pow(montoEntregado/montoRecibido, (double) 360/diasFactura))-1;
        BigDecimal tceaRedondeado = BigDecimal.valueOf(tceaOperacion).setScale(9, RoundingMode.HALF_UP);
        return tceaRedondeado.doubleValue();
    }

    public List<TceaOperacion> listarTceaOperacionUsuario(String ruc) {
       return tceaOperacionRepository.findAllByRucClienteProveedor(ruc);
    }

    public TceaOperacionDto buscarTceaOperacion(Integer id) {
        TceaOperacion tceaOperacion = tceaOperacionRepository.findById(id).orElse(null);
        if (tceaOperacion != null) {
            TceaOperacionDto tceaOperacionDto=new TceaOperacionDto();
            tceaOperacionDto.setId(tceaOperacion.getId());
            tceaOperacionDto.setTcea(tceaOperacion.getTcea());
            tceaOperacionDto.setFecha(tceaOperacion.getFecha());
            return tceaOperacionDto;
        }
        return null;
    }
}