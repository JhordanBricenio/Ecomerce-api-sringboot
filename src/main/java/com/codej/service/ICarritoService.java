package com.codej.service;

import com.codej.model.Carrito;
import com.codej.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICarritoService {
    public List<Carrito> findAll();
    public List<Carrito> findByCliente(Integer id);
    public Page<Carrito> findAll(Pageable pageable);
    public Carrito findById(Integer id);
    public Carrito save (Carrito carrito);
    public void delete(Integer id);
    //Eliminar producto del carrito
    public void deleteProduct(Integer id);

    //Eliminar carrito por cliente
    public void deleteByCliente(Integer id);

    //limpiar carrito
    public void deleteAll();
}
