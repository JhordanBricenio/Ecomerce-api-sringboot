package com.codej.repository;

import com.codej.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
}
