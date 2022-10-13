package com.codej.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name = "producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String slug;

    @Column(nullable = false)
    private String titulo;


    private double precio;
    private String sku;

    private String descripcion;
    private String imagen;
    private String contenido;


    private double stock;
    private double nventas;

    private double npuntos=5;


    private String estado;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
        this.sku = generarSku();

    }

    @NotNull(message = "La categoria no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorias_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categoria categoria;

    @NotNull(message = "La marca no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marca marca;

    //CRear funcion para slug
    public String slugify(String input) {
        String acentos = "áéíóúÁÉÍÓÚ";
        String original = "aeiouAEIOU";
        for (int i = 0; i < acentos.length(); i++) {
            input = input.replace(acentos.charAt(i), original.charAt(i));
        }
        return input.toLowerCase().replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.slug = slugify(titulo);
    }

    //Generar sku
    public String generarSku(){
        String sku = "";
        String[] palabras = this.titulo.split(" ");
        for (int i = 0; i < palabras.length; i++) {
            if (i < 3){
                sku += palabras[i].substring(0, 3)+this.getId();
            }
        }
        return sku.toUpperCase();
    }



}
