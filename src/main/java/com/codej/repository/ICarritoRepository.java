package com.codej.repository;
import com.codej.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICarritoRepository extends JpaRepository<Carrito, Integer> {

    //consulta el carrito por cliente
    @Query("select c from Carrito c where c.cliente.id = ?1")
    public List<Carrito> findByCliente(Integer id);

    //Eliminar producto del carrito
    @Query("delete from Carrito c where c.producto.id = ?1")
    public void deleteProduct(Integer id);

}
