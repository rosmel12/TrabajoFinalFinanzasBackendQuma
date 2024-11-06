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


    //relacion descuento
    @OneToMany(mappedBy = "comisionDescuento",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Descuento> descuentos;
}
