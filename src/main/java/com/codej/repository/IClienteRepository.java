package com.codej.repository;

import com.codej.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IClienteRepository extends JpaRepository<Cliente , Integer > {

    //Filtrar por email
    @Query("select c from Cliente c where c.email = ?1")
    Cliente findByEmail(String email);

    //Filtrar por apellidos
    Page<Cliente> findByApellidosContaining(String filtro, Pageable pageable);



    //filtrar por username
    @Query("select c from Cliente c where c.username = ?1")
    Cliente findByUsername(String username);

}
