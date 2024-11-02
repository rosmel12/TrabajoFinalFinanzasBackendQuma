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
}
