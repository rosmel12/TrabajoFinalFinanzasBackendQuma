package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.geom.QuadCurve2D;
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

    @Column(name = "valorNominal", nullable = false)
    private double valorNominal;

    @Column(name = "numeroDias" ,nullable=false)
    private int numeroDias;

    @Column(name = "tasaEfectivaAplicada", nullable = false)
    private double tasaEfectivaAplicada;

    @Column(name = "tasaDescuento", nullable = false)
    private double tasaDescuento;

    @Column(name = "descuento", nullable = false)
    private double descuento;

    @Column(name = "costesIniciales", nullable = false)
    private double costesIniciales;

    @Column(name = "costesFinales", nullable = false)
    private double costesFinales;

    @Column(name = "valorNeto", nullable = false)
    private double valorNeto;

    @Column(name = "valorRecibido", nullable = false)
    private double valorRecibido;

    @Column(name = "valorEntregado", nullable = false)
    private double valorEntregado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public double getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public double getTasaEfectivaAplicada() {
        return tasaEfectivaAplicada;
    }

    public void setTasaEfectivaAplicada(double tasaEfectivaAplicada) {
        this.tasaEfectivaAplicada = tasaEfectivaAplicada;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public double getTasaDescuento() {
        return tasaDescuento;
    }

    public void setTasaDescuento(double tasaDescuento) {
        this.tasaDescuento = tasaDescuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getCostesIniciales() {
        return costesIniciales;
    }

    public void setCostesIniciales(double costesIniciales) {
        this.costesIniciales = costesIniciales;
    }

    public double getCostesFinales() {
        return costesFinales;
    }

    public void setCostesFinales(double costesFinales) {
        this.costesFinales = costesFinales;
    }

    public double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public double getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(double valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public double getValorEntregado() {
        return valorEntregado;
    }

    public void setValorEntregado(double valorEntregado) {
        this.valorEntregado = valorEntregado;
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
