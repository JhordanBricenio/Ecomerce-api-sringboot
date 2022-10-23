package com.codej.service;


import com.codej.model.Cupon;

import java.util.List;

public interface ICuponService {
    public List<Cupon> findAll();
    public Cupon findById(Integer id);
    public Cupon save (Cupon cupon);
    public void delete(Integer id);
}
