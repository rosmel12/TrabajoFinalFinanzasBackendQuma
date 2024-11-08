package org.example.trabajofinalfinanzasbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarteraTceaDto {
    private Integer id;
    private double tcea;
    private double cantidadOperaciones;
    private double montosNominales;
    private double montosDescontados;
    private double montosRecibidos;
    private String moneda;
    private LocalDateTime fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTcea() {
        return tcea;
    }

    public void setTcea(double tcea) {
        this.tcea = tcea;
    }

    public double getCantidadOperaciones() {
        return cantidadOperaciones;
    }

    public void setCantidadOperaciones(double cantidadOperaciones) {
        this.cantidadOperaciones = cantidadOperaciones;
    }

    public double getMontosNominales() {
        return montosNominales;
    }

    public void setMontosNominales(double montosNominales) {
        this.montosNominales = montosNominales;
    }

    public double getMontosDescontados() {
        return montosDescontados;
    }

    public void setMontosDescontados(double montosDescontados) {
        this.montosDescontados = montosDescontados;
    }

    public double getMontosRecibidos() {
        return montosRecibidos;
    }

    public void setMontosRecibidos(double montosRecibidos) {
        this.montosRecibidos = montosRecibidos;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
