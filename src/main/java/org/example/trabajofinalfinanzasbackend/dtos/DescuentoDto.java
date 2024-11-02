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
public class DescuentoDto {
    private Integer id;
    private LocalDateTime fecha;
    private Integer idComision;
    private Integer idTasaNominal;
    private Integer idTasaEfectiva;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTasaEfectiva() {
        return idTasaEfectiva;
    }

    public void setIdTasaEfectiva(Integer idTasaEfectiva) {
        this.idTasaEfectiva = idTasaEfectiva;
    }

    public Integer getIdTasaNominal() {
        return idTasaNominal;
    }

    public void setIdTasaNominal(Integer idTasaNominal) {
        this.idTasaNominal = idTasaNominal;
    }

    public Integer getIdComision() {
        return idComision;
    }

    public void setIdComision(Integer idComision) {
        this.idComision = idComision;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
