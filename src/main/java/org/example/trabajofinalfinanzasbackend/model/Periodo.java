package org.example.trabajofinalfinanzasbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "periodo")
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plazoTasa", nullable = false)
    private String plazoTasa;

    @Column(name = "plazoDIas", nullable = false)
    private int plazoDIas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlazoDIas() {
        return plazoDIas;
    }

    public void setPlazoDIas(int plazoDIas) {
        this.plazoDIas = plazoDIas;
    }

    public String getPlazoTasa() {
        return plazoTasa;
    }

    public void setPlazoTasa(String plazoTasa) {
        this.plazoTasa = plazoTasa;
    }
}
