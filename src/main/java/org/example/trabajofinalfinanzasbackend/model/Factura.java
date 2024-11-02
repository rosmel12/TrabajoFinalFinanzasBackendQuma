package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero", nullable = false, unique = true)
    private String numero;

    @Column(name = "montoTotal", nullable = false)
    private double montoTotal;

    @Column(name = "montoTotalIgv", nullable = false)
    private double montoTotalIgv;

    @Column(name = "moneda", nullable = false)
    private String moneda;

    @Column(name = "fechaEmision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fechaVencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    //relacion-Cliente_proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rucClienteProveedor" ,nullable=false)
    private ClienteProveedor proveedorFactura;

    //relacion-Cliente_deudor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rucClienteDeudor" ,nullable=false)
    private ClienteDeudor deudorFactura;

    //realcion operacion_factoring
    @OneToOne(mappedBy ="facturaOperacion", cascade = CascadeType.ALL)
    private OperacionFactoring operacionFactoring;
}
