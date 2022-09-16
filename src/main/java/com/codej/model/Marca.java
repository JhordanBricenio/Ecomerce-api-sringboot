package com.codej.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;

}
