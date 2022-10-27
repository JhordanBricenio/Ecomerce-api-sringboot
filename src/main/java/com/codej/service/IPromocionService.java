package com.codej.service;

import com.codej.model.Promocion;

import java.util.List;

public interface IPromocionService {
    public List<Promocion> findAll();
    public Promocion findById(Integer id);
    public Promocion save (Promocion cupon);
    public void delete(Integer id);

    //Buscar por titulo
    public Promocion findByTitulo(String titulo);
}
