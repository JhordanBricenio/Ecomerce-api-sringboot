package com.codej.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dventa")
public class DVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double subtotal;
    private double cantidad;
    private Date fecha;
    private Integer venta_id;

    //Relacion con producto
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Product producto;



    //Calculo de subtotal
    public double getSubtotal() {
        return cantidad * producto.getPrecio();
    }
}
