package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.model.OperacionFactoring;
import org.example.trabajofinalfinanzasbackend.model.TceaOperacion;
import org.example.trabajofinalfinanzasbackend.repositories.TceaOperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TceaOperacionService {
    @Autowired
    private TceaOperacionRepository tceaOperacionRepository;

    public String IngresarTceaOperacion(OperacionFactoring operacionFactoring) {
        TceaOperacion tceaOperacion = new TceaOperacion();
        tceaOperacion.setTcea(calcularTceaOperacion(operacionFactoring.getNumeroDias(),operacionFactoring.getValorRecibido(),operacionFactoring.getValorEntregado()));
        tceaOperacion.setFecha(LocalDateTime.now());
        tceaOperacion.setTceaOperacionFactoring(operacionFactoring);
        tceaOperacionRepository.save(tceaOperacion);
        return "la tcea se agrego correctmente";
    }

    private double calcularTceaOperacion(int diasFactura, double montoRecibido,double montoEntregado) {
        double tceaOperacion = (Math.pow(montoEntregado/montoRecibido, (double) 360/diasFactura))-1;
        return tceaOperacion;
    }

    public List<TceaOperacion> listarTceaOperacionUsuario(String ruc) {
       return tceaOperacionRepository.findAllByRucClienteProveedor(ruc);
    }

    public TceaOperacion buscarTceaOperacion(Integer id) {
        return tceaOperacionRepository.findbyIdOperacionFactoring(id);
    }
}