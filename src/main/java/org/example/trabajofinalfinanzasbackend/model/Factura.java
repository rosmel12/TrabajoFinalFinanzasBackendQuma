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

    public OperacionFactoring getOperacionFactoring() {
        return operacionFactoring;
    }

    public void setOperacionFactoring(OperacionFactoring operacionFactoring) {
        this.operacionFactoring = operacionFactoring;
    }

    public ClienteDeudor getDeudorFactura() {
        return deudorFactura;
    }

    public void setDeudorFactura(ClienteDeudor deudorFactura) {
        this.deudorFactura = deudorFactura;
    }

    public ClienteProveedor getProveedorFactura() {
        return proveedorFactura;
    }

    public void setProveedorFactura(ClienteProveedor proveedorFactura) {
        this.proveedorFactura = proveedorFactura;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getMontoTotalIgv() {
        return montoTotalIgv;
    }

    public void setMontoTotalIgv(double montoTotalIgv) {
        this.montoTotalIgv = montoTotalIgv;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

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
