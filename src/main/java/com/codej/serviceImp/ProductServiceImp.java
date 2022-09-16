package com.codej.serviceImp;

import com.codej.model.Categoria;
import com.codej.model.Marca;
import com.codej.model.Product;
import com.codej.repository.IProductRepository;
import com.codej.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService {

    private IProductRepository productRepository;

    public ProductServiceImp(IProductRepository productRepository) {
        this.productRepository = productRepository;
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
}
