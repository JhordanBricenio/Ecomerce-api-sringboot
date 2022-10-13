package com.codej.service;

import com.codej.model.Direccion;

import java.util.List;
import java.util.Optional;

public interface IDireccionService {
    public List<Direccion> findAll();

    //Obentener direccion principal
    public Direccion findByClientePrincipal(Integer id);
    public List<Direccion> findByCliente(Integer id);
    public Direccion save (Direccion product);
    public void delete(Integer id);
}
