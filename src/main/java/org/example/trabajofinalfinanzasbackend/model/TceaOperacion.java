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

    //relacion operacion_Factoring
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idOperacionFactoring" ,nullable=false)
    private OperacionFactoring tceaOperacionFactoring;


}
