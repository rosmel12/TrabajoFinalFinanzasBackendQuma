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
import java.time.ZoneId;
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
        List<OperacionFactoring> flujosHoy = new ArrayList<>();
        for (OperacionFactoring factoring : flujos) {
            if (factoring.getFechaOperacion().toLocalDate().equals(LocalDate.now())) {
                flujosHoy.add(factoring);
            }
        }
        if (!flujosHoy.isEmpty()) {
        double tir = calcularTIR(flujosHoy);
        List<CarteraTcea> tcea= carteraTceaRepository.carteraTceaDia(ruc,moneda);
        CarteraTcea carteraTceaHoy = null;
        ///verificamos la existencia de la cartera de un dia
        for (CarteraTcea tceadia : tcea) {
            if (tceadia.getFecha().toLocalDate().equals(LocalDate.now())){
                carteraTceaHoy=tceadia;
                break;
            }
        }
        if (carteraTceaHoy !=null && carteraTceaHoy.getFecha().toLocalDate().equals(LocalDate.now())) {
            carteraTceaHoy.setFecha(LocalDateTime.now());
            carteraTceaHoy.setTcea(tir);
            carteraTceaHoy.setCantidadOperaciones(flujosHoy.size());
            carteraTceaHoy.setMontosNominales(sumaMontosNominales(flujosHoy));
            carteraTceaHoy.setMontosDescontados(sumaMontosDescuentos(flujosHoy));
            carteraTceaHoy.setMontosRecibidos(calcularInversion(flujosHoy));
            carteraTceaHoy.setMoneda(moneda);
            carteraTceaHoy.setFecha(LocalDateTime.now(ZoneId.of("America/Lima")));
            carteraTceaRepository.save(carteraTceaHoy);
        } else {
            ClienteProveedor proveedor = clienteProveedorRepository.findById(ruc).orElse(null);
            if (proveedor != null) {
                CarteraTcea carteraTcea = new CarteraTcea();
                carteraTcea.setTcea(tir);
                carteraTcea.setCantidadOperaciones(flujosHoy.size());
                carteraTcea.setMontosNominales(sumaMontosNominales(flujosHoy));
                carteraTcea.setMontosDescontados(sumaMontosDescuentos(flujosHoy));
                carteraTcea.setMontosRecibidos(calcularInversion(flujosHoy));
                carteraTcea.setMoneda(moneda);
                carteraTcea.setFecha(LocalDateTime.now(ZoneId.of("America/Lima")));
                carteraTcea.setProveedorCartera(proveedor);
                carteraTceaRepository.save(carteraTcea);
            }
        }
        return "TIR: " + tir;
        }
        return "no se puedo encontrar flujos";
    }

    ///caculamos el tir mediante el algoritmo binomial
    private double calcularTIR(List<OperacionFactoring> flujos) {
        double inversion = calcularInversion(flujos );

        double tirLow = -1.0;    // Límite inferior de la TIR
        double tirHigh = 50.0;   //Límite superior de la TIR
        double tolerance = 0.00001;
        double tirMid =0.0;  // Punto medio de la TIR
        double van=1.0 ;     // VAN en el punto medio

        while (Math.abs(van) > tolerance) {
            tirMid = (tirLow + tirHigh) / 2;  // Calcular el punto medio
            van = calcularVAN(flujos, inversion, tirMid);  // Calcular el VAN con el TIR intermedio

            // Salida de depuración para cada iteración
            System.out.println("inversion: "+ inversion +" tirMid: " + tirMid + ", VAN: " + van + ", tirLow: " + tirLow + ", tirHigh: " + tirHigh);

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
        return tasaRedondeada.doubleValue();
    }

    ///calculamos el van
    private double calcularVAN( List<OperacionFactoring> flujos , double inversion, double tir) {
        double van=-inversion;
        for (OperacionFactoring flujo : flujos) {
            LocalDate fechaVencimiento = flujo.getFacturaOperacion().getFechaVencimiento();
            int dias= calcularDias(fechaVencimiento);
            double flujoPago= flujo.getValorEntregado();
            van += flujoPago / Math.pow((1 + tir), (dias / 360.0));
        }
        return van;
    }

    ///Calculo de dias Factura
    private int calcularDias(LocalDate fechaFin) {
        LocalDate fechaInicio = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    ///calcular inversion o montos recibidos
    private double calcularInversion(List<OperacionFactoring> flujos ) {
        double inversion=0.0;
        for(OperacionFactoring flujo : flujos) {
            inversion += flujo.getValorRecibido();
        }
        BigDecimal sumaRedondeada = BigDecimal.valueOf(inversion).setScale(2, RoundingMode.HALF_UP);
        return sumaRedondeada.doubleValue();
    }

    ///calculasmos la suma de montos nominales
    private double sumaMontosNominales(List<OperacionFactoring> flujos) {
        double suma = 0;
        for (OperacionFactoring operacionFactoring : flujos) {
            suma +=operacionFactoring.getValorNominal();
        }
        BigDecimal sumaRedondeada = BigDecimal.valueOf(suma).setScale(2, RoundingMode.HALF_UP);
        return sumaRedondeada.doubleValue();
    }

    ///calculamos la suma de montos descontados
    private double sumaMontosDescuentos(List<OperacionFactoring> flujos) {
        double suma = 0.0;
        for (OperacionFactoring operacionFactoring : flujos) {
            suma += operacionFactoring.getDescuento() + operacionFactoring.getCostesIniciales();
        }
        BigDecimal sumaRedondeado = BigDecimal.valueOf(suma).setScale(2, RoundingMode.HALF_UP);
        return sumaRedondeado.doubleValue();
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
            carteraTceaDto.setMoneda(carteraTcea.getMoneda());
            carteraTceaDto.setFecha(carteraTcea.getFecha());
            carteraTceaDtos.add(carteraTceaDto);
        }
        return carteraTceaDtos;
    }

}
