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
