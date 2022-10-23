package com.codej.repository;

import com.codej.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImagesRepository extends JpaRepository<Imagen, Integer> {
}
