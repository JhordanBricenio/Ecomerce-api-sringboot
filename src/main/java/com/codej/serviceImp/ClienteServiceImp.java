package com.codej.serviceImp;

import com.codej.model.Cliente;
import com.codej.repository.IClienteRepository;
import com.codej.service.IClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImp implements IClienteService {

    private IClienteRepository clienteRepository;

    public ClienteServiceImp(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }
}
