package com.codej.serviceImp;

import com.codej.model.Cupon;
import com.codej.repository.ICuponRepository;
import com.codej.service.ICuponService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuponServiceImp implements ICuponService {

    private ICuponRepository cuponRepository;

    public CuponServiceImp(ICuponRepository cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    @Override
    public List<Cupon> findAll() {
        return cuponRepository.findAll();
    }

    @Override
    public Cupon findById(Integer id) {
        return cuponRepository.findById(id).orElse(null);
    }

    @Override
    public Cupon save(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    @Override
    public void delete(Integer id) {
        cuponRepository.deleteById(id);
    }

    @Override
    public Cupon findByCodigo(String codigo) {
        return cuponRepository.findByCodigo(codigo);
    }
}
