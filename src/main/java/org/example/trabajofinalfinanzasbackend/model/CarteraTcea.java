package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "carteratcea")
public class CarteraTcea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tcea", nullable = false)
    private double tcea;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    //relacion-cliente_proveeddor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rucCliente",nullable=false)
    private ClienteProveedor proveedorCartera;
}
