package com.codej.serviceImp;

import com.codej.model.Carrito;
import com.codej.model.Cliente;
import com.codej.repository.ICarritoRepository;
import com.codej.service.ICarritoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CarritoServiceImp implements ICarritoService {
    private ICarritoRepository carritoRepository;

    public CarritoServiceImp(ICarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Override
    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    @Override
    public List<Carrito> findByCliente(Integer id) {
        return carritoRepository.findByCliente(id);
    }

    @Override
    public Page<Carrito> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Carrito findById(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    @Override
    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public void delete(Integer id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        carritoRepository.deleteProduct(id);
    }


}
