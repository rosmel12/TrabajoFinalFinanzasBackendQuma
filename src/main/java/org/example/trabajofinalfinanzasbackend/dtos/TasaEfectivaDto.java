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
public class TasaEfectivaDto {
    private Integer id;
    private double tasaInteres;
    private String plazo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
