package com.codej.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "promocion")
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 200)
    private String titulo;
    private String banner;
    private double descuento;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechainicio;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name="create_at",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }


}
