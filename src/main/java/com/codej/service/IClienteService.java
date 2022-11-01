package com.codej.service;

import com.codej.model.Cliente;
import com.codej.model.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    public List<Cliente> findAll();
    public Page<Cliente> findAll(Pageable pageable);
    public Cliente findById(Integer id);
    public Cliente save (Cliente cliente);
    public void delete(Integer id);

    public Page<Cliente> findByApellidosContaining(String filtro, Pageable pageable);

    public Contacto saveContact (Contacto contacto);

    public List<Contacto> findAllContact();

    public Contacto findContactById(Integer id);

}
