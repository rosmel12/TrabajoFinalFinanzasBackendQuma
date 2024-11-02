package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "clienteproveedor")
public class ClienteProveedor {
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public List<Factura> getFacturasCliente() {
        return facturasCliente;
    }

    public void setFacturasCliente(List<Factura> facturasCliente) {
        this.facturasCliente = facturasCliente;
    }

    public List<CarteraTcea> getCarteraTceas() {
        return carteraTceas;
    }

    public void setCarteraTceas(List<CarteraTcea> carteraTceas) {
        this.carteraTceas = carteraTceas;
    }

    public Usuario getUserCliente() {
        return userCliente;
    }

    public void setUserCliente(Usuario userCliente) {
        this.userCliente = userCliente;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    //relacion-usuario
    @OneToOne
    @JoinColumn(name="idUsuario",nullable=false)
    private Usuario userCliente;

    //relacion-cartera_tcea
    @OneToMany(mappedBy = "proveedorCartera",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CarteraTcea> carteraTceas;

    //relacion factura
    @OneToMany(mappedBy = "proveedorFactura",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Factura> facturasCliente;



}
