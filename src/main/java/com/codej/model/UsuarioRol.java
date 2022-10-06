package com.codej.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "usuario_rol")
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rol rol;

}
