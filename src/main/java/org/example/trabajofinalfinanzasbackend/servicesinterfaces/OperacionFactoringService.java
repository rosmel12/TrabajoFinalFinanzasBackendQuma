package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.OperacionFactoringInsertarDto;
import org.example.trabajofinalfinanzasbackend.model.*;
import org.example.trabajofinalfinanzasbackend.repositories.DescuentoRepository;
import org.example.trabajofinalfinanzasbackend.repositories.FacturaRepository;
import org.example.trabajofinalfinanzasbackend.repositories.OperacionFactoringRepository;
import org.example.trabajofinalfinanzasbackend.repositories.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OperacionFactoringService {

@Autowired
private OperacionFactoringRepository operacionFactoringRepository;
@Autowired
private PeriodoRepository periodoRepository;
@Autowired
private TceaOperacionService tceaOperacionService;
@Autowired
private NotificacionClienteService notificacionClienteService;
@Autowired
private CarteraTceaService carteraTceaService;
@Autowired
private FacturaRepository facturaRepository;
@Autowired
private DescuentoRepository descuentoRepository;

private int diasFactura=0;
private double tep=0.0;
private double montoDescuento=0.0;

 ///insertar operacion factoring
public Integer insertarOperacion(OperacionFactoringInsertarDto operacionFactoringInsertarDto) {
   Factura factura=facturaRepository.findById(operacionFactoringInsertarDto.getIdFactura()).orElse(null);
   Descuento  descuento=descuentoRepository.findById(operacionFactoringInsertarDto.getIdDescuento()).orElse(null);

    if (factura!=null && descuento!=null) {
        Comision comision= descuento.getComisionDescuento();
        TasaNominal tasaNominal= descuento.getTasaNominalDescuento();
        TasaEfectiva tasaEfectiva= descuento.getTasaEfectivaDescuento();
        calcularDias(factura);
        if (tasaEfectiva != null) {
            convertirTasaEfectivaEfectiva(tasaEfectiva);
        } else if (tasaNominal != null) {
            convertirTasaNominalEfectiva(tasaNominal);
        }
        OperacionFactoring operacionFactoring = new OperacionFactoring();
        operacionFactoring.setFechaOperacion(LocalDateTime.now());
        operacionFactoring.setTasaInteresAplicada(this.tep);
        operacionFactoring.setMontoPago(calcularMontoDescuentoPago(factura,comision));
        operacionFactoring.setMontoDescuento(this.montoDescuento);
        operacionFactoring.setFacturaOperacion(factura);
        operacionFactoring.setDescuentoOperacion(descuento);
        ///creamos la operacion para la factura
        operacionFactoring = operacionFactoringRepository.save(operacionFactoring);
        System.out.println(operacionFactoring);
        ///creamos la tcea de la operacion
        tceaOperacionService.IngresarTceaOperacion(this.diasFactura,factura.getMontoTotal(), operacionFactoring);
        ///creamos la notificacion de la operacion
        notificacionClienteService.enviarNotificacionCliente(operacionFactoring);
        ///creamos la carteradel dia o actualizamos
        carteraTceaService.insertarCarteraTcea(factura.getProveedorFactura().getRuc());
        ///despues de realizado la operacion volvemos null a factura, descuento, comision, tasaNominal, tasaEfectiva para su uso en futuras operaciones
        return operacionFactoring.getId();
    }
    else {
        return null;
    }
}

///Calculo de dias Factura
private void calcularDias(Factura factura) {
/// Convertimos las fechas de Date a LocalDate
LocalDate fechaInicio = LocalDate.from(factura.getFechaEmision());
LocalDate fechaFin = LocalDate.from(factura.getFechaVencimiento());
/// Calculamos la diferencia en días
this.diasFactura =(int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
}

///Calculo las tasas a TEP
private void convertirTasaEfectivaEfectiva(TasaEfectiva tasaEfectiva) {
    Periodo periodo = periodoRepository.findByPlazoTasa(tasaEfectiva.getPlazo());
    this.tep= (Math.pow(1 +tasaEfectiva.getTasaInteres(), (double) diasFactura / periodo.getPlazoDIas()))-1;
}
private void convertirTasaNominalEfectiva(TasaNominal tasaNominal) {
    Periodo periodo = periodoRepository.findByPlazoTasa(tasaNominal.getPlazo());
    Periodo periodoCapitalizable = periodoRepository.findByPlazoTasa(tasaNominal.getCapitalizable());
    ///calcular m y n
    double m = (double) periodo.getPlazoDIas() /periodoCapitalizable.getPlazoDIas();
    double n= (double) this.diasFactura/periodoCapitalizable.getPlazoDIas();
    ///calcular tep
    this.tep=(Math.pow(1+(tasaNominal.getTasaInteres()/m),n))-1;
}

///Calculo TEPdescuento
private double calcularTasaDescuento(){ return (this.tep)/(1+this.tep);}

///Realizamos el calculo de la operacion de la factura
private double calcularMontoDescuentoPago(Factura factura, Comision comision) {
double montoDescuentoTasa = factura.getMontoTotal() * calcularTasaDescuento();
this.montoDescuento = montoDescuentoTasa + comision.getEnvio() + comision.getSeguro() + ( factura.getMontoTotal() * comision.getRetencion() );
return factura.getMontoTotal() - this.montoDescuento;
}


///listar operacion por factura
public OperacionFactoring listaroperacionPorFactura(Integer idFactura) {
    return operacionFactoringRepository.operacionFactura(idFactura);
}

///listar operacion por cliente
public List<OperacionFactoring> listaroperacionPorCliente(String ruc) {
    return operacionFactoringRepository.operacionesCliente(ruc);
}

}