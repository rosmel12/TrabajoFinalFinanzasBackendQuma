package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "comision")
public class Comision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estudioRiesgo", nullable = false)
    private double estudioRiesgo;

    @Column(name = "seguroDesgravamen", nullable = false)
    private double seguroDesgravamen;

    @Column(name = "fotoCopias", nullable = false)
    private double fotoCopias;

    @Column(name = "gastoAdministracion", nullable = false, unique = true)
    private double gastoAdministracion;

    @Column(name = "porte", nullable = false, unique = true)
    private double porte;

    @Column(name = "moneda", nullable = false, unique = true)
    private String moneda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getEstudioRiesgo() {
        return estudioRiesgo;
    }

    public void setEstudioRiesgo(double estudioRiesgo) {
        this.estudioRiesgo = estudioRiesgo;
    }

    public double getSeguroDesgravamen() {
        return seguroDesgravamen;
    }

    public void setSeguroDesgravamen(double seguroDesgravamen) {
        this.seguroDesgravamen = seguroDesgravamen;
    }

    public double getFotoCopias() {
        return fotoCopias;
    }

    public void setFotoCopias(double fotoCopias) {
        this.fotoCopias = fotoCopias;
    }

    public double getGastoAdministracion() {
        return gastoAdministracion;
    }

    public void setGastoAdministracion(double gastoAdministracion) {
        this.gastoAdministracion = gastoAdministracion;
    }

    public double getPorte() {
        return porte;
    }

    public void setPorte(double porte) {
        this.porte = porte;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<Descuento> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Descuento> descuentos) {
        this.descuentos = descuentos;
    }

    //relacion descuento
    @OneToMany(mappedBy = "comisionDescuento",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Descuento> descuentos;
}
