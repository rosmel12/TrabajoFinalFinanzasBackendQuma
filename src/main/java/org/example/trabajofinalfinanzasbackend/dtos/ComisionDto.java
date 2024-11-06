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
}
