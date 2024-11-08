package org.example.trabajofinalfinanzasbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComisionDto {
    private Integer id;
    private double estudioRiesgo;
    private double seguroDesgravamen;
    private double fotoCopias;
    private double gastoAdministracion;
    private double porte;
    private String moneda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getPorte() {
        return porte;
    }

    public void setPorte(double porte) {
        this.porte = porte;
    }

    public double getGastoAdministracion() {
        return gastoAdministracion;
    }

    public void setGastoAdministracion(double gastoAdministracion) {
        this.gastoAdministracion = gastoAdministracion;
    }

    public double getFotoCopias() {
        return fotoCopias;
    }

    public void setFotoCopias(double fotoCopias) {
        this.fotoCopias = fotoCopias;
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
}
