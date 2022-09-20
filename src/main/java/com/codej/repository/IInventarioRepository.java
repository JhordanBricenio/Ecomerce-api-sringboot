package com.codej.repository;

import com.codej.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {

}

