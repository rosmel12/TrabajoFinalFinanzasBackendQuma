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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OperacionFactoring getNotificacionOperacionFactoring() {
        return notificacionOperacionFactoring;
    }

    public void setNotificacionOperacionFactoring(OperacionFactoring notificacionOperacionFactoring) {
        this.notificacionOperacionFactoring = notificacionOperacionFactoring;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    //relacion operacion_Factoring
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idOperacionFactoring",nullable=false )
    private OperacionFactoring notificacionOperacionFactoring;
}
