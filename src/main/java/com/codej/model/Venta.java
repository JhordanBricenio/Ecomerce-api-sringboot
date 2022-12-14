package com.codej.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nventa;
    private double subtotal;
    private Double totalPagar;
    private String envio_titulo;
    private double envio_precio;
    private String transaccion;
    private String estado;
    private String nota;
    private String direccion;
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @PrePersist
    public void prePersist() {
        fecha = new Date();
        estado = "Procesando";
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"ventas","hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    //Relacion con dventa
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id")
    private List<DVenta> dventas;


    public Venta() {
        this.dventas = new ArrayList<>();
    }

   /* public double getSubtotal() {
        double total = 0;
        for (DVenta dventa : dventas) {
            total += dventa.getSubtotal();
        }
        return total;
    }*/


}
