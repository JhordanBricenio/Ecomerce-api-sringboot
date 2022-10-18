package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;


    @JsonIgnoreProperties(value = {"roles","hibernateLazyInitializer", "handler"})
    @ManyToMany(mappedBy = "roles")
    private List<Cliente> clientes;


}
