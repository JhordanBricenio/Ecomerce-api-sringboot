package com.codej.serviceImp;

import com.codej.model.*;
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
    private IVariedadRepository variedadRepository;
    private IImagesRepository imagenRepository;

    public ProductServiceImp(IProductRepository productRepository,
                                IInventarioRepository inventarioRepository,
                                IVariedadRepository variedadRepository,
                             IImagesRepository imagenRepository) {
        this.productRepository = productRepository;
        this.inventarioRepository = inventarioRepository;
        this.variedadRepository = variedadRepository;
        this.imagenRepository= imagenRepository;
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
    public Page<Product> findAllByTitulo(String filtro, Pageable pageable) {
        return productRepository.findByTituloContaining(filtro, pageable);
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

    @Override
    public Variedad guardar(Variedad variedad) {
        return variedadRepository.save(variedad);
    }

    @Override
    public Imagen guardar(Imagen galeria) {
        return imagenRepository.save(galeria);
    }


}
