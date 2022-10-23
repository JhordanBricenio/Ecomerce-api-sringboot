package com.codej.repository;

import com.codej.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Integer> {

   //Obtener todas las ventas de un cliente
   @Modifying
   @Transactional
    @Query("select v from Venta v where v.cliente.id = ?1")
    List<Venta> findAllVentasPorId(Integer id);


}
