package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String destinatario;
    private String dni;
    private String zip;
    private String direccion;
    private String pais;
    private String region;
    private String provincia;
    private String distrito;
    private String telefono;
    private boolean principal;

    //Relacion con direcciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"direcciones","hibernateLazyInitializer", "handler"}, allowSetters = true)
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    public boolean isPrincipal() {
        return principal;
    }


}
