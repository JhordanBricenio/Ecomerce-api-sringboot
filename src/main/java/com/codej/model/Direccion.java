package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;


}
