package com.codej.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "cupon")
public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 50)
    private String tipo;
    @Column(nullable = false,length = 200)
    private String codigo;
    @Column(nullable = false)
    private double valor;
    @Column(nullable = false)
    private double limite;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date create_at;

    @PrePersist
    public void prePersist(){
        create_at = new Date();
    }
}
