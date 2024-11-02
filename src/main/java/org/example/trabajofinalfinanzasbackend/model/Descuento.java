package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "descuento")
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    //relacion comisiones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idComision" ,nullable=false)
    private Comision comisionDescuento;

    //relacion tasa_nominal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idTasaNominal" )
    private TasaNominal tasaNominalDescuento ;

    //relacion tasa_efectiva
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idTasaEfectiva" )
    private TasaEfectiva tasaEfectivaDescuento;

    //relacion operacion_factoring
    @OneToOne(mappedBy ="descuentoOperacion", cascade = CascadeType.ALL)
    private OperacionFactoring operacionFactoring;

}
