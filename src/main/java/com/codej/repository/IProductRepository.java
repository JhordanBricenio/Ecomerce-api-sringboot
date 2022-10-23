package com.codej.repository;

import com.codej.model.Categoria;
import com.codej.model.Inventario;
import com.codej.model.Marca;
import com.codej.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query("from Categoria")
    public List<Categoria> findAllCategories();
    @Query("from Marca")
    public List<Marca> findAllMarcas();

    //Listar inventario por producto
    @Query("from Inventario where producto_id = ?1")
    public Inventario findAllInventarioByProduct(Integer id);

    //Buscar producto por slug
    public Product findBySlug(String slug);

    //listar productos por categoria
    @Query("from Product where categorias_id = ?1")
    public List<Product> findByCategoria(Integer id);

    //insertar variedad



}
