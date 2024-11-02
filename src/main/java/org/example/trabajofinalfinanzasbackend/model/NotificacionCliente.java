package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notificacioncliente")
public class NotificacionCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    //relacion operacion_Factoring
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idOperacionFactoring",nullable=false )
    private OperacionFactoring notificacionOperacionFactoring;
}
