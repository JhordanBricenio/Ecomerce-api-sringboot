package com.codej.repository;

import com.codej.model.Carrito;
import com.codej.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDireccioRepository extends JpaRepository<Direccion, Integer> {

    //consulta direcciones por cliente
    @Query("select d from Direccion d where d.cliente.id = ?1")
    public List<Direccion> findByCliente(Integer id);

    //Obtener direcion principal por cliente
    @Query("select d from Direccion d where d.cliente.id = ?1 and d.principal = true")
    public Direccion findByClientePrincipal(Integer id);
}
