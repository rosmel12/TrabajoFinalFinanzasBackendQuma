package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clientedeudor")
public class ClienteDeudor {
    @Id
    private String ruc;

    @Column(name = "nombreEmpresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "direccionEmpresa", nullable = false)
    private String direccionEmpresa;

    @Column(name = "telefonoEmpresa", nullable = false)
    private String telefonoEmpresa;

    @Column(name = "correoEmpresa", nullable = false)
    private String correoEmpresa;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    //relacion factura
    @OneToMany(mappedBy = "deudorFactura",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Factura> facturasCliente;
}
