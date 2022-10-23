package com.codej.service;

import com.codej.controller.VentaRestController;
import com.codej.model.Direccion;
import com.codej.model.Venta;

import java.util.List;

public interface IVentaService {

    public List<Venta> findAll();
    public Venta findById(Integer id);
    public Venta save (Venta venta);
    public void delete(Integer id);

    //Obtener todas las ventas de un cliente
    List<Venta> findAllVentasPorId(Integer id);

}
