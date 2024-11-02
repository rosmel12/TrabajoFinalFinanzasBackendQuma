package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tceaoperacion")
public class TceaOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tcea", nullable = false)
    private double tcea;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OperacionFactoring getTceaOperacionFactoring() {
        return tceaOperacionFactoring;
    }

    public void setTceaOperacionFactoring(OperacionFactoring tceaOperacionFactoring) {
        this.tceaOperacionFactoring = tceaOperacionFactoring;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTcea() {
        return tcea;
    }

    public void setTcea(double tcea) {
        this.tcea = tcea;
    }

    //relacion operacion_Factoring
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idOperacionFactoring" ,nullable=false)
    private OperacionFactoring tceaOperacionFactoring;


}
