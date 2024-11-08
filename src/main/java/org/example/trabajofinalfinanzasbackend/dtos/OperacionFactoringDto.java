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
public class OperacionFactoringDto {
    private Integer id;
    private LocalDateTime fechaOperacion;
    private double valorNominal;
    private int numeroDias;
    private double tasaEfectivaAplicada;
    private double tasaDescuento;
    private double descuento;
    private double costesIniciales;
    private double costesFinales;
    private double valorNeto;
    private double valorRecibido;
    private double valorEntregado;
    private Integer idFactura;
    private Integer idDescuento;
}
