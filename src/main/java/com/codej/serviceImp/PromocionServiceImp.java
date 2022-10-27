package com.codej.serviceImp;

import com.codej.model.Promocion;
import com.codej.repository.IPromocionRepository;
import com.codej.service.IPromocionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionServiceImp implements IPromocionService {

    private IPromocionRepository promocionRepository;

    public PromocionServiceImp(IPromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    @Override
    public List<Promocion> findAll() {
        return promocionRepository.findAll();
    }

    @Override
    public Promocion findById(Integer id) {
        return promocionRepository.findById(id).orElse(null);
    }

    @Override
    public Promocion save(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    @Override
    public void delete(Integer id) {
        promocionRepository.deleteById(id);
    }

    @Override
    public Promocion findByTitulo(String titulo) {
        return promocionRepository.findByTitulo(titulo);
    }
}
