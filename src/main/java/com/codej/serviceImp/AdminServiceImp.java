package com.codej.serviceImp;

import com.codej.model.Admin;
import com.codej.repository.IAdminRepository;
import com.codej.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImp implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;

    public AdminServiceImp(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Page<Admin> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Admin findById(Integer id) {
        return null;
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void delete(Integer id) {

    }
}
