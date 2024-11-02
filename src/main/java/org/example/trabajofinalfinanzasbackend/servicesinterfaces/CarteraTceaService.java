package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.model.CarteraTcea;
import org.example.trabajofinalfinanzasbackend.model.ClienteProveedor;
import org.example.trabajofinalfinanzasbackend.repositories.CarteraTceaRepository;
import org.example.trabajofinalfinanzasbackend.repositories.ClienteProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CarteraTceaService {
    @Autowired
    private CarteraTceaRepository carteraTceaRepository;
    @Autowired
    private ClienteProveedorRepository clienteProveedorRepository;

    public String insertarCarteraTcea(String ruc) {

        List<Object[]> flujos =carteraTceaRepository.flujos(ruc);
        if (!flujos.isEmpty()) {
        double tir = calcularTIR(flujos);
        CarteraTcea tceadia= carteraTceaRepository.carteraTceaDia(ruc);
        ///verificamos la existencia de la cartera de un dia
        if(tceadia==null){
            CarteraTcea carteraTcea = new CarteraTcea();
            ClienteProveedor proveedor = clienteProveedorRepository.findById(ruc).orElse(null);
            carteraTcea.setTcea(tir);
            carteraTcea.setFecha(LocalDateTime.now());
            carteraTcea.setMonto(calcularInversion(flujos));
            carteraTcea.setProveedorCartera(proveedor);
            carteraTceaRepository.save(carteraTcea);
            //return "se una nueva cartera de tcea del dia";
        }else {
            tceadia.setFecha(LocalDateTime.now());
            tceadia.setMonto(calcularInversion(flujos));
            tceadia.setTcea(tir);
            carteraTceaRepository.save(tceadia);
            //return "se modifico la cartera existente del dia";
        }
        return "TIR: " + tir;}
        return "no se puedo encontrar flujos";
    }
    
    ///caculamos el tir mediante el algoritmo binomial
    private double calcularTIR(List<Object[]> flujos) {
        double inversion = calcularInversion(flujos );

        double tirLow = -1.0;    // Límite inferior de la TIR
        double tirHigh = 50.0;   //Límite superior de la TIR
        double tolerance = 0.00000001;

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
        return tirMid;
    }

    ///calculamos el van
    private double calcularVAN( List<Object[]> flujos , double inversion, double tir) {
        double van=-inversion;
        for (Object[] flujo : flujos) {
            java.sql.Date sqlDate = (java.sql.Date) flujo[0];
            LocalDate fechaVencimiento = sqlDate.toLocalDate();
            int dias= calcularDias(fechaVencimiento);
            double flujoPago= (double) flujo[3];
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

    ///calcular inversion que es la suma de los montos sin igv de la factura
    private double calcularInversion(List<Object[]> flujos ) {
        double inversion=0.0;
        for(Object[] flujo : flujos) {
            inversion += (double) flujo[1];
        }
        return inversion;
    }



    public List<CarteraTcea> getCarteraTcea(String rucCliente){return carteraTceaRepository.findByRuc(rucCliente);}

}
