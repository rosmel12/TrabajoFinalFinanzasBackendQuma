package org.example.trabajofinalfinanzasbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {
    private Integer id;
    private String numero;
    private double montoTotal;
    private double montoTotalIgv;
    private String moneda;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private String rucClienteProveedor;
    private String rucClienteDeudor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRucClienteDeudor() {
        return rucClienteDeudor;
    }

    public void setRucClienteDeudor(String rucClienteDeudor) {
        this.rucClienteDeudor = rucClienteDeudor;
    }

    public String getRucClienteProveedor() {
        return rucClienteProveedor;
    }

    public void setRucClienteProveedor(String rucClienteProveedor) {
        this.rucClienteProveedor = rucClienteProveedor;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
