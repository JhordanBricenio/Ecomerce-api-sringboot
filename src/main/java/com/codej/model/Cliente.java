package com.codej.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private boolean enabled=true;

    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    private String genero;
    private int dni;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

}
