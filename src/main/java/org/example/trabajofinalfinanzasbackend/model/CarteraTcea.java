package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "carteratcea")
public class CarteraTcea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tcea", nullable = false)
    private double tcea;

    @Column(name = "cantidadOperaciones", nullable = false)
    private double cantidadOperaciones;

    @Column(name = "montosNominales", nullable = false)
    private double montosNominales;

    @Column(name = "montosDescontados", nullable = false)
    private double montosDescontados;

    @Column(name = "montosRecibidos", nullable = false)
    private double montosRecibidos;

    @Column(name="moneda",nullable = false)
    private String moneda;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public ClienteProveedor getProveedorCartera() {
        return proveedorCartera;
    }

    public void setProveedorCartera(ClienteProveedor proveedorCartera) {
        this.proveedorCartera = proveedorCartera;
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

    public double getMontosRecibidos() {
        return montosRecibidos;
    }

    public void setMontosRecibidos(double montosRecibidos) {
        this.montosRecibidos = montosRecibidos;
    }

    public double getMontosDescontados() {
        return montosDescontados;
    }

    public void setMontosDescontados(double montosDescontados) {
        this.montosDescontados = montosDescontados;
    }

    public double getMontosNominales() {
        return montosNominales;
    }

    public void setMontosNominales(double montosNominales) {
        this.montosNominales = montosNominales;
    }

    public double getCantidadOperaciones() {
        return cantidadOperaciones;
    }

    public void setCantidadOperaciones(double cantidadOperaciones) {
        this.cantidadOperaciones = cantidadOperaciones;
    }

    public double getTcea() {
        return tcea;
    }

    public void setTcea(double tcea) {
        this.tcea = tcea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //relacion-cliente_proveeddor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rucCliente",nullable=false)
    private ClienteProveedor proveedorCartera;
}
