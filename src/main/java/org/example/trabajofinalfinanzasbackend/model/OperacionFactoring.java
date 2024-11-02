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
