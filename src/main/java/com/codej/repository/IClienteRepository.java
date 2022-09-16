package com.codej.repository;

import com.codej.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente , Integer > {
}
