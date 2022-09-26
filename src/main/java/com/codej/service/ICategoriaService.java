package com.codej.service;

import com.codej.model.Categoria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriaService {
    public List<Categoria> findAll();
    public Categoria findById(Integer id);
    public Categoria save (Categoria product);
    public void delete(Integer id);
}
