package com.codej.service;

import com.codej.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Page<Product> findAll(Pageable pageable);
    public Product findById(Integer id);
    public Product save (Product product);
    public void delete(Integer id);

    public List<Categoria> findAllCategories();
    public List<Marca> findAllMarcas();

    public Inventario guardar (Inventario inventario);

    public Inventario findAllInventarioByProduct(Integer id);

    //Buscar producto por slug
    public Product findBySlug(String slug);

    //listar productos por categoria
    public List<Product> findByCategoria(Integer id);

    //guagrar variedad
    public Variedad guardar (Variedad variedad);

    //Guardar galeria
    public Imagen guardar (Imagen galeria);

}
