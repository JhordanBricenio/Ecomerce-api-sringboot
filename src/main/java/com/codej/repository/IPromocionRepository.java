package com.codej.repository;

import com.codej.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromocionRepository extends JpaRepository<Promocion, Integer> {
    public Promocion findByTitulo(String titulo);

}
