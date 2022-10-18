package com.codej.serviceImp;

import com.codej.model.Venta;
import com.codej.repository.IVentaRepository;
import com.codej.service.IVentaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImp implements IVentaService {

    private IVentaRepository ventaRepository;

    public VentaServiceImp(IVentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta findById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void delete(Integer id) {
        ventaRepository.deleteById(id);

    }
}
