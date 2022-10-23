package com.codej.serviceImp;

import com.codej.model.Cliente;
import com.codej.model.Contacto;
import com.codej.repository.IClienteRepository;
import com.codej.repository.IContactoRepository;
import com.codej.service.IClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImp implements IClienteService {

    private IClienteRepository clienteRepository;
    private IContactoRepository contactoRepository;

    public ClienteServiceImp(IClienteRepository clienteRepository,IContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
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

    @Override
    public Contacto saveContact(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public List<Contacto> findAllContact() {
        return contactoRepository.findAll();
    }

    @Override
    public Contacto findContactById(Integer id) {
        return contactoRepository.findById(id).orElse(null);
    }
}
