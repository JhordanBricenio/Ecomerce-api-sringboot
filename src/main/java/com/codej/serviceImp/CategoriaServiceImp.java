package com.codej.serviceImp;

import com.codej.model.Categoria;
import com.codej.repository.ICategoriaRepository;
import com.codej.service.ICategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImp implements ICategoriaService {

    private ICategoriaRepository categoriaRepository;

    public CategoriaServiceImp(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
