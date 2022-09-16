package com.codej.repository;

import com.codej.model.Categoria;
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

}
