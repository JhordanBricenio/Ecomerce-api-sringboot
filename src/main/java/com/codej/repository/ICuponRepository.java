package com.codej.repository;

import com.codej.model.Cupon;
import com.codej.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuponRepository extends JpaRepository<Cupon, Integer> {

    public Cupon findByCodigo(String titulo);
}
