package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "operacionfactoring")
public class OperacionFactoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fechaOperacion", nullable = false)
    private LocalDateTime fechaOperacion;

    @Column(name = "tasaInteresAplicada", nullable = false)
    private double tasaInteresAplicada;

    @Column(name = "montoDescuento", nullable = false)
    private double montoDescuento;

    @Column(name = "montoPago", nullable = false)
    private double montoPago;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<NotificacionCliente> getNotificacionClientes() {
        return notificacionClientes;
    }

    public void setNotificacionClientes(List<NotificacionCliente> notificacionClientes) {
        this.notificacionClientes = notificacionClientes;
    }

    public List<TceaOperacion> getTceas() {
        return tceas;
    }

    public void setTceas(List<TceaOperacion> tceas) {
        this.tceas = tceas;
    }

    public Descuento getDescuentoOperacion() {
        return descuentoOperacion;
    }

    public void setDescuentoOperacion(Descuento descuentoOperacion) {
        this.descuentoOperacion = descuentoOperacion;
    }

    public Factura getFacturaOperacion() {
        return facturaOperacion;
    }

    public void setFacturaOperacion(Factura facturaOperacion) {
        this.facturaOperacion = facturaOperacion;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public double getTasaInteresAplicada() {
        return tasaInteresAplicada;
    }

    public void setTasaInteresAplicada(double tasaInteresAplicada) {
        this.tasaInteresAplicada = tasaInteresAplicada;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    //relacion factura
    @OneToOne
    @JoinColumn(name="idFactura" ,nullable=false)
    private Factura facturaOperacion;

    //relacion descuento
    @OneToOne
    @JoinColumn(name="idDescuento" ,nullable=false)
    private Descuento descuentoOperacion;

    //realcion tcea_operacion
    @OneToMany(mappedBy = "tceaOperacionFactoring",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<TceaOperacion> tceas;

    //relacion notificacion
    @OneToMany(mappedBy = "notificacionOperacionFactoring",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<NotificacionCliente> notificacionClientes;

}
