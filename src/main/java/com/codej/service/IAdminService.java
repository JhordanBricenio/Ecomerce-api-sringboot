package com.codej.service;

import com.codej.model.Admin;
import com.codej.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdminService {
    public List<Admin> findAll();
    public Page<Admin> findAll(Pageable pageable);
    public Admin findById(Integer id);
    public Admin save (Admin admin);
    public void delete(Integer id);
}
