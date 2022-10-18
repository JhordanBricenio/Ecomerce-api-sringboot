package com.codej.repository;

import com.codej.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Integer> {


}
