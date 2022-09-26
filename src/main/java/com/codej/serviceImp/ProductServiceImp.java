package com.codej.serviceImp;

import com.codej.model.Categoria;
import com.codej.model.Inventario;
import com.codej.model.Marca;
import com.codej.model.Product;
import com.codej.repository.*;
import com.codej.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService {

    private IProductRepository productRepository;
    private IInventarioRepository inventarioRepository;

    public ProductServiceImp(IProductRepository productRepository,
                                IInventarioRepository inventarioRepository) {
        this.productRepository = productRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Categoria> findAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    public List<Marca> findAllMarcas() {
        return productRepository.findAllMarcas();
    }

    @Override
    public Inventario guardar(Inventario inventario) {
        return  inventarioRepository.save(inventario);
    }

    @Override
    public Inventario findAllInventarioByProduct(Integer id) {
        return productRepository.findAllInventarioByProduct(id);
    }

    @Override
    public Product findBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }

    @Override
    public List<Product> findByCategoria(Integer id) {
        return productRepository.findByCategoria(id);
    }


}
