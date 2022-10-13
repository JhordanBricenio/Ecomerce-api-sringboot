package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "clientes")
public class Cliente  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    private String username;
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

    @PrePersist
    public void prePersist() {
        username = email;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns =
    @JoinColumn(name = "rol_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"cliente_id", "rol_id"})})
    private List<Rol> roles;

    //Relacion con direcciones
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    public Cliente() {
        this.roles = new ArrayList<>();
        this.direcciones = new ArrayList<>();
    }

   public void agregarRol(Rol rol) {
        if (roles == null) {
            roles = new LinkedList<Rol>();
        }
        roles.add(rol);
    }



}
