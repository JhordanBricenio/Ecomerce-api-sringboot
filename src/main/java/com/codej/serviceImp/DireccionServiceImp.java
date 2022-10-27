package com.codej.serviceImp;

import com.codej.model.Direccion;
import com.codej.repository.IDireccioRepository;
import com.codej.service.IDireccionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImp implements IDireccionService {

    private IDireccioRepository repository;

    public DireccionServiceImp(IDireccioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Direccion> findAll() {
        return repository.findAll();
    }

    @Override
    public Direccion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Direccion findByClientePrincipal(Integer id) {
        return repository.findByClientePrincipal(id);
    }

    @Override
    public List<Direccion> findByCliente(Integer id) {
       return repository.findByCliente(id);
    }

    @Override
    public Direccion save(Direccion direccion) {
        return repository.save(direccion);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
