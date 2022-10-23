package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "variedades")
public class Variedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String titulo_variedad;
    private String variedades;

    //Relacion con direcciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"variedades","hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "producto_id")
    private Product producto;
}
