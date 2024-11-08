package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.CarteraTceaDto;
import org.example.trabajofinalfinanzasbackend.model.CarteraTcea;
import org.example.trabajofinalfinanzasbackend.model.ClienteProveedor;
import org.example.trabajofinalfinanzasbackend.model.OperacionFactoring;
import org.example.trabajofinalfinanzasbackend.repositories.CarteraTceaRepository;
import org.example.trabajofinalfinanzasbackend.repositories.ClienteProveedorRepository;
import org.example.trabajofinalfinanzasbackend.repositories.OperacionFactoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarteraTceaService {
    @Autowired
    private CarteraTceaRepository carteraTceaRepository;
    @Autowired
    private ClienteProveedorRepository clienteProveedorRepository;
    @Autowired
    private OperacionFactoringRepository operacionFactoringRepository;
    public String insertarCarteraTcea(String ruc, String moneda) {

        List<OperacionFactoring> flujos =operacionFactoringRepository.findOperacionesHoy(ruc,moneda);
        if (flujos!=null) {
        double tir = calcularTIR(flujos);
        CarteraTcea tceadia= carteraTceaRepository.carteraTceaDia(ruc);
        ///verificamos la existencia de la cartera de un dia
        if(tceadia==null){
            ClienteProveedor proveedor = clienteProveedorRepository.findById(ruc).orElse(null);
            if (proveedor != null) {
                CarteraTcea carteraTcea = new CarteraTcea();
                carteraTcea.setTcea(tir);
                carteraTcea.setCantidadOperaciones(flujos.size());
                carteraTcea.setMontosNominales(sumaMontosNominales(flujos));
                carteraTcea.setMontosDescontados(sumaMontosDescuentos(flujos));
                carteraTcea.setMontosRecibidos(calcularInversion(flujos));
                carteraTcea.setMoneda(moneda);
                carteraTcea.setFecha(LocalDateTime.now());
                carteraTcea.setProveedorCartera(proveedor);
                carteraTceaRepository.save(carteraTcea);
            }
        }
        else {
            tceadia.setFecha(LocalDateTime.now());
            tceadia.setTcea(tir);
            tceadia.setCantidadOperaciones(flujos.size());
            tceadia.setMontosNominales(sumaMontosNominales(flujos));
            tceadia.setMontosDescontados(sumaMontosDescuentos(flujos));
            tceadia.setMontosRecibidos(calcularInversion(flujos));
            tceadia.setMoneda(moneda);
            tceadia.setFecha(LocalDateTime.now());
            carteraTceaRepository.save(tceadia);
        }

        return "TIR: " + tir;}
        return "no se puedo encontrar flujos";
    }

    ///caculamos el tir mediante el algoritmo binomial
    private double calcularTIR(List<OperacionFactoring> flujos) {
        double inversion = calcularInversion(flujos );

        double tirLow = -1.0;    // Límite inferior de la TIR
        double tirHigh = 50.0;   //Límite superior de la TIR
        double tolerance = 0.0000001;

        double tirMid =0;  // Punto medio de la TIR
        double van=-1 ;     // VAN en el punto medio

        while (Math.abs(van) >= tolerance) {
            tirMid = (tirLow + tirHigh) / 2;  // Calcular el punto medio
            van = calcularVAN(flujos, inversion, tirMid);  // Calcular el VAN con el TIR intermedio

            // Salida de depuración para cada iteración
            System.out.println("tirMid: " + tirMid + ", VAN: " + van + ", tirLow: " + tirLow + ", tirHigh: " + tirHigh);

            // Ajustar los límites del TIR en función del valor del VAN
            if (van > 0) {
                // Si el VAN es positivo, el TIR es demasiado bajo, mueve el límite inferior hacia arriba
                tirLow = tirMid;
            } else {
                // Si el VAN es negativo, el TIR es demasiado alto, mueve el límite superior hacia abajo
                tirHigh = tirMid;
            }
        }
        // Si el VAN está lo suficientemente cerca de cero, retorna el TIR actual
        BigDecimal tasaRedondeada = BigDecimal.valueOf(tirMid).setScale(9, RoundingMode.HALF_UP);
        return tirMid;
    }

    ///calculamos el van
    private double calcularVAN( List<OperacionFactoring> flujos , double inversion, double tir) {
        double van=-inversion;
        for (OperacionFactoring flujo : flujos) {
            LocalDate fechaVencimiento = flujo.getFacturaOperacion().getFechaVencimiento();
            int dias= calcularDias(fechaVencimiento);
            double flujoPago= flujo.getValorRecibido();
            van += flujoPago / Math.pow((1 + tir), (dias / 360.0));
        }
        return van;
    }

    ///Calculo de dias Factura
    private int calcularDias(LocalDate fechaFin) {
        LocalDate fechaInicio = LocalDate.now();
        // Calculamos la diferencia en días
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    ///calcular inversion o montos recibidos
    private double calcularInversion(List<OperacionFactoring> flujos ) {
        double inversion=0.0;
        for(OperacionFactoring flujo : flujos) {
            inversion += flujo.getValorRecibido();
        }
        return inversion;
    }

    ///calculasmos la suma de montos nominales
    private double sumaMontosNominales(List<OperacionFactoring> flujos) {
        double suma = 0;
        for (OperacionFactoring operacionFactoring : flujos) {
            suma +=operacionFactoring.getValorNominal();
        }
        return suma;
    }

    ///calculamos la suma de montos descontados
    private double sumaMontosDescuentos(List<OperacionFactoring> flujos) {
        double suma = 0;
        for (OperacionFactoring operacionFactoring : flujos) {
            suma+=operacionFactoring.getDescuento()+operacionFactoring.getCostesIniciales();
        }
        return suma;
    }

    public List<CarteraTceaDto> getCarteraTcea(String rucCliente){
        List<CarteraTcea> carteraTceas=carteraTceaRepository.findByRuc(rucCliente);
        List<CarteraTceaDto> carteraTceaDtos=new ArrayList<>();
        for (CarteraTcea carteraTcea: carteraTceas){
            CarteraTceaDto carteraTceaDto=new CarteraTceaDto();
            carteraTceaDto.setId(carteraTcea.getId());
            carteraTceaDto.setTcea(carteraTcea.getTcea());
            carteraTceaDto.setCantidadOperaciones(carteraTcea.getCantidadOperaciones());
            carteraTceaDto.setMontosNominales(carteraTcea.getMontosNominales());
            carteraTceaDto.setMontosDescontados(carteraTcea.getMontosDescontados());
            carteraTceaDto.setMontosRecibidos(carteraTcea.getMontosRecibidos());
            carteraTceaDto.setFecha(carteraTcea.getFecha());
            carteraTceaDtos.add(carteraTceaDto);
        }
        return carteraTceaDtos;
    }

}
