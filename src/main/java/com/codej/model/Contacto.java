package com.codej.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "contactos")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    private String mensaje;
    private String asunto;
    private String estado;
    private String email;
    private String telefono;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @PrePersist
    public void prePersist() {
        fecha = new Date();
    }

}
