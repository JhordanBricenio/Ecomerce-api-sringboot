package com.codej.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    private String apellidos;

    @Column(unique = true)
    private String email;
    private String pais;
    private String password;
    private String perfil;
    private String telefono;
    private Date fechaNac;
    private String genero;
    private int dni;
}
