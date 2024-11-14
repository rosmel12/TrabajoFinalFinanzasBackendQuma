package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.OperacionFactoringInsertarDto;
import org.example.trabajofinalfinanzasbackend.model.*;
import org.example.trabajofinalfinanzasbackend.repositories.DescuentoRepository;
import org.example.trabajofinalfinanzasbackend.repositories.FacturaRepository;
import org.example.trabajofinalfinanzasbackend.repositories.OperacionFactoringRepository;
import org.example.trabajofinalfinanzasbackend.repositories.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

 ///insertar operacion factoring
public Integer insertarOperacion(OperacionFactoringInsertarDto operacionFactoringInsertarDto) {
   Factura factura=facturaRepository.findById(operacionFactoringInsertarDto.getIdFactura()).orElse(null);
   Descuento  descuento=descuentoRepository.findById(operacionFactoringInsertarDto.getIdDescuento()).orElse(null);

    if (factura!=null && descuento!=null) {
        Comision comision= descuento.getComisionDescuento();
        TasaNominal tasaNominal= descuento.getTasaNominalDescuento();
        TasaEfectiva tasaEfectiva= descuento.getTasaEfectivaDescuento();

        int diasFactura=calcularDias(factura);
        double tep=0.0;

        if (tasaEfectiva != null) {
            tep=convertirTasaEfectivaEfectiva(tasaEfectiva, diasFactura);
        } else if (tasaNominal != null) {
            tep=convertirTasaNominalEfectiva(tasaNominal, diasFactura);
        }

        ///creamos la operacion
        OperacionFactoring operacionFactoring = new OperacionFactoring();
        operacionFactoring.setFechaOperacion(LocalDateTime.now(ZoneId.of("America/Lima")));
        operacionFactoring.setValorNominal(factura.getMontoTotal());
        operacionFactoring.setNumeroDias(diasFactura);
        operacionFactoring.setTasaEfectivaAplicada(tep);
        operacionFactoring.setTasaDescuento(calcularTasaDescuento(tep));
        operacionFactoring.setDescuento(calcularDescuento(factura,tep));
        operacionFactoring.setCostesIniciales(calcularCostesIniciales(comision,factura));
        operacionFactoring.setCostesFinales(calcularCostesFinales(comision));
        operacionFactoring.setValorNeto(calcularValorNeto(factura,tep));
        operacionFactoring.setValorRecibido(calcularValorRecibido(factura,comision,tep));
        operacionFactoring.setValorEntregado(calcularValorEntrego(factura,comision));
        operacionFactoring.setFacturaOperacion(factura);
        operacionFactoring.setDescuentoOperacion(descuento);

        ///creamos la operacion para la factura
        operacionFactoring = operacionFactoringRepository.save(operacionFactoring);
        ///creamos la tcea de la operacion
        tceaOperacionService.IngresarTceaOperacion(operacionFactoring);
        ///creamos la notificacion de la operacion
        notificacionClienteService.enviarNotificacionCliente(operacionFactoring);
        ///creamos la cartera del dia o actualizamos
        carteraTceaService.insertarCarteraTcea(factura.getProveedorFactura().getRuc(), factura.getMoneda());
        ///despues de realizado la operacion volvemos null a factura, descuento, comision, tasaNominal, tasaEfectiva para su uso en futuras operaciones
        return operacionFactoring.getId();
    }
    else {
        return null;
    }
}

