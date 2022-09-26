package com.codej.repository;
import com.codej.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
}
