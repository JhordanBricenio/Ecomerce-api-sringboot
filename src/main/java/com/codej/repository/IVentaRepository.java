package com.codej.repository;

import com.codej.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Integer> {

   //Obtener todas las ventas de un cliente
   @Modifying
   @Transactional
    @Query("select v from Venta v where v.cliente.id = ?1")
    List<Venta> findAllVentasPorId(Integer id);

    //Filtrar ventas por fechas
    @Modifying
    @Transactional
    @Query("select v from Venta v where v.fecha between :fecha1 and :fecha2")
    List<Venta> findAllVentasPorFechas(@RequestParam ("fecha1") Date fecha1 , @RequestParam ("fecha1")Date fecha2);


}