///Calculo de dias Factura
private int calcularDias(Factura factura) {
/// Convertimos las fechas de Date a LocalDate
LocalDate fechaInicio=LocalDate.now();
LocalDate fechaFin = LocalDate.from(factura.getFechaVencimiento());
/// Calculamos la diferencia en días
return  (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
}

///Calculo las tasas a TEP
private double convertirTasaEfectivaEfectiva(TasaEfectiva tasaEfectiva, int diasFactura) {
    Periodo periodo = periodoRepository.findByPlazoTasa(tasaEfectiva.getPlazo());
    ///calcular tep
    double tasaCalculada = Math.pow(1 + tasaEfectiva.getTasaInteres(), (double) diasFactura / periodo.getPlazoDIas()) - 1;
    BigDecimal tasaRedondeada = BigDecimal.valueOf(tasaCalculada).setScale(9, RoundingMode.HALF_UP);
    return tasaRedondeada.doubleValue();
}
private double convertirTasaNominalEfectiva(TasaNominal tasaNominal, int diasFactura) {
    Periodo periodo = periodoRepository.findByPlazoTasa(tasaNominal.getPlazo());
    Periodo periodoCapitalizable = periodoRepository.findByPlazoTasa(tasaNominal.getCapitalizable());
    ///calcular m y n
    double m = (double) periodo.getPlazoDIas() /periodoCapitalizable.getPlazoDIas();
    double n= (double) diasFactura/periodoCapitalizable.getPlazoDIas();
    ///calcular tep
    double tasaCalculada= (Math.pow(1+(tasaNominal.getTasaInteres()/m),n))-1;
    BigDecimal tasaRedondeada = BigDecimal.valueOf(tasaCalculada).setScale(9, RoundingMode.HALF_UP);
    return tasaRedondeada.doubleValue();
}

///Calculo TEPdescuento
private double calcularTasaDescuento(double tep ){
    double tasaDescuentoCalculada= (tep)/(1+tep);
    BigDecimal tasaRedondeada = BigDecimal.valueOf(tasaDescuentoCalculada).setScale(9, RoundingMode.HALF_UP);
    return tasaRedondeada.doubleValue();
}

///Realizamos el calculo del decuento
private double calcularDescuento(Factura factura,double tep) {
double montoDescuentoTasa = factura.getMontoTotal() * calcularTasaDescuento(tep);
BigDecimal montoRedondeado = BigDecimal.valueOf(montoDescuentoTasa).setScale(2, RoundingMode.HALF_UP);
return montoRedondeado.doubleValue();
}

///Realizamos el calculo de lo costes iniciales
private double calcularCostesIniciales(Comision comision,Factura factura){
    double costesIniciales= comision.getEstudioRiesgo()+factura.getMontoTotal()*comision.getSeguroDesgravamen()+comision.getFotoCopias();
    BigDecimal montoRedondeado = BigDecimal.valueOf(costesIniciales).setScale(2, RoundingMode.HALF_UP);
    return montoRedondeado.doubleValue();
}

///Relizamos el calculo de los costes finales
private double calcularCostesFinales(Comision comision){
    double costesFinales= comision.getGastoAdministracion()+comision.getPorte();
    BigDecimal montoRedondeado = BigDecimal.valueOf(costesFinales).setScale(2, RoundingMode.HALF_UP);
    return montoRedondeado.doubleValue();
}

///calculamos el valor neto
private double calcularValorNeto(Factura factura, double tep){
    double valorNeto=factura.getMontoTotal()-calcularDescuento(factura,tep);
    BigDecimal montoRedondeado = BigDecimal.valueOf(valorNeto).setScale(2, RoundingMode.HALF_UP);
    return montoRedondeado.doubleValue();
}

///calculamos el valor recibido
private double calcularValorRecibido(Factura factura,Comision comision, double tep){
    double valorRecibido=calcularValorNeto(factura,tep)-calcularCostesIniciales(comision,factura);
    BigDecimal montoRedondeado = BigDecimal.valueOf(valorRecibido).setScale(2, RoundingMode.HALF_UP);
    return montoRedondeado.doubleValue();
}

///calculamos el valor entregado o flujos
private double calcularValorEntrego(Factura factura,Comision comision){
    double valorEntrego=factura.getMontoTotal()+calcularCostesFinales(comision);
    BigDecimal montoRedondeado = BigDecimal.valueOf(valorEntrego).setScale(2, RoundingMode.HALF_UP);
    return montoRedondeado.doubleValue();
}

///listar operacion por factura
public Boolean listaroperacionPorFactura(Integer idFactura) {
    OperacionFactoring operacionFactoring= operacionFactoringRepository.operacionFactura(idFactura);
    return operacionFactoring != null;
}

///listar operacion por cliente
public List<OperacionFactoring> listaroperacionPorCliente(String ruc) {
    return operacionFactoringRepository.operacionesCliente(ruc);
}

}