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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OperacionFactoring getOperacionFactoring() {
        return operacionFactoring;
    }

    public void setOperacionFactoring(OperacionFactoring operacionFactoring) {
        this.operacionFactoring = operacionFactoring;
    }

    public TasaEfectiva getTasaEfectivaDescuento() {
        return tasaEfectivaDescuento;
    }

    public void setTasaEfectivaDescuento(TasaEfectiva tasaEfectivaDescuento) {
        this.tasaEfectivaDescuento = tasaEfectivaDescuento;
    }

    public TasaNominal getTasaNominalDescuento() {
        return tasaNominalDescuento;
    }

    public void setTasaNominalDescuento(TasaNominal tasaNominalDescuento) {
        this.tasaNominalDescuento = tasaNominalDescuento;
    }

    public Comision getComisionDescuento() {
        return comisionDescuento;
    }

    public void setComisionDescuento(Comision comisionDescuento) {
        this.comisionDescuento = comisionDescuento;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

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
