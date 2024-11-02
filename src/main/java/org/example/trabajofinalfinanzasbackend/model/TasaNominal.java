package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tasanominal")
public class TasaNominal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tasaInteres", nullable = false)
    private double tasaInteres;

    @Column(name = "plazo", nullable = false)
    private String plazo;

    @Column(name = "capitalizable", nullable = false)
    private String capitalizable;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fechaFin", nullable = false)
    private LocalDateTime fechaFin;

    //relacion descuento
    @OneToMany(mappedBy = "tasaNominalDescuento",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Descuento> descuentos;

}